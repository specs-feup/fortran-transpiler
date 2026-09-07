package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.decl.FortranDecl;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AFortranDecl;

public class FortranFortranDecl<Self extends FortranFortranDecl<Self>> extends AFortranDecl<Self> {

    public FortranFortranDecl(FortranDecl node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public FortranDecl getNodeImpl() {
        return (FortranDecl) super.getNodeImpl();
    }
}
