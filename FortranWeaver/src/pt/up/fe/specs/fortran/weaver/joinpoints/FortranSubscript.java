package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.expr.Subscript;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExpr;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ASubscript;

public class FortranSubscript<Self extends FortranSubscript<Self>> extends ASubscript<Self> {

    public FortranSubscript(Subscript node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public Subscript getNodeImpl() {
        return (Subscript) super.getNodeImpl();
    }

    @Override
    public AExpr<?> getExprImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getValue(), getWeaverEngine(), AExpr.class);
    }
}
