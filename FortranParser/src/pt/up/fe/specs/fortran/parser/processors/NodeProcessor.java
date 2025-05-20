package pt.up.fe.specs.fortran.parser.processors;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.parser.FlangToClass;
import pt.up.fe.specs.fortran.parser.FortranJsonResult;
import pt.up.fe.specs.util.SpecsCheck;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface NodeProcessor {

    FortranJsonResult data();

    default FortranNode getChild(String id) {

        var currentId = id;
        // Get corresponding attributes
        var attrs = getAttrs(currentId);

        while (!FlangToClass.isClass(getType(attrs))) {
            // Assumes there is a value
            currentId = getString(attrs, "value");
            attrs = getAttrs(currentId);
        }

        return getNode(currentId);
    }

    default FortranNode getNode(String id) {
        var node = data().fortranNodes().get(id);
        SpecsCheck.checkNotNull(node, () -> "Could not find a FortranNode for id '" + id + "'");
        return node;
    }

    default <T> List<T> getList(Map<String, Object> attrs, String key, Function<Object, T> converter) {
        var list = (List<Object>) attrs.get(key);
        return list.stream().map(obj -> converter.apply(obj)).toList();
    }

    default Map<String, Object> getAttrs(String id) {
        return data().attributes().get(id);
    }

    default String getType(Map<String, Object> attrs) {
        return getString(attrs, "type");
    }

    default String getId(Map<String, Object> attrs) {
        return getString(attrs, "id");
    }

    default String getString(Map<String, Object> attrs, String key) {
        var value = attrs.get(key);
        SpecsCheck.checkNotNull(value, () -> "No attribute '" + key + "': " + attrs);
        return value.toString();
    }
}
