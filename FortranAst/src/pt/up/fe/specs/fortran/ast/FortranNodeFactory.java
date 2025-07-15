package pt.up.fe.specs.fortran.ast;

import org.suikasoft.jOptions.Interfaces.DataStore;
import org.suikasoft.jOptions.storedefinition.StoreDefinitions;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.expr.Literal;
import pt.up.fe.specs.fortran.ast.nodes.expr.StringLiteral;
import pt.up.fe.specs.fortran.ast.nodes.program.*;
import pt.up.fe.specs.fortran.ast.nodes.stmt.ExecutableStmt;
import pt.up.fe.specs.fortran.ast.nodes.stmt.PrintStmt;
import pt.up.fe.specs.fortran.ast.nodes.utils.Format;
import pt.up.fe.specs.fortran.ast.nodes.utils.Star;
import pt.up.fe.specs.util.SpecsCollections;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FortranNodeFactory {

    private final FortranContext context;
    private final DataStore baseData;

    public FortranNodeFactory(FortranContext context, DataStore baseData) {
        this.context = context;
        this.baseData = baseData;
    }

    public FortranNodeFactory(FortranContext context) {
        this(context, null);
    }

    public FortranNodeFactory(FortranNodeFactory factory) {
        this(factory.context, factory.baseData);
    }

    public DataStore newDataStore(Class<? extends FortranNode> nodeClass) {
        return newDataStore(nodeClass, null);
    }

    public DataStore newDataStore(Class<? extends FortranNode> nodeClass, String id) {

        DataStore data = DataStore.newInstance(StoreDefinitions.fromInterface(nodeClass), true);

        // Add base node, if present
        if (baseData != null) {
            data.addAll(baseData);
        }

        // Set context
        data.set(FortranNode.CONTEXT, context);

        // Set id
        if (id != null) {
            data.set(FortranNode.ID, id);
        } else {
            data.set(FortranNode.ID, context.get(FortranContext.ID_GENERATOR).next("node_"));
        }


        return data;
    }

    @Override
    public String toString() {
        return "FortranNodeFactory " + hashCode();
    }

    /**
     * Creates an empty node of the given class.
     *
     * @param <T>
     * @param nodeClass
     * @return
     */
    public <T extends FortranNode> T newNode(Class<T> nodeClass) {
        return newNode(nodeClass, Collections.emptyList());
    }

    public <T extends FortranNode> T newNode(Class<T> nodeClass, Collection<? extends FortranNode> children) {
        return newNode(nodeClass, children, null);
    }

    public <T extends FortranNode> T newNode(Class<T> nodeClass, Collection<? extends FortranNode> children, String id) {
        try {
            DataStore data = newDataStore(nodeClass, id);

            return nodeClass.getDeclaredConstructor(DataStore.class, Collection.class)
                    .newInstance(data, children);
        } catch (Exception e) {
            throw new RuntimeException("Could not create FortranNode", e);
        }
    }

    // PROGRAM

    public Application application(List<FortranFile> files) {
        DataStore data = newDataStore(Application.class);
        return new Application(data, files);
    }

    public FortranFile fortranFile(List<ProgramUnit> units) {
        DataStore data = newDataStore(FortranFile.class);
        return new FortranFile(data, units);
    }


    public MainProgram mainProgram(String programName, List<ExecutableStmt> execution) {
        DataStore data = newDataStore(MainProgram.class);
        data.set(MainProgram.PROGRAM_NAME, Optional.ofNullable(programName));

        var executionBlock = execution(execution);

        return new MainProgram(data, List.of(executionBlock));
    }

    public Execution execution(List<ExecutableStmt> statements) {
        DataStore data = newDataStore(Execution.class);

        return new Execution(data, statements);
    }

    // STMT

    public PrintStmt printStmt(Format format, FortranNode... outputItems) {
        return printStmt(format, List.of(outputItems));
    }

    public PrintStmt printStmt(Format format, List<FortranNode> outputItems) {
        DataStore data = newDataStore(PrintStmt.class);

        return new PrintStmt(data, SpecsCollections.concat(format, outputItems));
    }


    // EXPR

    public StringLiteral stringLiteral(String literal) {
        return stringLiteral(literal, null);
    }

    public StringLiteral stringLiteral(String literal, String kindParam) {
        DataStore data = newDataStore(StringLiteral.class);
        data.set(Literal.SOURCE_LITERAL, literal);

        return new StringLiteral(data, Collections.emptyList());
    }

    // UTIL

    public Format format(FortranNode formatType) {
        // Check if node is of allowed type
        if (!(formatType instanceof Star)) {
            throw new RuntimeException("Unsupported type for Format child: " + formatType.getClass());
        }

        DataStore data = newDataStore(Format.class);
        return new Format(data, List.of(formatType));
    }

    public Star star() {
        DataStore data = newDataStore(Star.class);
        return new Star(data, Collections.emptyList());
    }

}
