package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.omp.OmpConstruct;
import pt.up.fe.specs.fortran.ast.nodes.omp.clause.OmpClause;
import pt.up.fe.specs.fortran.ast.nodes.omp.enums.OmpDirectiveKind;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AOmpClause;
import java.util.Arrays;
import java.util.List;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AOmpConstruct;

public class FortranOmpConstruct<Self extends FortranOmpConstruct<Self>> extends AOmpConstruct<Self> {

    public FortranOmpConstruct(OmpConstruct node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public OmpConstruct getNodeImpl() {
        return (OmpConstruct) super.getNodeImpl();
    }

    @Override
    public AOmpClause<?>[] getClausesImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getClauses(), getWeaverEngine(), AOmpClause.class);
    }

    @Override
    public void setClausesImpl(AOmpClause<?>[] clauses) {
        List<OmpClause> clauseList = Arrays.stream(clauses)
                .map(c -> (OmpClause) c.getNodeImpl())
                .toList();

        this.getNodeImpl().setClauses(clauseList);
    }

    @Override
    public void setDirectiveImpl(String directive) {
        this.getNodeImpl().set(OmpConstruct.KINDS, OmpDirectiveKind.getKinds(directive));
    }
}
