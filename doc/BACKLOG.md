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
2. 
3.

## Use Cases
Mayari: 
1. Allow user the option to upload a .PNG to change personalize their character's sprite 
2. Make a MapParser class to extract information from .csv file to render original game setting 
3. Implement a specific power-up for increasing hero's speed 
4. Implement error handling in the controller by catching exceptions and displaying messages to the user 
5. Implementing a "collision" method (likely in the GameMap class) to alert whenever two Entity's collide 
6. Keep track of information about a hero's number lives, health points, number coins, etc. in Hero class 

Melanie: 
1. Start Screen Implementation (language select)
2. Players can change CSS in the settings popup
3. Implement settings popup (allows return to main menu, saving current game, etc.)
4. Implement HUD that dynamically updates during play
5. Implement moving screen (background objects, block objects, etc.)
6. Implement game pausing

Nick:
1. Parse in parameters from resource file based on heroes, enemies, and other entities
2. Parse in Level Parser from CSV (make it so that levels are generated in code, but created from resource/csvs)
3. Create Entity superclass which enemies hero classes can be made and determined using reflection
4. Keep track of positions of each entity in a class that can be given to the view
5. Make a EntityView Class that based on the entity state, displays the corresponding photo on screen and puts the entity at the corresponding place in space
6. Class with all types of enemy behavior, but can be accessed from resource file using reflection

James: 
1. Implement upgrades to attacker, defender, etc
2. Determining and implementing how specifically to win or lose a particular game type
3. Implement key binds for the game
4. Implement logic to track status/interactions within the game
5. Display information about a game
6. Implement selection settings at the beginning of the program

Nicki:
1. Create Enemy superclass (extends Entity) which can instantiate specific enemy type classes through reflection
2. Enemy movement and logic implementation
3. Create data files for default enemy attributes of all enemy types
4. Create data files for levels/environments which can be parsed to generate maps and correct sprites
5. Backend testing - focused on testing for enemies
6. Create Ability superclass which can instantiate specific ability/power-up classes through reflection
