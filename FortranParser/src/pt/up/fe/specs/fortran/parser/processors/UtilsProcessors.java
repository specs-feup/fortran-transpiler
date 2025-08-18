package pt.up.fe.specs.fortran.parser.processors;

import pt.up.fe.specs.fortran.ast.nodes.utils.Format;
import pt.up.fe.specs.fortran.ast.nodes.utils.Star;
import pt.up.fe.specs.fortran.parser.FlangData;
import pt.up.fe.specs.fortran.parser.FortranJsonResult;

public class UtilsProcessors extends ANodeProcessor {


    public UtilsProcessors(FortranJsonResult data) {
        super(data);
    }

    public void format(Format format) {

        var childId = getChildId(format, FlangData.getRegexValue());

        if (data().attributes().isIdInteger(childId)) {
            // Create placeholder LabelDecl
            var labelRef = factory().labelRef(factory().labelDecl(Integer.parseInt(childId)));
            format.addChild(labelRef);
            data().processorData().addLabelRef(labelRef);
            return;
        }

        format.addChild(getChild(childId));
    }

    public void star(Star star) {

    }


}
