package pt.up.fe.specs.fortran.parser.processors;

import pt.up.fe.specs.fortran.ast.nodes.decl.LabelDecl;
import pt.up.fe.specs.fortran.ast.nodes.utils.LabelRef;

import java.util.ArrayList;
import java.util.List;

public class ProcessorData {

    private final List<LabelDecl> labelDecls;
    private final List<LabelRef> labelRefs;

    public ProcessorData() {
        labelDecls = new ArrayList<>();
        labelRefs = new ArrayList<>();
    }

    public List<LabelDecl> getLabelDecls() {
        return labelDecls;
    }

    public List<LabelRef> getLabelRefs() {
        return labelRefs;
    }

    public void addLabelDecl(LabelDecl labelDecl) {
        labelDecls.add(labelDecl);
    }

    public void addLabelRef(LabelRef labelRef) {
        labelRefs.add(labelRef);
    }
}
