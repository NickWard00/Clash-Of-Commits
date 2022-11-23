package ooga.model.state;

import java.util.HashMap;
import java.util.Map;

public enum MovementState {

    STATIONARY("STATIONARY", 0),
    MOVING("MOVING", 1);

    private String movement;
    private int speedConverter;
    private static Map<String, MovementState> movementStateMap = new HashMap<>();

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

    public int getSpeedConverter() { return speedConverter; }

    public String getMovementString() { return movement; }

    public Map<String, MovementState> getMovementStateMap() { return movementStateMap; }

}
