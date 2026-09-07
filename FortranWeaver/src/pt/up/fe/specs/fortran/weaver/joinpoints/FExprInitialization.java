package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.decl.ExprInitialization;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExpr;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExprInitialization;

public class FExprInitialization extends AExprInitialization {

    public final ExprInitialization exprInitialization;

    public FExprInitialization(ExprInitialization exprInitialization, FortranWeaver weaver) {
        super(new FInitialization(exprInitialization, weaver), weaver);
        this.exprInitialization = exprInitialization;
    }

    @Override
    public AExpr getExprImpl() {
        return FortranJoinpoints.create(exprInitialization.getExpr(), getWeaverEngine(), AExpr.class);
    }

    @Override
    public FortranNode getNode() {
        return exprInitialization;
    }
}
