package pt.up.fe.specs.fortran.ast.nodes.program;

import org.suikasoft.jOptions.Datakey.DataKey;
import org.suikasoft.jOptions.Datakey.KeyFactory;
import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Collection;
import java.util.Optional;

public class MainProgram extends ProgramUnit {

    // DATAKEYS BEGIN

    public final static DataKey<Optional<String>> PROGRAM_NAME = KeyFactory.optional("programName");


    // DATAKEYS END

    public MainProgram(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    @Override
    public String getCode() {

        var code = new StringBuilder();

        var programName = get(PROGRAM_NAME).orElse(null);

        // Only write header if name is present
        if (programName != null) {
            code.append("PROGRAM " + programName).append(ln());
        }

        // Write closing
        code.append("END");
        if (programName != null) {
            code.append(" PROGRAM ").append(programName);
        }
        code.append(ln());

        return code.toString();
    }
}
