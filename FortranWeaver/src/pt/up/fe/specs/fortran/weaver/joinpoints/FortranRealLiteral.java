package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.expr.RealLiteral;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ARealLiteral;

public class FortranRealLiteral<Self extends FortranRealLiteral<Self>> extends ARealLiteral<Self> {

    public FortranRealLiteral(RealLiteral node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public RealLiteral getNodeImpl() {
        return (RealLiteral) super.getNodeImpl();
    }
}
