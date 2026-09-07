package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.expr.StringLiteral;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AStringLiteral;

public class FortranStringLiteral<Self extends FortranStringLiteral<Self>> extends AStringLiteral<Self> {

    public FortranStringLiteral(StringLiteral node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public StringLiteral getNodeImpl() {
        return (StringLiteral) super.getNodeImpl();
    }
}
