package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.expr.SectionSubscript;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ASectionSubscript;

public class FSectionSubscript extends ASectionSubscript {

    private final SectionSubscript sectionSubscript;

    public FSectionSubscript(SectionSubscript sectionSubscript, FortranWeaver weaver) {
        super(weaver);
        this.sectionSubscript = sectionSubscript;
    }

    @Override
    public FortranNode getNode() {
        return sectionSubscript;
    }
}
