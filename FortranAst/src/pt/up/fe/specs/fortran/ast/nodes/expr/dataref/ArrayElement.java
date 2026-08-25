package pt.up.fe.specs.fortran.ast.nodes.expr.dataref;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.expr.SectionSubscript;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayElement extends DataRef {
    public ArrayElement(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    public DataRef getArray() {
        return getChild(DataRef.class, 0);
    }

    public List<SectionSubscript> getSubscripts() {
        return getChildren(SectionSubscript.class, 1);
    }

    @Override
    public String getCode() {
        var arrayCode = getArray().getCode();
        var subscriptsCode = getSubscripts().stream()
                .map(SectionSubscript::getCode)
                .collect(Collectors.joining(", ", "(", ")"));

        return arrayCode + subscriptsCode;
    }
}
