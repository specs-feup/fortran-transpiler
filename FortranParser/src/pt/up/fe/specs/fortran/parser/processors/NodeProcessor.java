package pt.up.fe.specs.fortran.parser.processors;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.parser.FlangAttributes;
import pt.up.fe.specs.fortran.parser.FlangData;
import pt.up.fe.specs.fortran.parser.FlangName;
import pt.up.fe.specs.fortran.parser.FortranJsonResult;
import pt.up.fe.specs.util.SpecsCheck;

import java.util.List;
import java.util.regex.Pattern;

public interface NodeProcessor {

    FortranJsonResult data();

    default FortranNode getChild(String id) {
        return getNode(data().attributes().getChildId(id));
    }

    default FortranNode getNode(String id) {
        var node = data().fortranNodes().get(id);
        SpecsCheck.checkNotNull(node, () -> "Could not find a FortranNode for id '" + id + "'");
        return node;
    }


    default FortranNode getChild(FortranNode node, FlangName attribute) {
        return getNode(attributes().getChildId(node, attribute));
    }

    default FortranNode getChild(FortranNode node, String attribute) {
        return getNode(attributes().getChildId(node, attribute));
    }

    default FortranNode getChild(FortranNode node, Pattern attribute) {
        return getNode(attributes().getChildId(node, attribute));
    }

    default List<FortranNode> getChildren(FortranNode node, FlangName attribute) {
        return attributes().getChildrenIds(node, attribute).stream()
                .map(this::getNode)
                .toList();
    }


    /*
        default FlangAttributes getAttrs(FortranNode node) {
            return getAttrs(node.get(FortranNode.ID));
        }

        default FlangAttributes getAttrs(String id) {
            var attrs = data().attributes().get(id);
            SpecsCheck.checkNotNull(attrs, () -> "Id '" + id + "' does not have attributes associated: " + data().attributes());
            return attrs;
        }
    */
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
    /*
    default String getKind(String id) {
        return FortranJsonParser.getKind(id);
    }

*/


    default FlangData attributes() {
        return data().attributes();
    }

    default FlangAttributes attributes(FortranNode node) {
        return data().attributes().getAttrs(node);
    }


}
