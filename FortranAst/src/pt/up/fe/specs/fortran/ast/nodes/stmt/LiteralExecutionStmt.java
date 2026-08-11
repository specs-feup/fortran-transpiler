package pt.up.fe.specs.fortran.ast.nodes.stmt;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.LiteralNode;

import java.util.Collection;

// TODO(Process-ing): Why does this exist?!?!
public class LiteralExecutionStmt extends Stmt implements LiteralNode {

    public LiteralExecutionStmt(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    @Override
    public String getStmtCode() {
        return getLiteralCode();
    }
}
