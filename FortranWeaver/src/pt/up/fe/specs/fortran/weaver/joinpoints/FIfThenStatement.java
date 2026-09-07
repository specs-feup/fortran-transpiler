package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.stmt.ifstmt.IfThenStmt;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExpr;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AIfThenStatement;

public class FIfThenStatement extends AIfThenStatement {

    public final IfThenStmt ifThenStmt;

    public FIfThenStatement(IfThenStmt ifThenStmt, FortranWeaver weaver) {
        super(weaver);
        this.ifThenStmt = ifThenStmt;
    }

    @Override
    public AExpr getConditionImpl() {
        return FortranJoinpoints.create(ifThenStmt.getCondition(), getWeaverEngine(), AExpr.class);
    }

    @Override
    public FortranNode getNode() {
        return ifThenStmt;
    }
}
