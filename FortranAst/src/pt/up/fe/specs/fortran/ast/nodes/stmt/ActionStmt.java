package pt.up.fe.specs.fortran.ast.nodes.stmt;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;

/**
 * R515 action-stmt
 */
public abstract class ActionStmt extends ExecutableStmt {
    
    public ActionStmt(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }
}
