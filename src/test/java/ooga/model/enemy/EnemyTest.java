package ooga.model.enemy;

import ooga.controller.EntityParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {
    private EntityParser bugParser = new EntityParser("TestBug", new String[]{"Bug", "0", "0"});
    private EntityParser magicValueParser = new EntityParser("TestMagicValue", new String[]{"MagicValue", "0", "0"});

    @Test
    void testBugType() {
        Bug bug = new Bug(bugParser.getAttributeMap());
        assertEquals(bug.getMyAttributes().get("Type"), "Enemy");
    }

    @Test
    void testMagicValueType() {
        MagicValue magicValue = new MagicValue(magicValueParser.getAttributeMap());
        assertEquals(magicValue.getMyAttributes().get("Type"), "Enemy");
    }

    @Test
    void testBugName() {
        Bug bug = new Bug(bugParser.getAttributeMap());
        assertEquals(bug.getMyAttributes().get("Name"), "TestBug");
    }

    @Test
    void testMagicValueName() {
        MagicValue magicValue = new MagicValue(magicValueParser.getAttributeMap());
        assertEquals(magicValue.getMyAttributes().get("Name"), "TestMagicValue");
    }

    @Test
    void testBugSpeed() {
        Bug bug = new Bug(bugParser.getAttributeMap());
        assertEquals(bug.getMyAttributes().get("Speed"), "50");
    }

    @Test
    void testMagicValueSpeed() {
        MagicValue magicValue = new MagicValue(magicValueParser.getAttributeMap());
        assertEquals(magicValue.getMyAttributes().get("Speed"), "30");
    }

    @Test
    void testBugHP() {
        Bug bug = new Bug(bugParser.getAttributeMap());
        assertEquals(bug.getMyAttributes().get("HP"), "3");
        assertEquals(bug.getHp(), 3);
    }

    @Test
    void testMagicValueHP() {
        MagicValue magicValue = new MagicValue(magicValueParser.getAttributeMap());
        assertEquals(magicValue.getMyAttributes().get("HP"), "3");
        assertEquals(magicValue.getHp(), 3);
    }

    @Test
    void testBugDamage() {
        Bug bug = new Bug(bugParser.getAttributeMap());
        bug.setHP(1);
        bug.changeHp(-1);
        assertEquals(bug.getHp(), 0);
    }

    @Test
    void testMagicValueDamage() {
        MagicValue magicValue = new MagicValue(magicValueParser.getAttributeMap());
        magicValue.setHP(1);
        magicValue.changeHp(-1);
        assertEquals(magicValue.getHp(), 0);
    }

    @Test
    void testBugSprites() {
        Bug bug = new Bug(bugParser.getAttributeMap());
        assertEquals(bug.getMyAttributes().get("Sprites"), "/sprites/bug/");
    }

    @Test
    void testMagicValueSprites() {
        MagicValue magicValue = new MagicValue(magicValueParser.getAttributeMap());
        assertEquals(magicValue.getMyAttributes().get("Sprites"), "/sprites/magic_value/");
    }

    @Test
    void testBugSize() {
        Bug bug = new Bug(bugParser.getAttributeMap());
        assertEquals(bug.getMyAttributes().get("Size"), "30");
        assertEquals(bug.getSize(), 30);
    }

    @Test
    void testMagicValueSize() {
        MagicValue magicValue = new MagicValue(magicValueParser.getAttributeMap());
        assertEquals(magicValue.getMyAttributes().get("Size"), "30");
        assertEquals(magicValue.getSize(), 30);
    }

    @Test
    void testBugAttack() {
        Bug bug = new Bug(bugParser.getAttributeMap());
        assertEquals(bug.getMyAttributes().get("Attack"), "ShortRange");
    }

    @Test
    void testMagicValueAttack() {
        MagicValue magicValue = new MagicValue(magicValueParser.getAttributeMap());
        assertEquals(magicValue.getMyAttributes().get("Attack"), "LongRange");
    }

    @Test
    void testBugAttackRange() {
        Bug bug = new Bug(bugParser.getAttributeMap());
        assertEquals(bug.getMyAttributes().get("AttackRange"), "120");
    }

    @Test
    void testMagicValueAttackRange() {
        MagicValue magicValue = new MagicValue(magicValueParser.getAttributeMap());
        assertEquals(magicValue.getMyAttributes().get("AttackRange"), "180");
    }

    @Test
    void testBugXPosition() {
        Bug bug = new Bug(bugParser.getAttributeMap());
        assertEquals(bug.getMyAttributes().get("XPosition"), "0");
        assertEquals(bug.coordinates().get(0), 0);
    }

    @Test
    void testMagicValueXPosition() {
        MagicValue magicValue = new MagicValue(magicValueParser.getAttributeMap());
        assertEquals(magicValue.getMyAttributes().get("XPosition"), "0");
        assertEquals(magicValue.coordinates().get(0), 0);
    }

    @Test
    void testBugYPosition() {
        Bug bug = new Bug(bugParser.getAttributeMap());
        assertEquals(bug.getMyAttributes().get("YPosition"), "0");
        assertEquals(bug.coordinates().get(1), 0);
    }

    @Test
    void testMagicValueYPosition() {
        MagicValue magicValue = new MagicValue(magicValueParser.getAttributeMap());
        assertEquals(magicValue.getMyAttributes().get("YPosition"), "0");
        assertEquals(magicValue.coordinates().get(1), 0);
    }

    @Test
    void testBugDirectionState() {
        Bug bug = new Bug(bugParser.getAttributeMap());
        assertEquals(bug.getMyAttributes().get("Direction"), "SOUTH");
    }

    @Test
    void testMagicValueDirectionState() {
        MagicValue magicValue = new MagicValue(magicValueParser.getAttributeMap());
        assertEquals(magicValue.getMyAttributes().get("Direction"), "SOUTH");
    }

    @Test
    void testBugMovementState() {
        Bug bug = new Bug(bugParser.getAttributeMap());
        assertEquals(bug.getMyAttributes().get("Movement"), "MOVING");
    }

    @Test
    void testMagicValueMovementState() {
        MagicValue magicValue = new MagicValue(magicValueParser.getAttributeMap());
        assertEquals(magicValue.getMyAttributes().get("Movement"), "MOVING");
    }
}