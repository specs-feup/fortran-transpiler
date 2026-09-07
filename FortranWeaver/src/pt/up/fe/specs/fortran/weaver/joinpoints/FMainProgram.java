package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.program.MainProgram;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AMainProgram;

public class FMainProgram extends AMainProgram {

    public final MainProgram mainProgram;

    public FMainProgram(MainProgram mainProgram, FortranWeaver weaver) {
        super(new FProgramUnit(mainProgram, weaver), weaver);
        this.mainProgram = mainProgram;
    }

    @Override
    public FortranNode getNode() {
        return null;
    }
}
