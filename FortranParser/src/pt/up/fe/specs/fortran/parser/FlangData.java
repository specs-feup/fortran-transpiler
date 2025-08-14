package pt.up.fe.specs.fortran.parser;

import pt.up.fe.specs.fortran.ast.nodes.FortranNode;
import pt.up.fe.specs.util.providers.StringProvider;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;

public class FlangData {

    /// STATIC

    private static final Pattern REGEX_VALUE = Pattern.compile("value(<\\w+>)?");
    private static final Pattern REGEX_STMT = Pattern.compile("statement");

    public static Pattern getRegexValue() {
        return REGEX_VALUE;
    }

    public static Pattern getRegexStmt() {
        return REGEX_STMT;
    }

    public static FlangData convert(Map<String, Map<String, Object>> data) {
        var newAttrs = new HashMap<String, FlangAttributes>();

        for (var entry : data.entrySet()) {
            newAttrs.put(entry.getKey(), new FlangAttributes(entry.getValue()));
        }

        return new FlangData(newAttrs);
    }

    /// INSTANCE

    private final Map<String, FlangAttributes> attributes;

    public FlangData(Map<String, FlangAttributes> attributes) {
        this.attributes = attributes;
    }

    public FlangAttributes get(String id) {
        return attributes.get(id);
    }

    /**
     * If the id represents a node that is supported to be a base class of another node, returns the id of the
     * corresponding "derived" node.
     *
     * @param id
     * @return
     */
    public Optional<String> getDerivedId(String id) {

        // If there is already a concrete FortranAst class for this id, there is no derivation
        if (FlangToClass.isClass(getKind(id))) {
            return Optional.empty();
        }

        // Calculate key to the next level
        var key = id.endsWith("-Statement") ? REGEX_STMT : REGEX_VALUE;
        var attrs = getAttrs(id);
        var keys = attrs.getKeys();
        var derivedId = attrs.getOptionalString(key).orElseThrow(() -> new RuntimeException("Could not find key '" + key + "' for id " + id + ": " + keys));

        return Optional.of(derivedId);
    }

    public String getChildId(String id) {

        var currentId = id;

        var derivedId = getDerivedId(currentId).orElse(null);
        while (derivedId != null) {
            currentId = derivedId;
            derivedId = getDerivedId(currentId).orElse(null);
        }

        return currentId;
    }

    public Optional<FlangAttributes> getAttrsOptional(FortranNode node) {
        return getAttrsOptional(node.get(FortranNode.ID));
    }

    public Optional<FlangAttributes> getAttrsOptional(String id) {
        var attrs = attributes.get(id);
        return Optional.ofNullable(attrs);
    }

    public FlangAttributes getAttrs(String id) {
        return getAttrsOptional(id).orElseThrow(() -> new RuntimeException("Id '" + id + "' does not have attributes associated: " + attributes));
    }


    public FlangAttributes getAttrs(FortranNode node) {
        return getAttrs(node.get(FortranNode.ID));
    }

    public List<String> getStringList(FortranNode node, StringProvider key) {
        return getAttrs(node).getList(key, Object::toString);
    }


    public <T> List<T> getList(FortranNode node, StringProvider key, Function<Object, T> converter) {
        return getAttrs(node).getList(key, converter);
    }

    public <T> List<T> getList(FortranNode node, String key, Function<Object, T> converter) {
        return getAttrs(node).getList(key, converter);
    }

    public String getKind(String id) {
        return FortranJsonParser.getKind(id);
    }

    /*
        public boolean hasStmt(Map<String, Object> attrs, FlangName flangName) {
            var stmtName = getStmtAttr(flangName);
            return attrs.containsKey(stmtName);
        }
    */
    public String getStmtAttr(FlangName flangName) {
        return "Statement<" + flangName.getString() + ">";
    }

    public String getString(FortranNode node, String key, FlangName... path) {
        return getOptionalString(node, key, path).orElseThrow(() -> new RuntimeException("Could not find key '" + key + "' in node '" + node.getNodeName() + "' using the path " + Arrays.toString(path)));
    }


    public Optional<String> getOptionalString(FortranNode node, String key, FlangName... path) {
        return getOptional(node, key, path).map(Object::toString);
    }

    public Optional<Object> getOptional(FortranNode node, String key, FlangName... path) {
        var currentId = node.get(FortranNode.ID);

        // Get base attrs
        var currentAttrs = getAttrs(node);


        // Follow path
        for (var nodeName : path) {
            if (nodeName.isStmt()) {
                // Decode kind to statement attribute
                var firstKey = getStmtAttr(nodeName);
                // Get id to stmt
                var stmtId = currentAttrs.getOptionalString(firstKey);
                if (stmtId.isEmpty()) {
                    return Optional.empty();
                }
                // Update attrs to generic statement
                currentAttrs = getAttrs(stmtId.get());
                // Now get id of specific statement
                var specificStmtId = currentAttrs.getString("statement");
                // Update attrs to specific statement
                currentAttrs = getAttrs(specificStmtId);
                continue;
            }

            var pathKey = nodeName.getString();
            var nextId = currentAttrs.getOptionalString(pathKey);
            if (nextId.isEmpty()) {
                return Optional.empty();
            }
            currentAttrs = getAttrs(nextId.get());
        }

        // Return the value
        return currentAttrs.getOptional(key);
    }

    public String getChildId(FortranNode node, FlangName attribute) {
        return getChildId(getAttrs(node).getString(attribute));
    }

    public String getChildId(FortranNode node, String attribute) {
        return getChildId(getAttrs(node).getString(attribute));
    }

    public String getChildId(FortranNode node, Pattern attribute) {
        return getChildId(getAttrs(node).getString(attribute));
    }

    public List<String> getChildrenIds(FortranNode node, FlangName attribute) {
        return getAttrs(node).getStringList(attribute).stream()
                .map(this::getChildId)
                .toList();
    }


}
