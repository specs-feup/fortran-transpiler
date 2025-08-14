package pt.up.fe.specs.fortran.parser;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.expr.IntLiteral;
import pt.up.fe.specs.fortran.ast.nodes.expr.StringLiteral;
import pt.up.fe.specs.fortran.ast.nodes.program.Execution;
import pt.up.fe.specs.fortran.ast.nodes.program.FortranFile;
import pt.up.fe.specs.fortran.ast.nodes.program.MainProgram;
import pt.up.fe.specs.fortran.ast.nodes.stmt.FormatStmt;
import pt.up.fe.specs.fortran.ast.nodes.stmt.PrintStmt;
import pt.up.fe.specs.fortran.ast.nodes.utils.Format;
import pt.up.fe.specs.fortran.ast.nodes.utils.Star;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FlangToClass {

    private static final Map<FlangName, Class<? extends FortranNode>> NAME_TO_CLASS = new HashMap<>();

    static {
        NAME_TO_CLASS.put(FlangName.PROGRAM, FortranFile.class);
        NAME_TO_CLASS.put(FlangName.MAIN_PROGRAM, MainProgram.class);
        NAME_TO_CLASS.put(FlangName.EXECUTION_PART, Execution.class);
        NAME_TO_CLASS.put(FlangName.PRINT_STMT, PrintStmt.class);
        NAME_TO_CLASS.put(FlangName.FORMAT_STMT, FormatStmt.class);
        NAME_TO_CLASS.put(FlangName.CHAR_LITERAL_CONSTANT, StringLiteral.class);
        NAME_TO_CLASS.put(FlangName.INT_LITERAL_CONSTANT, IntLiteral.class);
        NAME_TO_CLASS.put(FlangName.FORMAT, Format.class);
        NAME_TO_CLASS.put(FlangName.STAR, Star.class);
    }

    public static boolean isClass(String type) {
        return FlangName.convertTry(type).map(NAME_TO_CLASS::containsKey).orElse(false);
    }

    public static Optional<Class<? extends FortranNode>> getClass(String type) {
        return FlangName.convertTry(type).map(NAME_TO_CLASS::get);
    }

}
