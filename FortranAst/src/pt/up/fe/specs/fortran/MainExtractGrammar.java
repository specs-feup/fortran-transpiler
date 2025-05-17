package pt.up.fe.specs.fortran;

import pt.up.fe.specs.util.SpecsIo;
import pt.up.fe.specs.util.csv.CsvWriter;
import pt.up.fe.specs.util.utilities.LineStream;

import java.io.File;

/**
 * Reads the Fortran grammar and extracts a list of the rules to a CSV
 */
public class MainExtractGrammar {

    public static void main(String[] args) {
        var grammar = SpecsIo.getResource("fortran/fortran2018.grammar");
        var csv = new CsvWriter("Rule", "Name");
        try (var lines = LineStream.newInstance(() -> "fortran/fortran2018.grammar")) {
            while (lines.hasNextLine()) {
                var line = lines.nextLine().trim();

                if (!line.startsWith("R")) {
                    continue;
                }

                // Find ->
                var idx = line.indexOf("->", 0);
                if (idx == -1) {
                    continue;
                }

                var ruleAndName = line.substring(0, idx);
                var sep = ruleAndName.indexOf(' ');

                csv.addLine(ruleAndName.substring(0, sep), ruleAndName.substring(sep + 1, ruleAndName.length()));
            }
        }


        var file = new File("grammar.csv");
        SpecsIo.write(file, csv.buildCsv());
        System.out.println("Wrote " + file.getAbsolutePath());
    }
}
