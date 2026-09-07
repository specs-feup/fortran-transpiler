package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.expr.RealLiteral;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ARealLiteral;

public class FRealLiteral extends ARealLiteral {

    private final RealLiteral realLiteral;

    public FRealLiteral(RealLiteral realLiteral, FortranWeaver weaver) {
        super(new FLiteral(realLiteral, weaver), weaver);
        this.realLiteral = realLiteral;
    }

    @Override
    public FortranNode getNode() {
        return realLiteral;
    }
}
