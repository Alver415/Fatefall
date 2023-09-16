package com.other.fatefall.mse;

import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class SetManager {

    public static final String DEFAULT_CARD_BACK_FACE = Objects.requireNonNull(
            SetManager.class.getResource("magic_card_back.png")).toExternalForm();

    private static final Path JMSE_SET_DIRECTORY = Path.of(".wd/jmse_sets");
    private static final boolean overwriteExisting = true;

    private static final MseMapper mapper = new MseMapper();

    private final String setName;
    private final String fileName;
    private ObjectNode set;

    private SetManager(String setName, String fileName) throws IOException {
        this.setName = setName;
        this.fileName = fileName;
        this.set = load();
    }

    public static SetManager get(String setName) throws IOException {
        Path basePath = JMSE_SET_DIRECTORY.resolve(setName);
        //TODO: Just guesses based on the first filename in the directory...
        String fileName = setName.replace(".mse-set", "");
        return new SetManager(setName, fileName);
    }

    public static SetManager importMseSet(String setName, Path source) throws IOException {
        //Parse the file name of source file (everything before .mse-set)
        String sourceFileName = source.getFileName().toString();
        int index = sourceFileName.lastIndexOf(".mse-set");
        String fileName = sourceFileName.substring(0, index);

        //Create the directory to store everything in based on the given setName
        Path basePath = JMSE_SET_DIRECTORY.resolve(setName);
        if (Files.exists(basePath)){
            return get(setName);
        }
        createDirectory(basePath);

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

    public ObjectNode getSet() {
        return set;
    }

    public void setSet(ObjectNode set) {
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
        // FileUtils.deleteDirectory(imagesDirectory);
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
                String name_2 = mse.command("set.cards[%s].name_2".formatted(i)).lines().get(0);
                if (!"\"\"".equals(name_2)) {
                    splitImage(getImagesPath().resolve(name + ".png"));
                }
            }
            mse.quit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void splitImages() {
        try (Stream<Path> walk = Files.walk(getImagesPath())) {
            walk.filter(p -> p.toString().endsWith(".png"))
                    .forEach(SetManager::splitImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void splitImage(Path path) {
        try {
            String fileName = path.getFileName().toString();
            String baseFileName = fileName.substring(0, fileName.length() - 4);
            Image image = new Image(new FileInputStream(path.toFile()));
            SplitImage splitImage = new SplitImage(image);
            Image left = splitImage.getLeft();
            Image right = splitImage.getRight();
            ImageIO.write(SwingFXUtils.fromFXImage(left, null), "png",
                    path.resolveSibling(baseFileName + ".card_front.png").toFile());
            ImageIO.write(SwingFXUtils.fromFXImage(right, null), "png",
                    path.resolveSibling(baseFileName + ".card_back.png").toFile());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static class SplitImage {
        protected final Image image;

        protected SplitImage(Image image) {
            this.image = image;
        }

        protected Image getLeft() {
            return new WritableImage(
                    image.getPixelReader(),
                    0,
                    0,
                    (int) (image.getWidth() / 2),
                    (int) image.getHeight());
        }

        protected Image getRight() {
            return new WritableImage(
                    image.getPixelReader(),
                    (int) (image.getWidth() / 2),
                    0,
                    (int) (image.getWidth() / 2),
                    (int) image.getHeight());
        }
    }


    /*============================== LOAD ==============================*/

    public ObjectNode load() throws IOException {
        List<String> lines = Files.readAllLines(getSetPath());
        return mapper.toJson(lines);
    }
    /*============================== Write ==============================*/

    public void save() throws IOException {
        String setString = mapper.fromJson(set);
        Files.writeString(getSetPath(), setString);
    }

    /*============================== IMPORT/EXPORT ==============================*/

    private static Path createDirectory(Path directory) throws IOException {
        if (overwriteExisting) {
            deleteIfExists(directory);
            if (!directory.toFile().mkdirs()) {
                throw new IllegalStateException("Couldn't create directory: " + directory);
            }
        } else if (directory.toFile().exists()) {
            throw new IllegalStateException("Directory already exists: " + directory);
        }
        return directory;
    }

    private static void deleteIfExists(Path directory) throws IOException {
        if (!directory.toFile().exists()) {
            return;
        }
        try (Stream<Path> walk = Files.walk(directory)) {
            walk.forEach(path -> {
                try {
                    Files.deleteIfExists(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

