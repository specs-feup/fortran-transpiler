package pt.up.fe.specs.fortran.ast.nodes.stmt.pointerassign;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.expr.Expr;
import pt.up.fe.specs.util.SpecsCollections;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SingleBoundPointerAssignStmt extends PointerAssignStmt {
    public SingleBoundPointerAssignStmt(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    public List<Expr> getBounds() {
        return SpecsCollections.cast(getChildren().subList(1, getNumChildren() - 1), Expr.class);
    }

    @Override
    public String getStmtCode() {
        var objectCode = getObject().getCode();
        var targetCode = getTarget().getCode();

        var bounds = getBounds();
        var boundsCode = bounds.isEmpty() ? ""
                : bounds.stream()
                .map(bound -> bound.getCode() + ":")
                .collect(Collectors.joining(", ", "(", ")"));

        return objectCode + boundsCode + " => " + targetCode;
    }
}
