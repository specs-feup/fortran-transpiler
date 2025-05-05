package pt.up.fe.specs.fortran.ast.nodes.stmt;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;

/**
 * R514 executable-construct
 */
public abstract class ExecutableStmt extends FortranNode {

    public ExecutableStmt(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }
}
