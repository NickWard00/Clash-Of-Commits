package ooga.model.state;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nicki Lee, Melanie Wang
 */
public enum DirectionState {
    NORTH_STATIONARY("NORTH_STATIONARY",0,0){
        @Override
        public DirectionState oppositeDirection() { return SOUTH_STATIONARY; }
    },
    EAST_STATIONARY("EAST_STATIONARY",0,0){
        @Override
        public DirectionState oppositeDirection() { return WEST_STATIONARY; }
    },
    SOUTH_STATIONARY("SOUTH_STATIONARY",0,0){
        @Override
        public DirectionState oppositeDirection() { return NORTH_STATIONARY; }
    },
    WEST_STATIONARY("WEST_STATIONARY",0,0){
        @Override
        public DirectionState oppositeDirection() { return EAST_STATIONARY; }
    },

    NORTH("NORTH", 0, -1) {
        @Override
        public DirectionState oppositeDirection() { return SOUTH; }
    },
    EAST("EAST", 1, 0) {
        @Override
        public DirectionState oppositeDirection() { return WEST; }
    },
    SOUTH("SOUTH", 0, 1) {
        @Override
        public DirectionState oppositeDirection() { return NORTH; }
    },
    WEST("WEST", -1, 0) {
        @Override
        public DirectionState oppositeDirection() { return EAST; }
    };

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


    // TODO: implement a record to cover the public getters

    public String getDirection() { return direction; }

    public List<Integer> getVelocity() { return Arrays.asList(xVelocity, yVelocity); }

    public static Map<String, DirectionState> getDirectionStateMap() { return directionStateMap; }

    public abstract DirectionState oppositeDirection();

}
