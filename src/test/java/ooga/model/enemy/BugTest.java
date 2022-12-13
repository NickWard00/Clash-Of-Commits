package ooga.model.enemy;

import ooga.controller.EntityParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BugTest {

    private EntityParser bugParser = new EntityParser("TestBug", new String[]{"Bug", "0", "0"});

    @Test
    void bugTest() {
        Bug bug = new Bug(bugParser.getAttributeMap());
        assertInstanceOf(Bug.class, bug);
        assertInstanceOf(Enemy.class, bug);
    }

}