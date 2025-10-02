package pt.up.fe.specs.fortran.ast.nodes.stmt;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;

public class TypeDeclarationStmt extends SpecificationStmt {

    public TypeDeclarationStmt(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }
}
