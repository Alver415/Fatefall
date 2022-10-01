package engine;

public class Value {

    private final Object value;

    public Value(Object value){
        this.value = value;
    }

    public String asString(){
        return String.valueOf(value);
    }
    public boolean asBoolean(){
        return value instanceof Boolean ? (Boolean) value : Boolean.valueOf(asString());

    }

    @Override
    public String toString(){
        return asString();
    }
}
