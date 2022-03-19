package mse;

import mse.data.MseCard;
import mse.data.MseKeyword;
import mse.data.MseNode;
import mse.data.MseSet;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SetManager {

    private static final Path JMSE_SET_DIRECTORY = Path.of("jmse_sets");
    private static final boolean overwriteExisting = true;

    private static final MseMapper mapper = new MseMapper();

    private final String setName;
    private final String fileName;
    private MseSet set;

    private SetManager(String setName, String fileName) throws IOException {
        this.setName = setName;
        this.fileName = fileName;
        this.set = load();
    }

    public static SetManager get(String setName) throws IOException {
        Path basePath = JMSE_SET_DIRECTORY.resolve(setName);
        //TODO: Just guesses based on the first filename in the directory...
        String fileName = Objects.requireNonNull(basePath.toFile().list())[0].split("\\.")[0];
        return new SetManager(setName, fileName);
    }

    public static SetManager importMseSet(String setName, Path source) throws IOException {
        //Parse the file name of source file (everything before .mse-set)
        String sourceFileName = source.getFileName().toString();
        int index = sourceFileName.lastIndexOf(".mse-set");
        String fileName = sourceFileName.substring(0, index);

        //Create the directory to store everything in based on the given setName
        Path basePath = createDirectory(JMSE_SET_DIRECTORY.resolve(setName));

        //Import the .mse-set file to the new directory (simple copy)
        Path importPath = basePath.resolve(sourceFileName);
        Files.copy(source, importPath);

        //Unzip the contents of the newly imported .mse-set file.
        Path unzippedPath = basePath.resolve(fileName);
        ZipUtil.unzip(importPath, unzippedPath);

        SetManager setManager = new SetManager(setName, fileName);
        setManager.generateImages();

        return setManager;
    }

    public MseSet getSet() {
        return set;
    }
    public void setSet(MseSet set) {
        this.set = set;
    }

    public Path getDirectoryPath() {
        return JMSE_SET_DIRECTORY.resolve(setName);
    }

    public Path getUnzippedPath() {
        return getDirectoryPath().resolve(fileName);
    }

    public Path getSetPath() {
        return getUnzippedPath().resolve("set");
    }

    public Path getImportPath() {
        return getDirectoryPath().resolve(fileName + ".mse-set");
    }

    public Path getExportPath() {
        return getDirectoryPath().resolve(fileName + ".exported.mse-set");
    }

    public Path getImagesPath() {
        return getDirectoryPath().resolve("images");
    }

    public void exportSet() throws IOException {
        ZipUtil.zip(getUnzippedPath(), getExportPath());
    }

    public void generateImages() throws IOException {
        exportSet();
        File imagesDirectory = getImagesPath().toFile();
        FileUtils.deleteDirectory(imagesDirectory);
        if (!imagesDirectory.exists()) {
            if (!imagesDirectory.mkdirs()) {
                throw new IOException("Failed to create directory: " + imagesDirectory);
            }
        }

        try (MseCliProcess mse = new MseCliProcess()) {
            mse.load(getExportPath());
            int count = Integer.parseInt(mse.command("number_of_items(in: set.cards)").lines().get(0));
            for (int i = 0; i < count; i++) {
                String name = mse.command("set.cards[%s].name".formatted(i)).lines().get(0)
                        .replace("\"", "")
                        .replace(",", "")
                        .replace(" ", "_");
                mse.write_image_file("set.cards[%s]".formatted(i), getImagesPath().resolve(name + ".png"));
            }
            mse.quit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*============================== LOAD ==============================*/

    public MseSet load() throws IOException {
        List<String> lines = Files.readAllLines(getSetPath());
        return mapper.fromLines(lines);
    }
    /*============================== Write ==============================*/

    public void save() throws IOException {
        String setString = mapper.toString(set);
        FileUtils.writeStringToFile(getSetPath().toFile(), setString, Charset.defaultCharset());
    }

    /*============================== IMPORT/EXPORT ==============================*/

    private static Path createDirectory(Path basePath) throws IOException {
        File directory = basePath.toFile();
        if (overwriteExisting) {
            FileUtils.deleteDirectory(directory);
            if (!directory.mkdirs()) {
                throw new IllegalStateException("Couldn't create directory: " + directory);
            }
        } else if (directory.exists()) {
            throw new IllegalStateException("Directory already exists: " + directory);
        }
        return basePath;
    }

}

