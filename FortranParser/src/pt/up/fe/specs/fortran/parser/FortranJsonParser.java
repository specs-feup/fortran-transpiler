package pt.up.fe.specs.fortran.parser;

import com.google.gson.stream.JsonReader;
import org.suikasoft.GsonPlus.JsonReaderParser;
import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.FortranContext;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.util.SpecsLogs;

import java.io.*;
import java.util.*;

public class FortranJsonParser implements JsonReaderParser {

    private final FortranContext context;
    private final Map<String, FortranNode> fortranNodes;
    private final Map<String, Map<String, Object>> attributes;
    private final Set<String> ids;

    private FortranJsonParser(DataStore fortranOptions) {
        context = new FortranContext(fortranOptions);
        this.fortranNodes = new HashMap<>();
        this.attributes = new HashMap<>();
        this.ids = new HashSet<>();
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
                    default:
                        SpecsLogs.warn("Case not defined: " + objectsType);
                }

            }
            reader.endObject();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Problem while parsing Fortran json", e);
        }

        return new FortranJsonResult(context, ids, fortranNodes, attributes);
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
        var kind = getString(nodeData, "type");

        // Check id
        if (ids.contains(id)) {
            throw new RuntimeException("Repeated id: " + id);
        }

        ids.add(id);

        // Get class corresponding to the kind
        var fortranClass = FlangToClass.NAME_TO_CLASS.get(kind);

        // If null assume that kind is to be ignored
        // Otherwise, create node
        if (fortranClass != null) {
            var node = context.get(FortranContext.FACTORY).newNode(fortranClass, Collections.emptyList(), id);
            fortranNodes.put(id, node);
        }

        attributes.put(id, nodeData);
    }


}
