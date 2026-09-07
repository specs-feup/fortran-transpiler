package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.stmt.ifstmt.ElseIfBlock;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AElseIfStatement;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AStatementBlock;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AElseIfBlock;

public class FortranElseIfBlock<Self extends FortranElseIfBlock<Self>> extends AElseIfBlock<Self> {

    public FortranElseIfBlock(ElseIfBlock node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public ElseIfBlock getNodeImpl() {
        return (ElseIfBlock) super.getNodeImpl();
    }

    @Override
    public AElseIfStatement<?> getHeaderImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getElseIfStmt(), getWeaverEngine(), AElseIfStatement.class);
    }

    @Override
    public AStatementBlock<?> getBodyImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getBlock(), getWeaverEngine(), AStatementBlock.class);
    }
}
