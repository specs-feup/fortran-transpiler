package pt.up.fe.specs.fortran.weaver.abstracts;

import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.FortranWeaver;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AJoinPoint;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AProgram;

/**
 * Abstract class which can be edited by the developer. This class will not be overwritten.
 *
 * @author Lara Weaver Generator
 */
public abstract class AFortranWeaverJoinPoint extends AJoinPoint {

    /**
     * 
     */
    public AFortranWeaverJoinPoint(FortranWeaver weaver){
        super(weaver);
    }
    /**
     * Returns the Weaving Engine this join point pertains to.
     */
    @Override
    public FortranWeaver getWeaverEngine() {
        return (FortranWeaver) super.getWeaverEngine();
    }

    /**
     * Compares the two join points based on their node reference of the used compiler/parsing tool.<br>
     * This is the default implementation for comparing two join points. <br>
     * <b>Note for developers:</b> A weaver may override this implementation in the editable abstract join point, so
     * the changes are made for all join points, or override this method in specific join points.
     */
    @Override
    public boolean compareNodes(AJoinPoint aJoinPoint) {
        return this.getNode().equals(aJoinPoint.getNode());
    }

    @Override
    public String getCodeImpl() {
        return getNode().getCode();
    }

    @Override
    public AJoinPoint getParentImpl() {
        return FortranJoinpoints.create(getNode().getParent(), getWeaverEngine());
    }

    @Override
    public AProgram getRootImpl() {
        return (AProgram) getWeaverEngine().getRootJp();
    }

    @Override
    public AJoinPoint[] getChildrenArrayImpl() {
        return FortranJoinpoints.create(getNode().getChildren(), getWeaverEngine(), AJoinPoint.class);
    }
}
