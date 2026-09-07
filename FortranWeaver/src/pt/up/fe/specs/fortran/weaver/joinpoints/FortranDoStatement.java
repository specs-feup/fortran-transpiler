package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.stmt.loop.DoConstruct;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ADoStatement;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExecution;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ALoopControl;
import java.util.Objects;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ADoStatement;

public class FortranDoStatement<Self extends FortranDoStatement<Self>> extends ADoStatement<Self> {

    public FortranDoStatement(DoConstruct node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public DoConstruct getNodeImpl() {
        return (DoConstruct) super.getNodeImpl();
    }

    @Override
    public AExecution<?> getBodyImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getBody(), getWeaverEngine(), AExecution.class);
    }

    @Override
    public ALoopControl<?> getControlImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getControl().get(), getWeaverEngine(), ALoopControl.class);
    }

    @Override
    public pt.up.fe.specs.fortran.weaver.enums.DoKind getKindImpl() {
        return pt.up.fe.specs.fortran.weaver.enums.DoKind.valueOf(this.getNodeImpl().getKind().name());
    }

    @Override
    public ADoStatement<?> copyScopeImpl() {
        DoConstruct copiedDoStmt = (DoConstruct) this.getNodeImpl().copy();
        copiedDoStmt.getBody().removeChildren();
        return FortranJoinpoints.create(copiedDoStmt, getWeaverEngine(), ADoStatement.class);
    }

    @Override
    public boolean sameScopeImpl(ADoStatement<?> loop) {
        if (!Objects.equals(this.getKindImpl().getDisplay(), loop.getKindImpl().getDisplay())) {
            return false;
        }

        return Objects.equals(this.getControlImpl().getCodeImpl(), loop.getControlImpl().getCodeImpl());
    }
}
