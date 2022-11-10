# Hero API

#### Mayari Merchant 


### Overview

DESIGN GOALS:
- Versatile so as to be adapted between game types that use 
different kinds of heroes 
- Manages all information that connects Hero logic to general 
game logic 

HOW IT PROVIDES FOR EXTENSION:
- The Hero class already extends Entity, and there is potential 
to have different types of Hero sub-classes based on different 
types of Hero abilities. 

CLASSES:
- Hero (superclass)
- Hero Types (subclasses) -> refactoring, testing, reflection, etc. 

DETAILS:
- This API manages the different things that the game can 
do to interact with the Hero. For example, when in the game 
there is a collision between an Enemy's bullet and a Hero, 
the game will call hero.takeDamage(), which will update all 
Hero information based on that type of hero's specifications 

CONSIDERATIONS:
- 