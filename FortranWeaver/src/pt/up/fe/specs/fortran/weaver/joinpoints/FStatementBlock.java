package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.program.StmtBlock;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExpr;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AStatement;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AStatementBlock;

public class FStatementBlock extends AStatementBlock {

    private final StmtBlock stmtBlock;

    public FStatementBlock(StmtBlock stmtBlock, FortranWeaver weaver) {
        super(weaver);
        this.stmtBlock = stmtBlock;
    }

    @Override
    public AStatement[] getStmtsArrayImpl() {
        return (AStatement[]) stmtBlock.getStatements()
                .stream()
                .map(node -> FortranJoinpoints.create(node, getWeaverEngine()))
                .toArray();
    }

    @Override
    public FortranNode getNode() {
        return stmtBlock;
    }
}
