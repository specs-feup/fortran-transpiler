package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.program.StmtBlock;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AStatement;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AStatementBlock;

public class FortranStatementBlock<Self extends FortranStatementBlock<Self>> extends AStatementBlock<Self> {

    public FortranStatementBlock(StmtBlock node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public StmtBlock getNodeImpl() {
        return (StmtBlock) super.getNodeImpl();
    }

    @Override
    public AStatement<?>[] getStmtsImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getStatements(), getWeaverEngine(), AStatement.class);
    }
}
