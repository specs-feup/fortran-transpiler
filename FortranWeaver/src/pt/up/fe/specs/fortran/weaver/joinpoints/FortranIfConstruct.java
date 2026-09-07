package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.stmt.ifstmt.IfConstruct;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AElseBlock;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AElseIfBlock;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AIfThenBlock;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AIfConstruct;

public class FortranIfConstruct<Self extends FortranIfConstruct<Self>> extends AIfConstruct<Self> {

    public FortranIfConstruct(IfConstruct node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public IfConstruct getNodeImpl() {
        return (IfConstruct) super.getNodeImpl();
    }

    @Override
    public AIfThenBlock<?> getIfThenBlockImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getIfThenBlock(), getWeaverEngine(), AIfThenBlock.class);
    }

    @Override
    public AElseIfBlock<?>[] getElseIfBlocksImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getElseIfBlocks(), getWeaverEngine(), AElseIfBlock.class);
    }

    @Override
    public AElseBlock<?> getElseBlockImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getElseBlock().get(), getWeaverEngine(), AElseBlock.class);
    }
}
