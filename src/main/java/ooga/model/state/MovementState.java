package ooga.model.state;

import java.util.HashMap;
import java.util.Map;

public enum MovementState {
    STATIONARY("STATIONARY", 0),
    MOVING("MOVING", 1),
    ATTACK("ATTACK",0),
    SPRINTING("SPRINTING", 2);

    private String movement;
    private int speedConverter;
    private static Map<String, MovementState> movementStateMap = new HashMap<>();

    /**
     * Constructor for MovementState
     * @param movement String representation of movement state
     * @param speedConverter int representation of the speed of the entity
     */
    MovementState(String movement, int speedConverter) {
        this.movement = movement;
        this.speedConverter = speedConverter;
    }

    static {
        for (MovementState state : MovementState.values()) {
            movementStateMap.put(state.movement, state);
        }
    }

    // TODO: implement a record to cover the public getters

    /**
     * Gets the speed conversion for that movement state
     * @return int speed conversion (0 or 1)
     */
    public int getSpeedConverter() {
        return speedConverter;
    }

    /**
     * Gets the movement string
     * @return
     */
    public String getMovementString() {
        return movement;
    }

    /**
     * Gets the movement state map
     * @return Map of movement states
     */
    public Map<String, MovementState> getMovementStateMap() {
        return movementStateMap;
    }

}
