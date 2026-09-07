package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.stmt.TypeDeclarationStmt;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AAttributeSpecifier;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AEntityDecl;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ATypeDeclarationStatement;

public class FortranTypeDeclarationStatement<Self extends FortranTypeDeclarationStatement<Self>> extends ATypeDeclarationStatement<Self> {

    public FortranTypeDeclarationStatement(TypeDeclarationStmt node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public TypeDeclarationStmt getNodeImpl() {
        return (TypeDeclarationStmt) super.getNodeImpl();
    }

    @Override
    public AEntityDecl<?>[] getDeclsImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getDecls(), getWeaverEngine(), AEntityDecl.class);
    }

    @Override
    public AAttributeSpecifier<?>[] getAttrsImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getAttributes(), getWeaverEngine(), AAttributeSpecifier.class);
    }
}
