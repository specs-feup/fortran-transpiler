package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.expr.dataref.ArrayElement;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AArrayElement;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ADataRef;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExpr;

public class FArrayElement extends AArrayElement {

    private final ArrayElement arrayElement;

    public FArrayElement(ArrayElement arrayElement) {
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
    public ADataRef getArrayImpl() {
        return FortranJoinpoints.create(arrayElement.getArray(), ADataRef.class);
    }

    @Override
    public FortranNode getNode() {
        return arrayElement;
    }
}
