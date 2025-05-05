package pt.up.fe.specs.fortran.ast;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.util.SpecsIo;
import pt.up.fe.specs.util.SpecsStrings;
import pt.up.fe.specs.util.SpecsSystem;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class FortranAstTest {

    private FortranNodeFactory factory;

    @BeforeAll
    static void setupOnce() {
        SpecsSystem.programStandardInit();
    }


    @BeforeEach
    void setUp() {
        factory = new FortranContext().get(FortranContext.FACTORY);
    }

    private static final String BASE_RESOURCE = "fortran/ast/";

    private static void test(String expected, FortranNode node) {
        // Generate code
        var code = node.getCode();

        var resourceName = BASE_RESOURCE + expected;
        if (!SpecsIo.hasResource(resourceName)) {
            fail("Could not find resource '" + resourceName + "'. Expected code:\n\n" + code);
        }

        // Compare resource contents with code, normalized
        var expectedNormalized = SpecsStrings.normalizeFileContents(SpecsIo.getResource(resourceName), true);
        var codeNormalized = SpecsStrings.normalizeFileContents(code, true);

        assertEquals(expectedNormalized, codeNormalized, "Codes do not match");
    }

    @Test
    void testMainProgram() {
        // Build AST
        var program = factory.program(List.of(factory.mainProgram("hello")));
        test("mainProgram.f90", program);
    }


}
