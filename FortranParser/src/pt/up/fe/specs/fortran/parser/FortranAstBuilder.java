package pt.up.fe.specs.fortran.parser;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.parser.processors.Nodes;

/**
 * Builds a Fortran AST based on the processed data.
 */
public class FortranAstBuilder {

    private final FortranJsonResult data;

    public FortranAstBuilder(FortranJsonResult data) {
        this.data = data;
    }

    public FortranNode build() {

        // Process each node
        var processor = new Nodes(data);
        for (var node : data.fortranNodes().values()) {
            processor.process(node);
        }

        // Return the root node
        var firstNode = data.fortranNodes().get(data.firstNode());

        if (firstNode == null) {
            throw new RuntimeException("No FortranNode for id '" + data.firstNode() + "'");
        }

        return firstNode;
    }


}
