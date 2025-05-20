package pt.up.fe.specs.fortran.parser.processors;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.program.MainProgram;
import pt.up.fe.specs.fortran.ast.nodes.program.Program;
import pt.up.fe.specs.fortran.parser.FortranJsonResult;
import pt.up.fe.specs.util.classmap.BiConsumerClassMap;

public class Nodes {

    private static final BiConsumerClassMap<FortranNode, FortranJsonResult> PROCESSORS;

    public static void process(FortranNode node, FortranJsonResult data) {
        PROCESSORS.accept(node, data);
    }

    static {
        PROCESSORS = new BiConsumerClassMap<>();

        PROCESSORS.put(Program.class, Processors::program);
        PROCESSORS.put(MainProgram.class, Processors::mainProgram);
    }
}
