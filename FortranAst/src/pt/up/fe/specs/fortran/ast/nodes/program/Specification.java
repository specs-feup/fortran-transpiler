package pt.up.fe.specs.fortran.ast.nodes.program;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;

public class Specification extends StmtBlock {

    public Specification(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    @Override
    public String getCode() {
        var code = new StringBuilder();
        for (var stmt : getStatements()) {
            code.append(stmt.getCode()).append(ln());
        }
        return code.toString();
    }
}
