package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.stmt.TypeDeclarationStmt;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AAttributeSpecifier;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AEntityDecl;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExpr;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ATypeDeclarationStatement;

public class FTypeDeclarationStatement extends ATypeDeclarationStatement {

    public final TypeDeclarationStmt typeDeclarationStmt;

    public FTypeDeclarationStatement(TypeDeclarationStmt typeDeclarationStmt, FortranWeaver weaver) {
        super(new FSpecificationStatement(typeDeclarationStmt, weaver), weaver);
        this.typeDeclarationStmt = typeDeclarationStmt;
    }

    @Override
    public AAttributeSpecifier[] getAttrsArrayImpl() {
        return typeDeclarationStmt.getAttributes()
                .stream()
                .map(node -> FortranJoinpoints.create(node, getWeaverEngine()))
                .toList()
                .toArray(new AAttributeSpecifier[0]);
    }

    @Override
    public AEntityDecl[] getDeclsArrayImpl() {
        return typeDeclarationStmt.getDecls()
                .stream()
                .map(node -> FortranJoinpoints.create(node, getWeaverEngine()))
                .toList()
                .toArray(new AEntityDecl[0]);
    }

    @Override
    public FortranNode getNode() {
        return typeDeclarationStmt;
    }
}
