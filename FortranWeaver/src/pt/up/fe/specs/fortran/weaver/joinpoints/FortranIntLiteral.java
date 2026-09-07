package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.expr.IntLiteral;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AIntLiteral;

public class FortranIntLiteral<Self extends FortranIntLiteral<Self>> extends AIntLiteral<Self> {

    public FortranIntLiteral(IntLiteral node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public IntLiteral getNodeImpl() {
        return (IntLiteral) super.getNodeImpl();
    }
}
