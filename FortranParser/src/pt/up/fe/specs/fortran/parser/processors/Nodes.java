package pt.up.fe.specs.fortran.parser.processors;

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
import pt.up.fe.specs.fortran.parser.FortranJsonResult;
import pt.up.fe.specs.util.classmap.ConsumerClassMap;

/**
 * Maps node classes to processors for each class, which will populate each FortranAst node.
 */
public class Nodes {

    private final ConsumerClassMap<FortranNode> processors;

    public Nodes(FortranJsonResult data) {
        this.processors = new ConsumerClassMap<>();

        var p = new ProgramProcessors(data);
        processors.put(FortranFile.class, p::program);
        processors.put(MainProgram.class, p::mainProgram);
        processors.put(Execution.class, p::execution);

        var s = new StmtProcessors(data);
        processors.put(PrintStmt.class, s::printStmt);
        processors.put(FormatStmt.class, s::formatStmt);

        var e = new ExprProcessors(data);
        processors.put(StringLiteral.class, e::stringLiteral);
        processors.put(IntLiteral.class, e::intLiteral);


        var u = new UtilsProcessors(data);
        processors.put(Star.class, u::star);
        processors.put(Format.class, u::format);
    }

    public void process(FortranNode node) {
        processors.accept(node);
    }

}
