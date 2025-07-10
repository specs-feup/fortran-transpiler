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
        format.addChild(getChild(format, FlangData.getRegexValue()));
    }

    public void star(Star star) {

    }


}
