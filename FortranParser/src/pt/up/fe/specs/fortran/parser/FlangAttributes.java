package pt.up.fe.specs.fortran.parser;

import pt.up.fe.specs.util.SpecsCheck;
import pt.up.fe.specs.util.providers.StringProvider;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;

public class FlangAttributes {

    private final Map<String, Object> attributes;

    public FlangAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return attributes.toString();
    }

    public Set<String> getKeys() {
        return attributes.keySet();
    }

    /*
        public Map<String, Object> getAttributes() {
            return attributes;
        }
    */
    public Optional<String> getOptionalString(String key) {
        var value = attributes.get(key);

        if (value == null) {
            return Optional.empty();
        }

        return Optional.of(value.toString());
    }

    public Optional<String> getOptionalString(Pattern pattern) {

        // Go over all keys in attributes, check which ones match the pattern

        var matches = attributes.keySet().stream()
                .filter(key -> pattern.matcher(key).matches())
                .toList();

        if (matches.size() > 1) {
            throw new RuntimeException("Given pattern '" + pattern + "' matches more than one attribute: " + attributes);
        }

        if (matches.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(attributes.get(matches.get(0)).toString());
    }


    public String getString(StringProvider key) {
        return getString(key.getString());
    }

    public String getString(String key) {
        return getOptionalString(key).orElseThrow(() -> new RuntimeException("No attribute '" + key + "': " + attributes));
    }

    public String getString(Pattern key) {
        return getOptionalString(key).orElseThrow(() -> new RuntimeException("No attribute that matches regex '" + key + "': " + attributes));
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

    public Optional<Object> getOptional(String key) {
        var value = attributes.get(key);
        return Optional.ofNullable(value.toString());
    }
}
