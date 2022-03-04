package mse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String... args) throws InterruptedException {

        String fileName = "generated.png";
        try(MseCliProcess mse = new MseCliProcess()){
            Files.deleteIfExists(Path.of(fileName));
            mse.load(Path.of("empty.mse-set"));
            Map<String, String> fieldMap = new HashMap<>();
            fieldMap.put("name", "Name");
            fieldMap.put("type", "Type");
            fieldMap.put("rule_text", "Oracle");
            fieldMap.put("power", "P");
            fieldMap.put("toughness", "T");
            fieldMap.put("casting_cost", "1WUBRG");

            mse.command("source := set.cards[0]");
            mse.new_card("my_card", fieldMap);
            mse.write_image_file("my_card", fileName);
            mse.quit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
