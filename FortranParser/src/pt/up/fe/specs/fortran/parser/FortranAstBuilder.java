package pt.up.fe.specs.fortran.parser;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.parser.processors.Nodes;

public class FortranAstBuilder {

    private final FortranJsonResult data;

    public FortranAstBuilder(FortranJsonResult data) {
        this.data = data;
    }

    public FortranNode build() {

        // Process each node
        for (var node : data.fortranNodes().values()) {
            Nodes.process(node, data);
        }

        // Return the root node
        var firstNode = data.fortranNodes().get(data.firstNode());

        if (firstNode == null) {
            throw new RuntimeException("No FortranNode for id '" + data.firstNode() + "'");
        }

        return firstNode;
    }


}
