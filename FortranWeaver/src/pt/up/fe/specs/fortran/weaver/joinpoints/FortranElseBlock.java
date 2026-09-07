package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.stmt.ifstmt.ElseBlock;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AStatementBlock;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AElseBlock;

public class FortranElseBlock<Self extends FortranElseBlock<Self>> extends AElseBlock<Self> {

    public FortranElseBlock(ElseBlock node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public ElseBlock getNodeImpl() {
        return (ElseBlock) super.getNodeImpl();
    }

    @Override
    public AStatementBlock<?> getBodyImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getBlock(), getWeaverEngine(), AStatementBlock.class);
    }
}
