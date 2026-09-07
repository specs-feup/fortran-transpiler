package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.expr.Literal;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ALiteral;

public class FortranLiteral<Self extends FortranLiteral<Self>> extends ALiteral<Self> {

    public FortranLiteral(Literal node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public Literal getNodeImpl() {
        return (Literal) super.getNodeImpl();
    }

    @Override
    public String getLiteralImpl() {
        return this.getNodeImpl().getLiteral();
    }
}
