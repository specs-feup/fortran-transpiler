package pt.up.fe.specs.fortran.ast.nodes.program;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.util.utilities.StringLines;

import java.util.Collection;
import java.util.stream.Collectors;


/**
 * R509 execution-part
 * <p>
 * executable-construct [execution-part-construct]...
 * <p>
 * Contains statements to execute
 */
public class Execution extends FortranNode {

    public Execution(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    @Override
    public String getCode() {

        var body = getChildren().stream()
                .map(FortranNode::getCode)
                .flatMap(s -> StringLines.getLines(s).stream())
                .collect(Collectors.joining(ln()));

        return body;
    }
}
