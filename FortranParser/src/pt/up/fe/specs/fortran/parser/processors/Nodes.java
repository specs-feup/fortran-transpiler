package pt.up.fe.specs.fortran.parser.processors;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.program.Execution;
import pt.up.fe.specs.fortran.ast.nodes.program.MainProgram;
import pt.up.fe.specs.fortran.ast.nodes.program.Program;
import pt.up.fe.specs.fortran.ast.nodes.stmt.PrintStmt;
import pt.up.fe.specs.fortran.parser.FortranJsonResult;
import pt.up.fe.specs.util.classmap.ConsumerClassMap;

public class Nodes {

    private final ConsumerClassMap<FortranNode> processors;

    public Nodes(FortranJsonResult data) {
        this.processors = new ConsumerClassMap<>();

        var p = new TopProcessors(data);
        processors.put(Program.class, p::program);
        processors.put(MainProgram.class, p::mainProgram);
        processors.put(Execution.class, p::execution);


        var s = new StmtProcessors(data);
        processors.put(PrintStmt.class, s::printStmt);
    }

    public void process(FortranNode node) {
        processors.accept(node);
    }

}
