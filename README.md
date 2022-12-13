ooga
====

This project implements a player for multiple related games.

Names: Nick Ward, Nicki Lee, James Qu, Melanie Wang, Mayari Merchant


### Timeline

Start Date: 11/02/22

Finish Date: 12/14/22

Hours Spent: ~330

### Primary Roles
Nick Ward: Did all of the parsing, saving and loading of files (backend), error handling, and also was key in model, view, and controller method design and implementation.

Melanie Wang: Did the majority of the GUI frontend design and implementation excluding the win/lose screens. Also connected saving/loading to the web via FireBase, and drew all the game assets.

Nicki Lee: Was responsible for enemy and hero attacks, powerups, and detecting those specific collisions. Also created most of the model structures.

Mayari Merchant: Worked on error handling and cheat keys. Supported Nicki and James on collision detection. 

James Qu: Was responsible for obstacles and handling their collisions with entities. Also played roles in the frontend (scoring).

We collectively wrote tests together. Our testing coverage is ~85% (at least on our laptops), and we've included a photo
as proof. 
### Resources Used
All images were drawn in Procreate illustration software by Melanie Wang.

Music (potentially used): (all free use)

Start Screen - tEh (r0x!) by Mr. Spastic

Level 1 - Levels by Gumbel

Level 2 - 8 Bit Raceway - Wizwars

Sound Effects: (all free use)

Running on Ground -Disagree on freesound.org

### Running the Program

Main class: Main

Data files needed: 

To start the project/test the project: everything in the repository except the doc folder

Errors handled:
* Trying to parse bad data files
* If the game enters a state where data structures are not initialized
* Trying to get data files that do not exist
* Trying to load saves that contain corrupt/no information
* All errors are thrown as IllegalStateExceptions and are caught in the controller and displayed to the user
* The key along with each of the errors corresponds to a key in the ResourceBundle so that errors can be displayed in different languages

Cheat Keys:

Features implemented:
1. Language Selection- player can select between 4 languages.
2. Loading Saves - player can load previously saved files
3. Saving the game - player can save a game (keeping track of player position, health, score, and enemies left)
4. Saving and Loading Online - player can save and load a game to an online database, meaning if someone plays the game and saves it online, another player can play their save.
5. Player can choose between several game types when playing.
6. The player can attack enemies and kill them.
7. The enemy can attack players and kill them.
8. All entities can move around the map.
9. The player can destroy blocks (example, bushes) that are marked as destroyable in the simulation files.
10. The player and enemies cannot walk through blocks (example, trees) that are marked as immovable.
10. There is a play/pause button that stops player and enemy actions.
11. There is an about button that gives the player movement instructions.
12. The player can toggle between three CSS display options (default, dark, and snowy)
13. The player can quit to title and start a new game.
14. The player can make use of various cheat keys to make the game easier to play.
15. The player can heal through the acquisition of powerups.

### Notes/Assumptions

Assumptions or Simplifications:
* We assume the coder will not try to implement a map with
a metric ton of blocks or enemies. While our game can handle quite large maps and more than a handful of enemies,
at a certain point it will start to affect game performance.

Interesting data files:
* Easter egg: The main heroine is wearing a hawaiian shirt, courtesy of Professor Duvall's Fashion Choices.
* Easter egg: The two enemies implemented in our game are named after things you don't want to see in your code:
bugs and magic values. 

Known Bugs:
* If you try really, really hard, you can glitch out of the boundaries of the game.
* In Bullet Hell, if you collide with the enemies enough times, you will get launched out of the map.
* If you approach an enemy, and it starts firing, and then you quit the game and restart a new game, the enemy will now
be invisible. We tried to fix this for a long time, but couldn't quite get it in time.

Challenge Features:
Our map loading system is extremely flexible and is done by reading resource files. We were able to load the entire
Legend of Zelda map into our game just by uploading all the textures and specifying their statuses in a simulation file. 
Along that same line, it is easy to customize the hero and enemies (health, movement, etc.) as this behavior is also
done in files. 
We have also implemented extra features that make our game more enjoyable to play like music and sound effects.


### Impressions
We had a lot of fun coding this assignment. Right guys?
Overall, I think our group enjoyed this assignment because we had a lot of creative freedom with it. We also lucked out in the random team selection
because everyone contributed pretty regularly to our game's development. I think in order to improve this assignment, you should ~~behead~~ penalize any students that don't do their fair share of work.
Also, the assignment is a LOT of work. We had everyone working regularly, pulling an easy 15-20 hours a week and still
we may not have completed EVERYTHING on your assignment checklist. Especially that 85% testing coverage requirement...please revise that to around 50% like the previous assignments for the sake of world peace.

Extendable areas in the future:
1. Creation of more game areas - we talked about incorporating blocks on the maps such as cave blocks, that when stepped on would move the player onto a new map.
2. Addition of more enemy types - we had plans for other enemies as well, such as infinite loops that teleported you around the map. 
3. Diverse powerups - we could add more powerups to the game other than just healing ones, like ones that increase the damage you do, or make you invincible for a period of time.


