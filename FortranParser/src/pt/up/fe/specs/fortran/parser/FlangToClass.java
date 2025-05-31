package pt.up.fe.specs.fortran.parser;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.program.MainProgram;
import pt.up.fe.specs.fortran.ast.nodes.program.Program;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FlangToClass {

    private static final Map<FlangName, Class<? extends FortranNode>> NAME_TO_CLASS = new HashMap<>();

    static {
        NAME_TO_CLASS.put(FlangName.PROGRAM, Program.class);
        NAME_TO_CLASS.put(FlangName.MAIN_PROGRAM, MainProgram.class);
    }

    public static boolean isClass(String type) {
        return FlangName.convertTry(type).map(NAME_TO_CLASS::containsKey).orElse(false);
    }

    public static Optional<Class<? extends FortranNode>> getClass(String type) {
        return FlangName.convertTry(type).map(NAME_TO_CLASS::get);
    }

}
