package pt.up.fe.specs.fortran.ast.nodes.stmt.pointerassign;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.expr.Expr;

import java.util.Collection;

public class DoubleBound extends FortranNode {
    public DoubleBound(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    public Expr getLowerBound() {
        return getChild(Expr.class, 0);
    }

    public Expr getUpperBound() {
        return getChild(Expr.class, 1);
    }

    @Override
    public String getCode() {
        return getLowerBound().getCode() + ":" + getUpperBound().getCode();
    }
}
