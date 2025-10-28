package pt.up.fe.specs.fortran.ast.nodes.stmt;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.decl.EntityDecl;

import java.util.Collection;
import java.util.List;

/**
 * Has EntityDecl as children, each one representing an entity declaration.
 */
public class TypeDeclarationStmt extends SpecificationStmt {

    public TypeDeclarationStmt(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }


    public List<EntityDecl> getDecls() {
        return getChildren(EntityDecl.class);
    }
}
