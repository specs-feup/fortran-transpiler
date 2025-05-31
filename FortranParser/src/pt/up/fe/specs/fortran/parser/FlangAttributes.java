package pt.up.fe.specs.fortran.parser;

import pt.up.fe.specs.util.SpecsCheck;
import pt.up.fe.specs.util.providers.StringProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class FlangAttributes {

    public static Map<String, FlangAttributes> convert(Map<String, Map<String, Object>> data) {
        var newAttrs = new HashMap<String, FlangAttributes>();

        for (var entry : data.entrySet()) {
            newAttrs.put(entry.getKey(), new FlangAttributes(entry.getValue()));
        }

        return newAttrs;
    }

    private final Map<String, Object> attributes;

    public FlangAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return attributes.toString();
    }

    /*
        public Map<String, Object> getAttributes() {
            return attributes;
        }
    */
    public String getString(String key) {
        var value = attributes.get(key);
        SpecsCheck.checkNotNull(value, () -> "No attribute '" + key + "': " + attributes);
        return value.toString();
    }

    public List<String> getStringList(StringProvider key) {
        return getList(key, Object::toString);
    }


    public <T> List<T> getList(StringProvider key, Function<Object, T> converter) {
        return getList(key.getString(), converter);
    }

    public <T> List<T> getList(String key, Function<Object, T> converter) {
        var list = (List<Object>) attributes.get(key);
        SpecsCheck.checkNotNull(list, () -> "Attrs do not have a value for key '" + key + "': " + attributes);
        return list.stream().map(obj -> converter.apply(obj)).toList();
    }
}
