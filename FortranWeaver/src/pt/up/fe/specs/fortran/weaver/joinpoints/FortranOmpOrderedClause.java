package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.omp.clause.OmpOrderedClause;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AOmpOrderedClause;

public class FortranOmpOrderedClause<Self extends FortranOmpOrderedClause<Self>> extends AOmpOrderedClause<Self> {

    public FortranOmpOrderedClause(OmpOrderedClause node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public OmpOrderedClause getNodeImpl() {
        return (OmpOrderedClause) super.getNodeImpl();
    }
}
