package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.program.MainProgram;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AMainProgram;

public class FortranMainProgram<Self extends FortranMainProgram<Self>> extends AMainProgram<Self> {

    public FortranMainProgram(MainProgram node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public MainProgram getNodeImpl() {
        return (MainProgram) super.getNodeImpl();
    }
}
