package pt.up.fe.specs.fortran.ast.nodes.program.construct;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.stmt.ActionStmt;

import java.util.Collection;

public class ActionStmtAdapter extends ExecConstruct {
    public ActionStmtAdapter(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    public ActionStmt getStmt() {
        return getChild(ActionStmt.class, 0);
    }

    @Override
    public String getCode() {
        return getStmt().getCode();
    }
}
