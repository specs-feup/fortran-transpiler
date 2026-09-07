package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.stmt.ifstmt.IfThenBlock;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AIfThenStatement;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AStatementBlock;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AIfThenBlock;

public class FortranIfThenBlock<Self extends FortranIfThenBlock<Self>> extends AIfThenBlock<Self> {

    public FortranIfThenBlock(IfThenBlock node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public IfThenBlock getNodeImpl() {
        return (IfThenBlock) super.getNodeImpl();
    }

    @Override
    public AIfThenStatement<?> getHeaderImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getIfThenStmt(), getWeaverEngine(), AIfThenStatement.class);
    }

    @Override
    public AStatementBlock<?> getBodyImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getBlock(), getWeaverEngine(), AStatementBlock.class);
    }
}
