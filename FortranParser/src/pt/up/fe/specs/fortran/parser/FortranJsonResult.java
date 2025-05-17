package pt.up.fe.specs.fortran.parser;

import pt.up.fe.specs.fortran.ast.FortranContext;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;

import java.util.Map;
import java.util.Set;

public record FortranJsonResult(FortranContext context, Set<String> ids, Map<String, FortranNode> fortranNodes,
                                Map<String, Map<String, Object>> attributes) {
}
