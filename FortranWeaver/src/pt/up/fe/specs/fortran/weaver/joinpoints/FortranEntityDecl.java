package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.decl.EntityDecl;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AEntityDecl;

public class FortranEntityDecl<Self extends FortranEntityDecl<Self>> extends AEntityDecl<Self> {

    public FortranEntityDecl(EntityDecl node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public EntityDecl getNodeImpl() {
        return (EntityDecl) super.getNodeImpl();
    }

    @Override
    public String getNameImpl() {
        return this.getNodeImpl().get(EntityDecl.NAME);
    }
}
