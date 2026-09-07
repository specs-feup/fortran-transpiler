package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.expr.SectionSubscript;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ASectionSubscript;

public class FortranSectionSubscript<Self extends FortranSectionSubscript<Self>> extends ASectionSubscript<Self> {

    public FortranSectionSubscript(SectionSubscript node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public SectionSubscript getNodeImpl() {
        return (SectionSubscript) super.getNodeImpl();
    }
}
