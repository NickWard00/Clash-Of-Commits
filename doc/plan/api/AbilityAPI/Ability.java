public abstract class Ability {
    // method to activate the ability/powerup
    void activate() {}

    // method to deactive the ability/powerup
    void deactivate() {}

    // method which returns a bool indicating whether or not the ability is active
    Boolean isActive() {}

    // method which returns a bool indicating whether or not this ability can be activated
    // could be false if the ability is limited use, has a cooldown time, etc.
    Boolean canActivate() {}
}