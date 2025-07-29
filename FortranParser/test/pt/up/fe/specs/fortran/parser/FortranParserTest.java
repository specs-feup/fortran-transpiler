package pt.up.fe.specs.fortran.parser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.FortranContext;
import pt.up.fe.specs.fortran.ast.FortranOptions;
import pt.up.fe.specs.util.SpecsIo;
import pt.up.fe.specs.util.SpecsStrings;
import pt.up.fe.specs.util.SpecsSystem;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FortranParserTest {

    private static final DataStore DEFAULT_OPTIONS = DataStore.newInstance(FortranOptions.STORE_DEFINITION);

    @BeforeAll
    static void setupOnce() {
        SpecsSystem.programStandardInit();
    }

    private static final String BASE_RESOURCE = "fortran/parser/";

    private static void test(String resource) {
        test(resource, DataStore.newInstance(FortranOptions.STORE_DEFINITION));
    }

    private static void test(String resource, DataStore fortranOptions) {
        // Read json resource
        var resourceName = BASE_RESOURCE + resource;
        if (!SpecsIo.hasResource(resourceName)) {
            fail("Could not find input resource '" + resourceName + "'");
        }

        // Parse
        var context = new FortranContext(fortranOptions);
        var parseResult = FortranJsonParser.parse(new InputStreamReader(SpecsIo.resourceToStream(resourceName), StandardCharsets.UTF_8), context);
        var rootNode = new FortranAstBuilder(parseResult).build();
        System.out.println(parseResult);
        System.out.println("AST: " + rootNode.toTree());
        System.out.println("CODE:\n" + rootNode.getCode());

        //var code = parseResult.root().getCode();
        var code = rootNode.getCode();

        // Get expected output resource
        var expectedResourceName = BASE_RESOURCE + SpecsIo.removeExtension(resource) + ".expected.f90";
        if (!SpecsIo.hasResource(resourceName)) {
            fail("Could not find expected output resource '" + expectedResourceName + "'. Expected contents:\n" + code);
        }

        // Compare resource contents with code, normalized
        var expectedNormalized = SpecsStrings.normalizeFileContents(SpecsIo.getResource(expectedResourceName), true);
        var codeNormalized = SpecsStrings.normalizeFileContents(code, true);

        assertEquals(expectedNormalized, codeNormalized, "Codes do not match.\nOriginal code:\n" + code);


    }

    @Test
    void testHelloWorld() {
        test("hello.json");
    }

    @Test
    void testNativeParser() {
        var context = new FortranContext(DEFAULT_OPTIONS);
        var parser = new FortranNativeParser(context);

        parser.parse(SpecsIo.toInputStream("""
                program hello
                    ! This is a comment line; it is ignored by the compiler
                    print *, 'Hello, World!'
                    ! print 100, 'Hello, World!', 2
                    ! 100 FORMAT(A, I3)
                    PRINT '(A, F6.3)', 'Value = ', 3
                end program hello                
                                """));
    }


}
