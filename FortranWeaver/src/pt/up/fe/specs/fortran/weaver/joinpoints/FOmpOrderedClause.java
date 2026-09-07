package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.omp.clause.OmpOrderedClause;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AOmpOrderedClause;

public class FOmpOrderedClause extends AOmpOrderedClause {

    public final OmpOrderedClause ompOrderedClause;

    public FOmpOrderedClause(OmpOrderedClause ompOrderedClause, FortranWeaver weaver) {
        super(new FOmpClause(ompOrderedClause, weaver), weaver);
        this.ompOrderedClause = ompOrderedClause;
    }

    @Override
    public FortranNode getNode() {
        return ompOrderedClause;
    }
}
