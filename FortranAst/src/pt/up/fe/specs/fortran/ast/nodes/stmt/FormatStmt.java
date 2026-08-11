package pt.up.fe.specs.fortran.ast.nodes.stmt;

import org.suikasoft.jOptions.Datakey.DataKey;
import org.suikasoft.jOptions.Datakey.KeyFactory;
import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;

public class FormatStmt extends IDEStmt {
    public static final DataKey<String> SOURCE = KeyFactory.string("source");

    public FormatStmt(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    //TODO properly support the statement
    @Override
    public String getStmtCode() {
        return get(SOURCE).replaceFirst("^\\d+", "");
    }
}
