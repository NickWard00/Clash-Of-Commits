package ooga.model.state;

import java.util.HashMap;
import java.util.Map;

/**
 * I'm not sure how we'll implement movement yet, but just some skeleton code for now. We can build off/use this next week.
 * */
public enum MovementState {

    STATIONARY("stationary", 0),
    MOVING("moving", 1);

    private String movement;
    private static Map<String, MovementState> movementStateMap = new HashMap<>();

    MovementState(String movement, int speedConverter) { this.movement = movement; }

    static {
        for (MovementState state : MovementState.values()) {
            movementStateMap.put(state.movement, state);
        }
    }


    /** Next week I'll implement a record to cover the public getters */

    public String getMovement() { return movement; }

    public Map<String, MovementState> getMovementStateMap() { return movementStateMap; }

}
