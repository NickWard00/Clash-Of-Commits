package ooga.model.state;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum DirectionState {

    NORTH("NORTH", 0, 1),
    EAST("EAST", 1, 0),
    SOUTH("SOUTH", 0, -1),
    WEST("WEST", -1, 0);

    private String direction;
    private int xVelocity;
    private int yVelocity;
    private static Map<String, DirectionState> directionStateMap = new HashMap<>();

    DirectionState(String direction, int xVelocity, int yVelocity) {
        this.direction = direction;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    static {
        for (DirectionState state : DirectionState.values()) {
            directionStateMap.put(state.direction, state);
        }
    }


    /** Next week I'll implement a record to cover the public getters */

    public String getDirection() { return direction; }

    public List<Integer> getVelocity() { return Arrays.asList(xVelocity, yVelocity); }

    public static Map<String, DirectionState> getDirectionStateMap() { return directionStateMap; }

}
