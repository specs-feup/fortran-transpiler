package pt.up.fe.specs.fortran.parser;

import com.google.gson.stream.JsonReader;
import org.suikasoft.GsonPlus.JsonReaderParser;
import pt.up.fe.specs.util.SpecsLogs;

import java.io.*;

public class FortranJsonParser implements JsonReaderParser {


    public FortranJsonResult parse(File file) {
        try {
            return parse(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Could not read file '" + file + "'", e);
        }
    }

    public FortranJsonResult parse(Reader input) {
        JsonReader reader = new JsonReader(input);

        try {
            // Top-level array
            reader.beginArray();

            while (reader.hasNext()) {
                reader.beginObject();

                // First element is type
                var type = nextString(reader, "type");

                switch (type) {
                    case "node":
                        parseNode(reader);
                        break;
                    default:
                        SpecsLogs.warn("Case not defined: " + type);
                }

                reader.endObject();
            }
            reader.endArray();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Problem while parsing Fortran json", e);
        }

        return null;
    }

    private void parseNode(JsonReader reader) {


        // Create node

        var id = nextString(reader, "id");
        var kind = nextString(reader, "kind");
        var children = nextList(reader, "children", this::nextString);


    }
}
