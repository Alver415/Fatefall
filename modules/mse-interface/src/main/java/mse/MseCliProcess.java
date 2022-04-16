package mse;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MseCliProcess implements Closeable {

    private static final String NEW_LINE = System.lineSeparator();
    private final Process process;

    public static void main(String... args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        try(MseCliProcess process = new MseCliProcess()){
            String input;
            while (scanner.hasNext()){
                input = scanner.next();
                process.command(input);
            }
        }
    }

    public MseCliProcess() throws IOException {
        /* Requires that 'mse.exe' be on path */
        this(Path.of("mse"));
    }

    public MseCliProcess(Path mseExe) throws IOException {
        String msePath = mseExe.toString();
        List<String> startCommand = List.of(msePath, "--cli", "--raw");
        System.out.println(String.join(" ", startCommand));
        process = new ProcessBuilder(startCommand)
                .redirectError(ProcessBuilder.Redirect.INHERIT)
                .redirectErrorStream(true)
                .start();
    }

    public Response help() throws IOException {
        return command(":help");
    }

    public Response quit() throws IOException {
        return command(":quit");
    }

    public Response load(Path set) throws IOException {
        return load(set.toString());
    }

    public Response load(String set) throws IOException {
        return command(":load " + set);
    }

    public Response reset() throws IOException {
        return command(":reset");
    }

    public Response cd(Path directory) throws IOException {
        return cd(directory.toString());
    }

    public Response cd(String directory) throws IOException {
        return command(":cd " + directory);
    }

    public Response pwd() throws IOException {
        return command(":pwd");
    }

    public Response write_image_file(String input, Path file) throws IOException {
        return write_image_file(input, file.toString());
    }

    public Response write_image_file(String input, String file) throws IOException {
        return command(String.format(
                "write_image_file(input:%s, file:\"%s\")",
                input, file));
    }

    public Response write_image_file(String input, Path file, int width, int height) throws IOException {
        return write_image_file(input, file.toString(), width, height);
    }

    public Response write_image_file(String input, String file, int width, int height) throws IOException {
        return command(String.format(
                "write_image_file(input:%s, file:\"%s\", width:%d, height:%d)",
                input, file, width, height));
    }

    public Response new_card(String variable, Map<String, String> fieldMap) throws IOException {
        String fields = fieldMap.entrySet().stream()
                .filter(e -> e.getValue() != null)
                .map(e -> String.format("%s:\"%s\"", e.getKey(), e.getValue()))
                .collect(Collectors.joining(",", "[", "]"));
        return command(String.format("%s := new_card(%s)", variable, fields));
    }

    /**
     * Synchronized blocking command to MSE process
     */
    public synchronized Response command(String command) throws IOException {
        System.out.println(command);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        writer.write(escape(command));
        writer.write(NEW_LINE);
        writer.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String firstLine = reader.readLine();
        try {
            int status = Integer.parseInt(firstLine);
            int lineCount = Integer.parseInt(reader.readLine());
            String line;
            int i = 0;
            List<String> lines = new ArrayList<>(lineCount);
            while (i++ < lineCount && (line = reader.readLine()) != null) {
                lines.add(line);
            }
            Response response = new Response(status, lineCount, List.copyOf(lines));
            System.out.println(response);
            return response;
        } catch (Exception e){
            System.err.println(firstLine);
            String line;
            while (reader.ready() && (line = reader.readLine()) != null) {
                System.err.println(line);
            }
            throw e;
        }
    }

    private String escape(String command) {
        return command
                .replace("\\", "/")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t")
                .replace("\b", "\\b")
                .replace("\f", "\\f")
                .replace("{", "\\{")
                .replace("}", "\\}")
                ;
    }

    @Override
    public void close() {
        process.destroy();
    }

    public record Response(int status, int lineCount, List<String> lines) {

        @Override
        public String toString() {
            return status + NEW_LINE +
                    lineCount + NEW_LINE +
                    String.join(NEW_LINE, lines);
        }
    }
}
