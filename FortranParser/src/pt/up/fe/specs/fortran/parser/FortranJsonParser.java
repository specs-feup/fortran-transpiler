package pt.up.fe.specs.fortran.parser;

import com.google.gson.stream.JsonReader;
import pt.up.fe.specs.util.SpecsCheck;
import pt.up.fe.specs.util.SpecsLogs;

import java.io.*;

public class FortranJsonParser {


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
            // Top-level object
            reader.beginObject();

            while (reader.hasNext()) {
                String name = reader.nextName();

                switch (name) {
                    case "node":
                        parseNode(reader);
                        break;
                    default:
                        SpecsLogs.warn("Case not defined: " + name);
                }
            }
            reader.endObject();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Problem while parsing Fortran json", e);
        }

        return null;
    }

    private void parseNode(JsonReader reader) {


        // Create node

        try {
            reader.beginObject();

            // Expected id
            String name = reader.nextName();
            SpecsCheck.checkArgument(name.equals("id"), () -> "Expected id");

            String id = reader.nextString();

            // Expected kind
            name = reader.nextName();
            SpecsCheck.checkArgument(name.equals("kind"), () -> "Expected kind");

            String kind = reader.nextString();

            while (reader.hasNext()) {
                name = reader.nextName();
                switch (name) {
                    case "children":
                        reader.nextName();
                }

            }
            reader.endObject();
        } catch (IOException e) {
            throw new RuntimeException("Problem while parsing object", e);
        }
        /*
        reader.beginObject();
        String name = reader.nextName();

        System.out.println("NAME: " + name);

         */
/*
                if (name.equals("name")) {
                    System.out.println("Name: " + reader.nextString());
                } else if (name.equals("age")) {
                    System.out.println("Age: " + reader.nextInt());
                } else {
                    reader.skipValue(); // Skip values you don't care about
                }

 */

    }
}
