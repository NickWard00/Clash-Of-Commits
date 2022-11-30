# OOGA BACKLOG
### Team Number 6
### Nick Ward, Nicki Lee, Mayari Merchant, James Qu, Melanie Wang

Our project is a side scroller game where you play as a programmer who must traverse
the map, avoiding and defeating "bad coding habit" enemies. The hero programmer can 
activate various "good coding habit" power-ups and abilities to help them reach the
end of the map.

## Base Project Requirements
1. Player can select 4 languages in the start screen.
2. Player can change between 3 appearance styles in the settings popup.
3. (Basic Extension) Player can either load a new game or load a previous game in the game selection screen.
4. (Basic Extension) Player can save their game at any point and load their save files later (see number 5)
5. (Challenging Extension) Player can save and load game data using an online database/web server.
6. Player can see information about a game when loading (name, author, description, icon)
7. Player can select any game repeatedly without rerunning the program.
8. Player can pause the game.
9. The game displays a HUD with status information (ex. score, high score)
10. The game keeps track of high scores.

## Game Specific Features
1. Power-ups and/or rewards when the enemies are defeated 

## Use Cases
* Sprint 2 Todos:

* Types of Games:

Our normal game (adventure mode)
Bullet Hell- One long thin map w just a list of enemies to see how far hero can get (requires tracking of distance)
Use a distance tracker (track blocks moved)
Tower defense (player only allowed to move up/down, enemies constantly come towards player)

* Refactoring:
Refactor controller to avoid static things (all of the entities, collisions, attacks)
Put slots into resource files (so that maps can be easily chosen)
Make collision translation more extendable

* New Features:
Pause button
Working attacks (player and enemy)
Settings screen CSS etc.
Saving/Loading Game
Different movement for enemies (perhaps declared in entity map??)
Connect HUD to score, health
Change map to include bug enemy and some other blocks
A Powerup/Upgrade (heart)
Enable hero to use both types of attacks (short and long) instead of just one as it is now
Enemy attacks? (at discrete/random intervals?) 
Destroyable bushes? (replace map perimeter with tree stumps)
Win Conditions (easily associated with different maps in resource files)

* Error Handling: 
ErrorHandler class in Controller, View, → errors get propagated up to the Controller, which initializes an ErrorView object, which displays errors
 
* Testing:
Keep implementing tests
