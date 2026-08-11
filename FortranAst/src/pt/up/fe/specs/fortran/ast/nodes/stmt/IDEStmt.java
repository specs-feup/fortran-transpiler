package pt.up.fe.specs.fortran.ast.nodes.stmt;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;

/*
 * Represents a statement that can be interpreted as either ImplicitPartStmt,
 * DeclConstruct or ExecPartConstruct.
 */
public abstract class IDEStmt extends Stmt {
    public IDEStmt(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }
}
