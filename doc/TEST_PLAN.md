# Testing

## Strategies

## Scenarios

James Qu:
* Testing for when the upgrade is at max level and user tries to continue to upgrade the entity
    * Sad test case that will throw an exception window saying that the entity can't be upgraded anymore
    * Exception window will be used verify testing worked
* Testing for when the game is won
  * Happy test case that would show if the game is won or not and could be through the game winning screen or other 
  game winning mechanisms
* Testing for when an upgrade is successfully used on an entity
  * Happy test case that would show if the upgrade went through on the character or not and if their
stats increased as expected

Mayari Merchant: 
* Testing for what should happen on different cases with file upload 
  * Happy case: correct file uploaded --> information is parsed as expected 
  * Sad cases: incorrect file type, or no file uploaded --> catch exceptions and handle them properly 
  * Will try calling different FileHandler methods and check their output 
* Frontend testing to ensure that buttons to upload files, pause, select game mode, etc. work 
  * Happy case: user knows what to do, selects buttons in the right order, and they lead to the logical next step 
  * Sad case: user presses buttons in an unexpected manner/order and game behaves erratically as such without feedback to user 
  * Sad case: user presses buttons properly, but the buttons don't function properly 
  * Using GUI testing to simulate different button-pressing patterns and checking their output 
* Testing what happens when the user tries to save game to file 
  * Happy case: game in its current state is saved to a file 
  * Sad case: game is not saved to file, game's saved state is not up-to-date
  * Using GUI testing to see what happens with the file save UI option. 
  * Loading a simulation from this saved file and comparing the states between the two versions 

Melanie Wang:
* Test for when different language buttons are clicked
  * Happy test to ensure the next screen's buttons are displaying the correct texts.
* Test to make sure the GUI is properly updating
  * Sad test to make sure the user cannot get two of the same type of upgrade applied at once
  * Happy test to see that when injured the player's health depletes the correct amount
* Test for making sure character movement is accurate
  * Happy test case to make sure character coordinates are in the right place after moving a certain amount
  * Sad test to make sure character cannot move out of bounds