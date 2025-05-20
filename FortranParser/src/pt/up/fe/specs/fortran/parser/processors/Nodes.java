package pt.up.fe.specs.fortran.parser.processors;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.program.MainProgram;
import pt.up.fe.specs.fortran.ast.nodes.program.Program;
import pt.up.fe.specs.fortran.parser.FortranJsonResult;
import pt.up.fe.specs.util.classmap.ConsumerClassMap;

public class Nodes {

    private final ConsumerClassMap<FortranNode> processors;

    public Nodes(FortranJsonResult data) {
        this.processors = new ConsumerClassMap<>();

        var p = new Processors(data);
        processors.put(Program.class, p::program);
        processors.put(MainProgram.class, p::mainProgram);
    }

    public void process(FortranNode node) {
        processors.accept(node);
    }

}
