package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.omp.clause.OmpReductionClause;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AOmpReductionClause;

public class FOmpReductionClause extends AOmpReductionClause {

    public final OmpReductionClause ompReductionClause;

    public FOmpReductionClause(OmpReductionClause ompReductionClause, FortranWeaver weaver) {
        super(new FOmpClause(ompReductionClause, weaver), weaver);
        this.ompReductionClause = ompReductionClause;
    }

    @Override
    public FortranNode getNode() {
        return ompReductionClause;
    }
}
