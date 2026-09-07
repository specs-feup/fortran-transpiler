package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.stmt.ifstmt.ElseIfBlock;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AElseIfBlock;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AElseIfStatement;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AStatementBlock;

public class FElseIfBlock extends AElseIfBlock {

    public final ElseIfBlock elseIfBlock;

    public FElseIfBlock(ElseIfBlock elseIfBlock, FortranWeaver weaver) {
        super(weaver);
        this.elseIfBlock = elseIfBlock;
    }

    @Override
    public AStatementBlock getBodyImpl() {
        return FortranJoinpoints.create(elseIfBlock.getBlock(), getWeaverEngine(), AStatementBlock.class);
    }

    @Override
    public AElseIfStatement getHeaderImpl() {
        return FortranJoinpoints.create(elseIfBlock.getElseIfStmt(), getWeaverEngine(), AElseIfStatement.class);
    }

    @Override
    public FortranNode getNode() {
        return elseIfBlock;
    }
}
