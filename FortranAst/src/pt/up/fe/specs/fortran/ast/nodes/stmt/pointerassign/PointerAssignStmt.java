package pt.up.fe.specs.fortran.ast.nodes.stmt.pointerassign;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.expr.Expr;
import pt.up.fe.specs.fortran.ast.nodes.expr.dataref.DataRef;
import pt.up.fe.specs.fortran.ast.nodes.stmt.ActionStmt;

import java.util.Collection;

public abstract class PointerAssignStmt extends ActionStmt {
    public PointerAssignStmt(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    public DataRef getObject() {
        return getChild(DataRef.class, 0);
    }

    public Expr getTarget() {
        return getChild(Expr.class, getNumChildren() - 1);
    }
}
