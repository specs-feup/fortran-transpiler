package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.omp.clause.OmpReductionClause;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AOmpReductionClause;

public class FortranOmpReductionClause<Self extends FortranOmpReductionClause<Self>> extends AOmpReductionClause<Self> {

    public FortranOmpReductionClause(OmpReductionClause node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public OmpReductionClause getNodeImpl() {
        return (OmpReductionClause) super.getNodeImpl();
    }
}
