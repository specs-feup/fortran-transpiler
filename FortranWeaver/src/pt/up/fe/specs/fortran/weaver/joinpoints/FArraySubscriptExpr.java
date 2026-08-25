package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.expr.dataref.ArrayElement;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AArraySubscriptExpr;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ADataRef;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExpr;

public class FArraySubscriptExpr extends AArraySubscriptExpr {

    private final ArrayElement arrayElement;

    public FArraySubscriptExpr(ArrayElement arrayElement) {
        super(new FDataRef(arrayElement));
        this.arrayElement = arrayElement;
    }

    @Override
    public AExpr[] getSubscriptsArrayImpl() {
        return arrayElement.getSubscripts()
                .stream()
                .map(FortranJoinpoints::create)
                .toList()
                .toArray(new AExpr[0]);
    }

    @Override
    public ADataRef getVarImpl() {
        return FortranJoinpoints.create(arrayElement.getRef(), ADataRef.class);
    }

    @Override
    public FortranNode getNode() {
        return arrayElement;
    }
}
