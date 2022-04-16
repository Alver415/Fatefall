package plugin;

import com.fasterxml.jackson.databind.JsonNode;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.File;
import java.io.IOException;

public class ScriptPlugin {

    public static String convert(JsonNode json, String script) throws IOException {
        return convert(json.toPrettyString(), script);
    }
    public static String convert(String json, String script) throws IOException {
        String language = "js";
        Context context = Context.newBuilder()
                .engine(Engine.newBuilder(language).build())
                .build();

        context.getBindings(language).putMember("input", json);
        return execute(script, language, context);
    }

    private static String execute(String pathname, String language, Context context) throws IOException {
        File file = new File(pathname);
        Source source = Source.newBuilder(language, file).build();
        Value eval = context.eval(source);
        System.out.println(eval);
        return eval.asString();
    }
}