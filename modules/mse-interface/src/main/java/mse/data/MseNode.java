package mse.data;

import java.util.LinkedHashMap;
import java.util.Map;

public class MseNode<V> {
    public final Map<String, V> fields = new LinkedHashMap<>();
}
