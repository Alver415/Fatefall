package engine;

import java.util.HashMap;
import java.util.Map;

public class Scope {

    private final Scope parent;
    private final Map<String, Value> variables = new HashMap<>();

    public Scope(Scope parent) {
        this.parent = parent;
    }

    public Value resolve(String identifier) {
        if (variables.containsKey(identifier)) {
            return variables.get(identifier);
        } else if (parent != null) {
            return parent.resolve(identifier);
        } else {
            throw new RuntimeException("Failed to resolve identifier: " + identifier);
        }
    }

    public void assign(String identifier, Value value) {
        if (variables.containsKey(identifier)) {
            variables.put(identifier, value);
        } else if (parent != null) {
            parent.assign(identifier, value);
        } else {
            throw new RuntimeException("Failed to assign identifier: " + identifier);
        }
    }

    public void declare(String identifier) {
        if (variables.containsKey(identifier)) {
            throw new RuntimeException("Failed to declare identifiier: " + identifier);
        } else {
            variables.put(identifier, null);
        }
    }
}
