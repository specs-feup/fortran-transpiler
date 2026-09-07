package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.expr.BinaryOperator;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExpr;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ABinaryOperator;

public class FortranBinaryOperator<Self extends FortranBinaryOperator<Self>> extends ABinaryOperator<Self> {

    public FortranBinaryOperator(BinaryOperator node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public BinaryOperator getNodeImpl() {
        return (BinaryOperator) super.getNodeImpl();
    }

    @Override
    public pt.up.fe.specs.fortran.weaver.enums.BinaryOperatorKind getKindImpl() {
        return pt.up.fe.specs.fortran.weaver.enums.BinaryOperatorKind.valueOf(this.getNodeImpl().getOp().name());
    }

    @Override
    public AExpr<?> getLeftImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getLhs(), getWeaverEngine(), AExpr.class);
    }

    @Override
    public AExpr<?> getRightImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getRhs(), getWeaverEngine(), AExpr.class);
    }

    @Override
    public void setLeftImpl(AExpr<?> lhs) {
        throw new UnsupportedOperationException(get_class() + ": Action setLeft not implemented ");
    }

    @Override
    public void setRightImpl(AExpr<?> rhs) {
        throw new UnsupportedOperationException(get_class() + ": Action setRight not implemented ");
    }
}
