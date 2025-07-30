package pt.up.fe.specs.fortran.weaver.abstracts;

import org.lara.interpreter.weaver.interf.SelectOp;
import pt.up.fe.specs.fortran.weaver.FortranJoinpoints;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AJoinPoint;
import pt.up.fe.specs.fortran.weaver.abstracts.joinpoints.AProgram;

import java.util.List;

/**
 * Abstract class which can be edited by the developer. This class will not be overwritten.
 *
 * @author Lara Weaver Generator
 */
public abstract class AFortranWeaverJoinPoint extends AJoinPoint {

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

    /**
     * Generic select function, used by the default select implementations.
     */
    @Override
    public <T extends AJoinPoint> List<? extends T> select(Class<T> joinPointClass, SelectOp op) {
        throw new RuntimeException("Generic select function not implemented yet. Implement it in order to use the default implementations of select");
    }

    @Override
    public String getCodeImpl() {
        return getNode().getCode();
    }

    @Override
    public AJoinPoint getParentImpl() {
        return FortranJoinpoints.create(getNode().getParent());
    }

    @Override
    public AProgram getRootImpl() {
        return (AProgram) getWeaverEngine().getRootJp();
    }
}
