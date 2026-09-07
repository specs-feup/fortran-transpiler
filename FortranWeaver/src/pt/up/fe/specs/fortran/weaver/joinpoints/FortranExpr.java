package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.expr.Expr;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExpr;

public class FortranExpr<Self extends FortranExpr<Self>> extends AExpr<Self> {

    public FortranExpr(Expr node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public Expr getNodeImpl() {
        return (Expr) super.getNodeImpl();
    }
}
