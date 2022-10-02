package engine.types;

public class MSString extends MSValue{

    private final String string;

    public MSString(String string){
        this.string = string;
    }

    public String getString(){
        return string;
    }

    @Override
    public String toString(){
        return string;
    }
}
