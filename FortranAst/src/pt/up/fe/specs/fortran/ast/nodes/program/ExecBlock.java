package pt.up.fe.specs.fortran.ast.nodes.program;

import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.fortran.ast.nodes.program.construct.ExecPartConstruct;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ExecBlock extends FortranNode {

    public ExecBlock(DataStore data, Collection<? extends FortranNode> children) {
        super(data, children);
    }

    public List<ExecPartConstruct> getConstructs() {
        return getChildren(ExecPartConstruct.class);
    }

    @Override
    public String getCode() {
        return getConstructs().stream()
                .map(FortranNode::getCode)
                .collect(Collectors.joining(ln()));
    }
}
