package pt.up.fe.specs.fortran.weaver.joinpoints;

import pt.up.fe.specs.fortran.ast.nodes.loops.RangeLoopControl;
import pt.up.fe.specs.fortran.ast.nodes.expr.Expr;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ADataRef;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AExpr;

import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.ARangeLoopControl;

public class FortranRangeLoopControl<Self extends FortranRangeLoopControl<Self>> extends ARangeLoopControl<Self> {

    public FortranRangeLoopControl(RangeLoopControl node, FortranWeaver weaver) {
        super(node, weaver);
    }

    @Override
    public RangeLoopControl getNodeImpl() {
        return (RangeLoopControl) super.getNodeImpl();
    }

    @Override
    public ADataRef<?> getVarImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getVar(), getWeaverEngine(), ADataRef.class);
    }

    @Override
    public AExpr<?> getLowerImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getLower(), getWeaverEngine(), AExpr.class);
    }

    @Override
    public AExpr<?> getUpperImpl() {
        return FortranJoinpoints.create(this.getNodeImpl().getUpper(), getWeaverEngine(), AExpr.class);
    }

    @Override
    public AExpr<?> getStepImpl() {
        return this.getNodeImpl().getStep()
                .map(step -> FortranJoinpoints.create(step, getWeaverEngine(), AExpr.class))
                .orElse(null);
    }

    @Override
    public void setUpperImpl(AExpr<?> upper) {
        this.getNodeImpl().setUpper((Expr) upper.getNodeImpl());
    }

    @Override
    public void setStepImpl(AExpr<?> step) {
        this.getNodeImpl().setStep((Expr) step.getNodeImpl());
    }
}
