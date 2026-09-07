package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.program.Application;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AProgram;

public class FortranProgram<Self extends FortranProgram<Self>> extends AProgram<Self> {

    public FortranProgram(Application node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public Application getNodeImpl() {
        return (Application) super.getNodeImpl();
    }
}
