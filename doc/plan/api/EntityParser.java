public class EntityParser {
    // path to the resource file
    private String path;
    // name of the entity
    private String name;
    // map of attributes
    private Map<String, Integer> attributes;

    // constructor, using path to the resource file as a parameter
    public EntityParser(String path) {
        this.path = path;
        this.attributes = new Map<String, Integer>();
        this.parse(); // Parse will handle the sim file and fill the attributes map
    }

    // get the name of the entity
    public String getImageForView() {
        return pathToImage; // Or is there a better way to pass the image around?
    }

    // get the list of attributes
    public Map<String, Integer> getAttributes() {
        return this.attributes; // Attributes being: entity type, name, HP, position, state, etc...
    }

    // get the list of relationships
    public String entityType() {
        return entityType;
    }
}
