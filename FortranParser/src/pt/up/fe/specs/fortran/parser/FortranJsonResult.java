package pt.up.fe.specs.fortran.parser;

import pt.up.fe.specs.fortran.ast.FortranContext;
import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.util.SpecsLogs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public record FortranJsonResult(FortranContext context, String firstNode, Set<String> ids,
                                Map<String, FortranNode> fortranNodes,
                                FlangData attributes) {

    public FortranJsonResult(FortranContext context, String firstNode, Set<String> ids, Map<String, FortranNode> fortranNodes, FlangData attributes) {
        this.context = context;
        this.firstNode = firstNode;
        this.ids = ids;
        this.fortranNodes = fortranNodes;
        this.attributes = attributes;

        postProcess();
    }

    private void postProcess() {
        // Detect which nodes are the "parent" node according to node hierarchy and merge attributes with "derived" node
        //attributes.getDerivedId()

        // To avoid being dependent of an order where ids of base classes always appear before than ids of derived
        // classes, built a derived->base id map
        var derivedToBase = new HashMap<String, String>();
        for (var id : ids) {
            // If id has derived class, add relationship to map
            var derivedId = attributes.getDerivedId(id, false);
            derivedId.ifPresent(derived -> derivedToBase.put(derived, id));
        }

        // Merge attributes for existing nodes, so that node processors can have access to all the needed data
        // to build the nodes
        for (var nodeId : fortranNodes.keySet()) {
            var nodeAttrs = attributes.get(nodeId);

            var baseId = derivedToBase.get(nodeId);
            while (baseId != null) {
                //SpecsLogs.info("Merging " + baseId + " attrs into " + nodeId);
                nodeAttrs.merge(attributes.get(baseId));
                baseId = derivedToBase.get(baseId);
            }
        }

        //System.out.println("DERIVED TO BASE MAP: " + derivedToBase);
    }

}
