package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.stmt.ifstmt.IfStmt;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AActionStatement;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExpr;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AIfStatement;

public class FIfStatement extends AIfStatement {

    public final IfStmt ifStmt;

    public FIfStatement(IfStmt ifStmt, FortranWeaver weaver) {
        super(new FActionStatement(ifStmt, weaver), weaver);
        this.ifStmt = ifStmt;
    }

    @Override
    public AExpr getConditionImpl() {
        return FortranJoinpoints.create(ifStmt.getCondition(), getWeaverEngine(), AExpr.class);
    }

    @Override
    public AActionStatement getStatementImpl() {
        return FortranJoinpoints.create(ifStmt.getThenAction(), getWeaverEngine(), AActionStatement.class);
    }

    @Override
    public FortranNode getNode() {
        return ifStmt;
    }
}
