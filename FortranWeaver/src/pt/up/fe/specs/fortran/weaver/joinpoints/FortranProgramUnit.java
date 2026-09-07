package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.program.ProgramUnit;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ASpecification;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AProgramUnit;

public class FortranProgramUnit<Self extends FortranProgramUnit<Self>> extends AProgramUnit<Self> {

    public FortranProgramUnit(ProgramUnit node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public ProgramUnit getNodeImpl() {
        return (ProgramUnit) super.getNodeImpl();
    }

    @Override
    public ASpecification<?> getSpecificationImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getSpecification(), getWeaverEngine(), ASpecification.class);
    }
}
