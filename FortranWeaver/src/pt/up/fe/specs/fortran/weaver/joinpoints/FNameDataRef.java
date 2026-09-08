package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.expr.dataref.NameDataRef;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ADataRef;

public class FNameDataRef extends ADataRef {
    public NameDataRef nameDataRef;

    public FNameDataRef(NameDataRef nameDataRef) {
        super(new FDataRef(nameDataRef));

        this.nameDataRef = nameDataRef;
    }

    @Override
    public FortranNode getNode() {
        return nameDataRef;
    }
}
