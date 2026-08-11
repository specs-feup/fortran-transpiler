package pt.up.fe.specs.fortran.ast.nodes.stmt;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;

/*
 * Represents a statement that can be interpreted as either ImplicitPartStmt
 * or SpecConstruct.
 */
public abstract class ISStmt extends Stmt {
    public ISStmt(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }
}
