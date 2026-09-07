package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.expr.ArraySubscriptExpr;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ADataRef;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ASectionSubscript;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AArraySubscriptExpr;

public class FortranArraySubscriptExpr<Self extends FortranArraySubscriptExpr<Self>> extends AArraySubscriptExpr<Self> {

    public FortranArraySubscriptExpr(ArraySubscriptExpr node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public ArraySubscriptExpr getNodeImpl() {
        return (ArraySubscriptExpr) super.getNodeImpl();
    }

    @Override
    public ASectionSubscript<?>[] getSubscriptsImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getSubscripts(), getWeaverEngine(), ASectionSubscript.class);
    }

    @Override
    public ADataRef<?> getVarImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getRef(), getWeaverEngine(), ADataRef.class);
    }
}
