package pt.up.fe.specs.fortran.ast.nodes.stmt.pointerassign;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DoubleBoundPointerAssignStmt extends PointerAssignStmt {
    public DoubleBoundPointerAssignStmt(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    public List<DoubleBound> getBounds() {
        return getChildrenOf(DoubleBound.class);
    }

    @Override
    public String getStmtCode() {
        var objectCode = getObject().getCode();
        var boundsCode = getBounds().stream()
                .map(DoubleBound::getCode)
                .collect(Collectors.joining(", ", "(", ")"));
        var targetCode = getTarget().getCode();

        return objectCode + boundsCode + " => " + targetCode;
    }
}
