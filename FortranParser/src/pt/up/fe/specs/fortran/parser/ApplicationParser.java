package pt.up.fe.specs.fortran.parser;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.FortranContext;
import pt.up.fe.specs.fortran.ast.nodes.program.Application;
import pt.up.fe.specs.fortran.ast.nodes.program.FortranFile;
import pt.up.fe.specs.util.SpecsLogs;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ApplicationParser {

    private final DataStore fortranOptions;

    public ApplicationParser(DataStore fortranOptions) {
        this.fortranOptions = fortranOptions;
    }

    /**
     * Builds a Fortran Application node from a map of sources, from input file to base source path (e.g., the folder it originated from, could be from a recursive search).
     *
     * @param sourceFiles
     * @return
     */
    public Application parse(Map<File, File> sourceFiles) {

        // Generate a FortranFile for each source file
        var fortranFiles = new ArrayList<FortranFile>();


        var context = new FortranContext(fortranOptions);
        var parser = new FortranParser(context);

        //System.out.println("SOURCE FILES: " + allSourceFiles);
        for (var sourceFileMapping : sourceFiles.entrySet()) {
            var sourceFile = sourceFileMapping.getKey();
            var inputSourcePath = sourceFileMapping.getValue();

            var parseResult = parser.parse(sourceFile);
            var rootNode = new FortranAstBuilder(parseResult).build();

            if (!(rootNode instanceof FortranFile fortranFile)) {
                SpecsLogs.info("Expected a " + FortranFile.class + " instance, got " + rootNode.getClass() + ". Ignoring file " + sourceFile);
                continue;
            }

            // Annotate file note
            fortranFile.set(FortranFile.FILE_NAME, sourceFile.getName());
            fortranFile.set(FortranFile.FOLDER_NAME, sourceFile.getAbsoluteFile().getParent());
            fortranFile.set(FortranFile.INPUT_SOURCE_PATH, inputSourcePath.getAbsolutePath());

            fortranFiles.add(fortranFile);
        }

        // Create application node
        return context.get(FortranContext.FACTORY).application(fortranFiles);
    }

    public Application parse(List<File> sourceFiles) {
        var sourcesMap = new LinkedHashMap<File, File>();
        sourceFiles.stream().forEach(file -> sourcesMap.put(file, file));
        return parse(sourcesMap);
    }
}
