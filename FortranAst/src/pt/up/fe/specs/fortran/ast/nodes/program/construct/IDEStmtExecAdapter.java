package pt.up.fe.specs.fortran.ast.nodes.program.construct;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.stmt.IDEStmt;

import java.util.Collection;

public class IDEStmtExecAdapter extends ExecConstruct {
    public IDEStmtExecAdapter(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    public IDEStmt getStmt() {
        return getChild(IDEStmt.class, 0);
    }

    @Override
    public String getCode() {
        return getStmt().getCode();
    }
}
