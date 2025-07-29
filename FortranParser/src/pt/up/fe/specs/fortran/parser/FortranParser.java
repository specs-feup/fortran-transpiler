package pt.up.fe.specs.fortran.parser;

import pt.up.fe.specs.fortran.ast.FortranContext;
import pt.up.fe.specs.util.SpecsIo;

import java.io.File;

public class FortranParser {

    private final FortranContext context;
    private final FortranNativeParser nativeParser;

    public FortranParser(FortranContext context) {
        this.context = context;
        this.nativeParser = new FortranNativeParser(context);
    }

    public FortranJsonResult parse(File file) {

        var extension = SpecsIo.getExtension(file);

        // Choose parser according to extension
        return switch (extension) {
            case "json" -> FortranJsonParser.parse(file, context);
            default -> nativeParser.parse(file);
        };
    }
}
