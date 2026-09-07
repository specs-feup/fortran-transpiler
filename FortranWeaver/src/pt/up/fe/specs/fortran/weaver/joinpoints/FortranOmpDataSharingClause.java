package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.omp.clause.OmpDataSharingClause;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AOmpDataSharingClause;

public class FortranOmpDataSharingClause<Self extends FortranOmpDataSharingClause<Self>> extends AOmpDataSharingClause<Self> {

    public FortranOmpDataSharingClause(OmpDataSharingClause node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public OmpDataSharingClause getNodeImpl() {
        return (OmpDataSharingClause) super.getNodeImpl();
    }
}
