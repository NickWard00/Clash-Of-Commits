package ooga.model.enemy;

import java.util.List;
import java.util.Map;

public class Bug extends Enemy {

    /**
     * Constructor for the Bug subclass
     * */
    public Bug(Map<String, String> attributes, Map<Integer, List<String>> states) {
        super(attributes, states);
    }
}
