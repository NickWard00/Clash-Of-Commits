# DESIGN Document for Clash of commits: Team 6
## Nick Ward, Melanie Wang, Nicki Lee, James Qu, Mayari Merchant

## Role(s)
Nick:
* Parser implementation, model and view entity creation
* Handled many controller methods to pass information between view and model
* Implemented alternative game modes
* Error Handling

Melanie:
* Mainly frontend GUI design and implementation, more minor roles in the controller
* Implemented saving and loading files to the web
* Drew game assets

Nicki:
* Mainly backend model implementation, minor roles in the controller
* Responsible for implementing attacks, enemy logic, movement and direction states, powerups, and worked with Mayari and James on collisions

Mayari:
* Helping initialize getting the map view on the screen
* Re-factor of long classes such as the Controller into separate modular classes
* Worked on movement and direction for cheat key implementation, collisions with James and Nicki

James:
* Roles in the backend in model classes with obstacles and helped in handling collisions in general in the model with Mayari and Nicki.
* Also had roles in the controller and front end such as creating game states and dealing with scoring.

## Design Goals
Our goal with this game was to design a scrolling adventure game similar to the flavor of Legend of Zelda, Super Mario Brothers, etc. We aimed to have a player move in four directions around a map, killing enemies while trying to stay alive. More importantly, we tried to create a game that was highly customizable. Our game can be easily extended as the majority of it relies upon external resource files to dictate appearances, rules, and movement. Some examples of this include:
* The Entity class can be extended into various types of enemies. Additionally, any number and type of enemies can be placed on the map via a resource file.
* Attributes such as speed, attack type, hp and more are read in from resource files for all entities and can be changed or switched around easily simply through editing the resource files
* The game map designed to be completely customizable (as long as the images used are square tiles). Any game map can be generated using a CSV file (comprised of numbers which correspond to tile type) and a SIM file (a file specifying which number corresponds to which tile image). To show this, we have a MOD level that is the entire original Legend of Zelda map ported into our game.
* Powerups can be easily extended to create new types of powerups. They can also be placed anywhere on the map with a resource file.
* The game states can be extended to include various other types of game types.
* Reflection is used frequently in our game to make our code as flexible as possible. For example, the code we wrote for collision handling can apply to any two colliding objects regardless of subclass or order passed into the method as long as the objects extend one of our four main superclasses (Entity, Attack, Obstacle, PowerUp) which should cover almost any type of collision that we may want to implement in the future.



## High-Level Design


* Our Entity superclass is extended by two subclasses, Hero and Enemy. Our subclasses MainHero and Link then further extend the Hero class, while Bug and MagicValue extend Enemy.
* Our Collision superclass is extended by subclasses of each type of collision (AttackEntityCollision, AttackObstacleCollision, EntityObstacleCollision, and EntityPowerUpCollision) which are generated through reflection
* Our Attack superclass is extended by our two types of attacks currently implemented (ShortRange and LongRange). Every Entity has one attack type that it’s able to use – the attack type is specified in the Entity’s sim file – and an attack() method which uses reflection to generate the correct type of attack when called. Note, the Hero entity can use both types of attacks if the user presses different keys.
* The PowerUp superclass is currently extended by one subclass, HealPowerUp, which gives the hero one additional hp. While we currently only have one type of power-up, we tried to implement this API  in a way that is easily understandable and extendable for the future. For example, PowerUp has an abstract method, activatePowerUp(Entity entity), so any subclass of PowerUp will need to have an activate method. In our collision handling, when detecting a collision between the hero entity and a powerup, our EntityPowerUpCollision class simply calls “powerup.activatePowerUp(entity).” Therefore, this will be applicable for any future powerup subclass.
* We have two state types in the Model for our entities, MovementState and DirectionState, both of which are implemented using enums. MovementState’s states are Stationary, Moving, Attack, and Sprinting, and each state has a speedConverter value that affects the entity’s movement. Stationary’s speedConverter is 0 since when in this state, we want the entity’s speed to be multiplied by 0 to make it stationary. The speedConverter for Moving is 1 as the entity should move at its regular speed while the converter for Sprinting is 2 to make the entity run faster. DirectionState’s states are North, East, South, and West, corresponding to the direction in which we want an entity to both face and travel. The converters for Direction state are two integer converters which affect the speed along the x axis and along the y axis. For example, for the North state, the xVelocity converter is 0 while the yVelocity is -1. By combining MovementState and DirectionState, we are able to control the speed and direction all entities travel.
* Our obstacle superclass contains many different subclasses of different types of obstacles and can be extended to add more obstacles. The main type of obstacles are walls which are an abstract class, and it implements a block method to block entities and attacks from moving past it. There are also two different types of walls implemented at the moment (DestroyableWalls and ImmovableWalls) which differ in that destroyable walls have hitpoints while immovable walls cannot be removed from the game.
* The game state class in the controller is used to store the score in the game and determine the win and lose conditions of the different types of games. This allows the class to determine the type of end game screen that is shown when the game is won or lost and visualizes the corresponding end game screen up. The MapGameState class is an abstract superclass that has different subclasses that extend it to show the different game types and win conditions that can exist in those game types. These classes can easily be extended to add more game types in it in the future.

## Assumptions or Simplifications
We assume that when the user is creating new files for the game that they create the files in the format of the example files. We also assume that they know to edit the sim file to access the

## Changes from the Plan
We originally had a tower defense game in mind, but then after talking for a bit we decided a scroller would be more interesting and rewarding to implement.
We simplified our design by limiting the amount of save slots that the user can save into, because if they could create many saves displaying the files on the UI would start to become annoying both for us to implement and for the user to navigate to. Additionally, most games published today only allow for a certain number of save slots, so limiting this feature isn’t out of the norm.

## How to Add New Features
* PowerUps: To add a new powerup, one would need to create a new subclass of the PowerUp class with the method activatePowerUp() which holds the logic of the powerup.
* Attacks: To add a new type of attack, one would need to create a new subclass of the Attack class as well as add a sim file with the attack type’s attributes (damage dealt, speed, related sprite, max duration, etc.