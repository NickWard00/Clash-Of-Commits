# Ability API

#### Nicki Lee


### Overview

DESIGN GOALS:
- To make implementing new abilities/power-ups easier and to reduce duplicate code

HOW IT PROVIDES FOR EXTENSION:
- Every type of hero ability will extend the Ability superclass which will contain
methods like activate() and deactivate() that all abilities will use. It could also
potentially be extended to be used for enemy abilities as well. 

CLASSES:
- Ability (superclass)
- Ability Types (subclasses) -> Test, Refactoring, OfficeHours, Polymorphism, 
Reflection, ExceptionThrow

DETAILS:
- This API handles to activation and deactivation of abilities/power-ups. With the
abstraction of the Ability superclass, the model will be able to manage abilities
without needing to know which ability it's dealing with. 

CONSIDERATIONS:
- In order to make it as flexible as possible, it would be good to try to implement
the Ability handling API in a way that can deal with both hero and enemy abilities.
Additionally, we need to account for the fact that abilities may be very different.
Some will have limited uses while others are unlimited, some will be instantaneous 
while others may last for a certain amount of time or until an event triggers it to 
end, and more. 
