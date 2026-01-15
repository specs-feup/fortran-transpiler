package pt.up.fe.specs.fortran.ast.nodes.stmt;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;

public abstract class SpecificationStmt extends DeclarationStmt {

    public SpecificationStmt(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }
}
