package pt.up.fe.specs.fortran.weaver;

import org.lara.interpreter.weaver.interf.AGear;
import org.lara.interpreter.weaver.interf.JoinPoint;
import org.lara.interpreter.weaver.interf.WeaverEngine;
import org.lara.interpreter.weaver.options.WeaverOption;
import org.lara.language.specification.dsl.LanguageSpecification;
import org.suikasoft.jOptions.DataStore.SimpleDataStore;
import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.FortranContext;
import pt.up.fe.specs.fortran.ast.FortranOptions;
import pt.up.fe.specs.fortran.ast.nodes.program.Application;
import pt.up.fe.specs.fortran.ast.nodes.program.FortranFile;
import pt.up.fe.specs.fortran.parser.FortranAstBuilder;
import pt.up.fe.specs.fortran.parser.FortranJsonParser;
import pt.up.fe.specs.fortran.weaver.abstracts.weaver.AFortranWeaver;
import pt.up.fe.specs.util.SpecsIo;
import pt.up.fe.specs.util.SpecsLogs;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Weaver Implementation for FortranWeaver<br>
 * Since the generated abstract classes are always overwritten, their implementation should be done by extending those abstract classes with user-defined classes.<br>
 * The abstract class {@link pt.up.fe.specs.fortran.weaver.abstracts.AFortranWeaverJoinPoint} contains attributes and actions common to all join points.
 *
 * @author Lara Weaver Generator
 */
public class FortranWeaver extends AFortranWeaver {

    private final static String DEFAULT_OUTPUT_DIR = "weaver_output";

    private List<File> currentSources;
    private File currentOutputDir;
    private DataStore currentArgs;

    private List<File> allSourceFiles;
    private Application currentRoot;

    public FortranWeaver() {
        this.currentSources = Collections.emptyList();
        this.currentOutputDir = new File(DEFAULT_OUTPUT_DIR);
        this.currentArgs = new SimpleDataStore(getStoreDefinition());

        this.allSourceFiles = Collections.emptyList();
        this.currentRoot = null;
    }

    /**
     * Setups the weaver with inputs sources, an output folder and the provided options.
     *
     * @param sources   the sources with the code (files/folders)
     * @param outputDir output folder for the generated file(s)
     * @param args      options for the weaver
     * @return true if initialization occurred without problems, false otherwise
     */
    @Override
    public boolean begin(List<File> sources, File outputDir, DataStore args) {

        // Set fields
        this.currentSources = sources;
        //this.currentOutputDir = outputDir;
        this.currentArgs = args;

        // TODO: Create a mapping between original source and a given source file,
        // in order to be able to replicate source structure in the output folder

        this.allSourceFiles = getAllSourceFiles();

        // TODO: This shoudl be extracted to its own class, probably in FortranParser?
        // Generate a FortranFile for each source file
        var fortranFiles = new ArrayList<FortranFile>();
        // TODO: Options should come from the weaver datakeys
        var fortranOptions = DataStore.newInstance(FortranOptions.STORE_DEFINITION);
        var context = new FortranContext(fortranOptions);

        System.out.println("SOURCE FILES: " + allSourceFiles);
        for (var sourceFile : allSourceFiles) {
            var parseResult = FortranJsonParser.parse(sourceFile, context);
            var rootNode = new FortranAstBuilder(parseResult).build();

            if (!(rootNode instanceof FortranFile fortranFile)) {
                SpecsLogs.info("Expected a " + FortranFile.class + " instance, got " + rootNode.getClass() + ". Ignoring file " + sourceFile);
                continue;
            }

            System.out.println("Adding file '" + sourceFile.getAbsolutePath() + "'");
            fortranFiles.add(fortranFile);
        }

        // Create root node
        this.currentRoot = context.get(FortranContext.FACTORY).application(fortranFiles);

        return true;
    }

    private List<File> getAllSourceFiles() {
        var allSources = new ArrayList<File>();

        for (var source : currentSources) {
            addSource(source, allSources);
        }

        return allSources;
    }

    private void addSource(File source, ArrayList<File> allSources) {
        // Base case
        if (source.isFile()) {
            var extension = SpecsIo.getExtension(source);

            if (!extension.equals("json")) {
                SpecsLogs.info("Ignoring file '" + source + "', currently only supporting .json extension");
                return;
            }

            allSources.add(source);
            return;
        }

        if (source.isDirectory()) {
            for (var childSource : SpecsIo.getFilesRecursive(source, List.of("json", "f90"))) {
                addSource(childSource, allSources);
            }
            return;
        }

        SpecsLogs.info("Ignoring source: " + source);
    }

    /**
     * Return a JoinPoint instance of the sources root, i.e., an instance of AProgram
     *
     * @return an instance of the join point root/program
     */
    @Override
    public JoinPoint select() {
        return FortranJoinpoints.create(currentRoot);
    }

    /**
     * Performs operations needed when closing the weaver (e.g., generates new version of source file(s) to the specified output folder).
     *
     * @return if close was successful
     */
    @Override
    public boolean close() {

        // Make sure output folder exists
        SpecsIo.mkdir(currentOutputDir);

        // Write output files
        for (var file : currentRoot.getFiles()) {
            var outputFile = new File(currentOutputDir, file.get(FortranFile.FILE_NAME));
            SpecsLogs.info("Writing file '" + outputFile.getAbsolutePath() + "'");
            SpecsIo.write(outputFile, file.getCode());
        }

        SpecsLogs.info("Output files written to folder " + currentOutputDir.getAbsolutePath());

        return true;
    }

    /**
     * Returns a list of Gears associated to this weaver engine
     *
     * @return a list of implementations of {@link AGear} or null if no gears are available
     */
    @Override
    public List<AGear> getGears() {
        return List.of(); //i.e., no gears currently being used
    }

    /**
     * Returns a list of options specific to this weaver engine.
     *
     * @return a list of {@link WeaverOption} representing additional options provided by this weaver
     */
    @Override
    public List<WeaverOption> getOptions() {
        return List.of(); //i.e., no additional options
    }

    /**
     * Returns thread-local instance of weaver engine.
     */
    public static FortranWeaver getFortranWeaver() {
        return (FortranWeaver) WeaverEngine.getThreadLocalWeaver();
    }

    /**
     * Builds the language specification, based on the input XML files.
     *
     * @return a new {@link LanguageSpecification} instance for this weaver
     */
    public static LanguageSpecification buildLanguageSpecification() {
        return LanguageSpecification.newInstance(() -> "fortran/weaverspecs/" + LanguageSpecification.getJoinPointsFilename(),
                () -> "fortran/weaverspecs/" + LanguageSpecification.getAttributesFilename(),
                () -> "fortran/weaverspecs/" + LanguageSpecification.getActionsFilename());
    }

    /**
     * Builds the language specification, based on the input XML files.
     *
     * @return a new {@link LanguageSpecification} instance for this weaver
     */
    @Override
    protected LanguageSpecification buildLangSpecs() {
        return buildLanguageSpecification();
    }
}
