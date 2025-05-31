package pt.up.fe.specs.fortran.parser.processors;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.parser.*;
import pt.up.fe.specs.util.SpecsCheck;
import pt.up.fe.specs.util.providers.StringProvider;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface NodeProcessor {

    FortranJsonResult data();

    default FortranNode getChild(String id) {

        var currentId = id;
        // Get corresponding attributes
        var attrs = getAttrs(currentId);

        while (!FlangToClass.isClass(getKind(currentId))) {
            // Assumes there is a value
            currentId = attrs.getString("value");
            attrs = getAttrs(currentId);
        }

        return getNode(currentId);
    }

    default FortranNode getNode(String id) {
        var node = data().fortranNodes().get(id);
        SpecsCheck.checkNotNull(node, () -> "Could not find a FortranNode for id '" + id + "'");
        return node;
    }



    default FlangAttributes getAttrs(String id) {
        var attrs = data().attributes().get(id);
        SpecsCheck.checkNotNull(attrs, () -> "Id '" + id + "' does not have attributes associated: " + data().attributes());
        return attrs;
    }
/*
    default String getId(Map<String, Object> attrs) {
        return getString(attrs, "id");
    }
*/
    /*
    default String getString(Map<String, Object> attrs, String key) {
        var value = attrs.get(key);
        SpecsCheck.checkNotNull(value, () -> "No attribute '" + key + "': " + attrs);
        return value.toString();
    }
*/
    default String getKind(String id) {
        return FortranJsonParser.getKind(id);
    }


    default boolean hasStmt(Map<String, Object> attrs, FlangName flangName) {
        var stmtName = getStmtAttr(flangName);
        return attrs.containsKey(stmtName);
    }

    default String getStmtAttr(FlangName flangName) {
        return "Statement<" + flangName.getString() + ">";
    }

}
