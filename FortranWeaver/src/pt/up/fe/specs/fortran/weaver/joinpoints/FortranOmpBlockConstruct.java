package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.omp.OmpBlockConstruct;
import pt.up.fe.specs.fortran.ast.nodes.program.Execution;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExecution;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AOmpBlockConstruct;

public class FortranOmpBlockConstruct<Self extends FortranOmpBlockConstruct<Self>> extends AOmpBlockConstruct<Self> {

    public FortranOmpBlockConstruct(OmpBlockConstruct node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public OmpBlockConstruct getNodeImpl() {
        return (OmpBlockConstruct) super.getNodeImpl();
    }

    @Override
    public void setBodyImpl(AExecution<?> body) {
        this.getNodeImpl().setBody((Execution) body.getNodeImpl());
    }
}
