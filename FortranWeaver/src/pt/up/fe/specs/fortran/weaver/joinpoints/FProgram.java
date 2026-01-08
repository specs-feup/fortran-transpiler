package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.program.Application;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AJoinPoint;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AProgram;

public class FProgram extends AProgram {

    private final Application app;

    public FProgram(Application app, FortranWeaver weaver) {
        super(weaver);
        this.app = app;
    }

    @Override
    public FortranNode getNode() {
        return app;
    }

}
