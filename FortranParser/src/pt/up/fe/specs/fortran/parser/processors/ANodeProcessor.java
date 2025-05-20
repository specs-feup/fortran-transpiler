package pt.up.fe.specs.fortran.parser.processors;

import pt.up.fe.specs.fortran.parser.FortranJsonResult;

public class ANodeProcessor implements NodeProcessor {


    private final FortranJsonResult data;

    public ANodeProcessor(FortranJsonResult data) {
        this.data = data;
    }

    @Override
    public FortranJsonResult data() {
        return data;
    }
}
