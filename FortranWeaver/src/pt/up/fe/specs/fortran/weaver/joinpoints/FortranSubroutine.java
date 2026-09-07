package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.program.Subroutine;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ASubroutine;

public class FortranSubroutine<Self extends FortranSubroutine<Self>> extends ASubroutine<Self> {

    public FortranSubroutine(Subroutine node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public Subroutine getNodeImpl() {
        return (Subroutine) super.getNodeImpl();
    }

    @Override
    public String getModuleNameImpl() {
        return this.getNodeImpl().getName();
    }
}
