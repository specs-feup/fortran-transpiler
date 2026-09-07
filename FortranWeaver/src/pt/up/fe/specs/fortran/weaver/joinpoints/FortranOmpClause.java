package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.omp.clause.OmpClause;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AOmpClause;

public class FortranOmpClause<Self extends FortranOmpClause<Self>> extends AOmpClause<Self> {

    public FortranOmpClause(OmpClause node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public OmpClause getNodeImpl() {
        return (OmpClause) super.getNodeImpl();
    }
}
