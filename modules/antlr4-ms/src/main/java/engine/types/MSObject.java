package engine.types;

import java.util.HashMap;
import java.util.Map;

public class MSObject extends MSValue{

    private final Map<String, MSValue> fields = new HashMap<>();

    public MSValue put(String key, MSValue value){
        fields.put(key, value);
        return value;
    }

    public MSValue get(String key){
        return fields.get(key);
    }

    @Override
    public String toString(){
        return fields.toString();
    }
}
