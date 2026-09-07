package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.expr.ArraySubscriptExpr;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.*;

public class FArraySubscriptExpr extends AArraySubscriptExpr {

    private final ArraySubscriptExpr arraySubscriptExpr;

    public FArraySubscriptExpr(ArraySubscriptExpr arraySubscriptExpr, FortranWeaver weaver) {
        super(new FDataRef(arraySubscriptExpr, weaver), weaver);
        this.arraySubscriptExpr = arraySubscriptExpr;
    }

    @Override
    public ASectionSubscript[] getSubscriptsArrayImpl() {
        return arraySubscriptExpr.getSubscripts()
                .stream()
                .map(node -> FortranJoinpoints.create(node, getWeaverEngine()))
                .toList()
                .toArray(new ASectionSubscript[0]);
    }

    @Override
    public ADataRef getVarImpl() {
        return FortranJoinpoints.create(arraySubscriptExpr.getRef(), getWeaverEngine(), ADataRef.class);
    }

    @Override
    public FortranNode getNode() {
        return arraySubscriptExpr;
    }
}
