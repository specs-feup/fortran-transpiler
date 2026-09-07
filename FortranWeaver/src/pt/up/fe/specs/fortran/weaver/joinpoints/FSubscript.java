package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.expr.Subscript;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExpr;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ASubscript;

public class FSubscript extends ASubscript {

    private final Subscript subscript;

    public FSubscript(Subscript subscript, FortranWeaver weaver) {
        super(new FSectionSubscript(subscript, weaver), weaver);
        this.subscript = subscript;
    }

    @Override
    public AExpr getExprImpl() {
        return FortranJoinpoints.create(subscript.getValue(), getWeaverEngine(), AExpr.class);
    }

    @Override
    public FortranNode getNode() {
        return subscript;
    }
}
