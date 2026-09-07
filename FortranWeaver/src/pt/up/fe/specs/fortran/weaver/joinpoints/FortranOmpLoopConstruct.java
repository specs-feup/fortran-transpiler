package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.omp.OmpLoopConstruct;
import pt.up.fe.specs.fortran.ast.nodes.stmt.loop.DoConstruct;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ADoStatement;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AOmpLoopConstruct;

public class FortranOmpLoopConstruct<Self extends FortranOmpLoopConstruct<Self>> extends AOmpLoopConstruct<Self> {

    public FortranOmpLoopConstruct(OmpLoopConstruct node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public OmpLoopConstruct getNodeImpl() {
        return (OmpLoopConstruct) super.getNodeImpl();
    }

    @Override
    public void setLoopImpl(ADoStatement<?> loop) {
        this.getNodeImpl().setLoop((DoConstruct) loop.getNodeImpl());
    }
}
