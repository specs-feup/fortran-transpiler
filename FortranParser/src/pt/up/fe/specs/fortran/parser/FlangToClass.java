package pt.up.fe.specs.fortran.parser;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.program.MainProgram;
import pt.up.fe.specs.fortran.ast.nodes.program.Program;

import java.util.HashMap;
import java.util.Map;

public class FlangToClass {

    public static final Map<String, Class<? extends FortranNode>> NAME_TO_CLASS = new HashMap<>();

    static {
        NAME_TO_CLASS.put("program", Program.class);
        NAME_TO_CLASS.put("main-program", MainProgram.class);
    }
}
