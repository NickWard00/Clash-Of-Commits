# Make Enemy API

#### Nicki Lee


### Overview

DESIGN GOALS: 
- To have a flexible design to make implementing new enemy types easier 
- Use reflection

HOW IT PROVIDES FOR EXTENSION:
- Every enemy type will extend the Enemy superclass which will contain
useful common methods for all enemies such as takeDamage(). Generating
enemies will be done through reflection to make the design flexible for
future new enemy types and changes to the classes. 

CLASSES:
- Enemy (superclass)
- Enemy Types (subclasses) -> Bug, MagicValue, Switch, GetSet, Static, 
InfiniteLoop, MergeConflict

DETAILS:
- This API manages the creation of enemies of all types. There will be an 
Enemy superclass with specific enemy type subclasses. Generating enemies
involves calling the subclass constructor as well as setting the correct
attributes (HP, movement rules, sprite, etc).

CONSIDERATIONS:
- The design of the Enemy superclass must be able to apply to all enemy 
types which will have varying movement rules, attributes, attack types
and abilities. We should be careful about how we implement this so as 
to not restrict the types of enemies we can create in the future. 
