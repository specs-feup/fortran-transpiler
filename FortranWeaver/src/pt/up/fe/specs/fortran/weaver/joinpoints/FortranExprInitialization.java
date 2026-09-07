package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.decl.ExprInitialization;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExpr;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExprInitialization;

public class FortranExprInitialization<Self extends FortranExprInitialization<Self>> extends AExprInitialization<Self> {

    public FortranExprInitialization(ExprInitialization node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public ExprInitialization getNodeImpl() {
        return (ExprInitialization) super.getNodeImpl();
    }

    @Override
    public AExpr<?> getExprImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getExpr(), getWeaverEngine(), AExpr.class);
    }
}
