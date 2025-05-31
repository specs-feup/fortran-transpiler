package pt.up.fe.specs.fortran.parser;

import com.google.gson.stream.JsonReader;
import org.suikasoft.GsonPlus.JsonReaderParser;
import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.FortranContext;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.util.SpecsCheck;
import pt.up.fe.specs.util.SpecsLogs;

import java.io.*;
import java.util.*;

public class FortranJsonParser implements JsonReaderParser {

    private final FortranContext context;
    private final Map<String, FortranNode> fortranNodes;
    private final Map<String, Map<String, Object>> attributes;
    private final Set<String> ids;

    private String firstNode;

    private FortranJsonParser(DataStore fortranOptions) {
        context = new FortranContext(fortranOptions);
        this.fortranNodes = new HashMap<>();
        this.attributes = new HashMap<>();
        this.ids = new HashSet<>();
        firstNode = null;
    }


    public static FortranJsonResult parse(File file, DataStore fortranOptions) {
        try {
            return parse(new FileReader(file), fortranOptions);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Could not read file '" + file + "'", e);
        }
    }

    public static FortranJsonResult parse(Reader input, DataStore fortranOptions) {
        var parser = new FortranJsonParser(fortranOptions);
        return parser.parsePrivate(input);
    }

    private FortranJsonResult parsePrivate(Reader input) {
        JsonReader reader = new JsonReader(input);

        try {
            // Top-level object
            reader.beginObject();

            while (reader.hasNext()) {
                var objectsType = nextName(reader);

                switch (objectsType) {
                    case "nodes":
                        parseNodes(reader);
                        break;
                    case "enums":
                        parseEnums(reader);
                        break;
                    default:
                        SpecsLogs.warn("Case not defined: " + objectsType);
                }

            }
            reader.endObject();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Problem while parsing Fortran json", e);
        }

        return new FortranJsonResult(context, firstNode, ids, fortranNodes, FlangData.convert(attributes));
    }

    private void parseEnums(JsonReader reader) {
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                throw new RuntimeException("Parsing of enums not yet implemented");
            }
            reader.endArray();
        } catch (IOException e) {
            throw new RuntimeException("Problem while parsing Fortran json", e);
        }

    }

    private void parseNodes(JsonReader reader) {
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                var nodeData = nextObject(reader);
                processNodeData(nodeData);
            }
            reader.endArray();
        } catch (IOException e) {
            throw new RuntimeException("Problem while parsing Fortran json", e);
        }
    }

    private void processNodeData(Map<String, Object> nodeData) {


        // Read preamble
        var id = getString(nodeData, "id");

        // Check id
        if (ids.contains(id)) {
            throw new RuntimeException("Repeated id: " + id);
        }

        ids.add(id);

        // Assumes the first node is the root node
        if (firstNode == null) {
            firstNode = id;
        }

        var kind = getKind(id);


        // Get class corresponding to the kind
        var fortranClassTry = FlangToClass.getClass(kind);

        // If no class assume that kind is to be ignored
        // Otherwise, create node
        fortranClassTry.ifPresent(fortranClass -> fortranNodes.put(id, context.get(FortranContext.FACTORY).newNode(fortranClass, Collections.emptyList(), id)));
/*
        if (fortranClassTry.isPresent()) {
            var fortranClass = fortranClassTry.get();
            var node = context.get(FortranContext.FACTORY).newNode(fortranClass, Collections.emptyList(), id);
            fortranNodes.put(id, node);
        }
*/
        attributes.put(id, nodeData);
    }

    /**
     * Kind is encoded in the id.
     *
     * @param id
     * @return
     */
    public static String getKind(String id) {
        var dashIdx = id.indexOf('-');
        SpecsCheck.checkArgument(dashIdx != -1, () -> "Expected to finda dash (-) that signals the beginning of the node kind");

        return id.substring(dashIdx + 1);
    }

}
