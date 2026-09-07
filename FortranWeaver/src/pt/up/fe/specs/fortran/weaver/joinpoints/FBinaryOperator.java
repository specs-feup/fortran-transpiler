package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.expr.BinaryOperator;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ABinaryOperator;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExpr;

public class FBinaryOperator extends ABinaryOperator {

    private final BinaryOperator binaryOperator;

    public FBinaryOperator(BinaryOperator binaryOperator, FortranWeaver weaver) {
        super(new FExpr(binaryOperator, weaver), weaver);
        this.binaryOperator = binaryOperator;
    }

    @Override
    public String getKindImpl() {
        return binaryOperator.getOp().toString();
    }

    @Override
    public AExpr getLeftImpl() {
        return FortranJoinpoints.create(binaryOperator.getLhs(), getWeaverEngine(), AExpr.class);
    }

    @Override
    public AExpr getRightImpl() {
        return FortranJoinpoints.create(binaryOperator.getRhs(), getWeaverEngine(), AExpr.class);
    }

    @Override
    public FortranNode getNode() {
        return binaryOperator;
    }
}
