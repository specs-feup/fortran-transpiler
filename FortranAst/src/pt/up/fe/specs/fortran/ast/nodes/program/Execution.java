package pt.up.fe.specs.fortran.ast.nodes.program;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;


/**
 * R509 execution-part
 * <p>
 * executable-construct [execution-part-construct]...
 * <p>
 * Contains statements to execute
 * <p>
 * It is more specialized than ExecBlock, since it requires the last construct
 * to be a ExecConstruct (if it exists) to avoid AST ambiguity.
 */
public class Execution extends ExecBlock {
    public Execution(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }
}
