## OOGA API Changes
### Team 6
### Names: Nick Ward, Melanie Wang, Nicki Lee, Mayari Merchant, James Qu


#### API #1: GameState

* Method changed: determineWin()

    * Why was the change made?
      The change was made so that we do not have to pass the score in as a parameter so that it would lead to more abstraction in the code as the game state now just stores the score in there instead.

    * Major or Minor (how much they affected your team mate's code)
      Minor as this did not really affect many changes in the other classes as the score would just not be passed in as a parameter

    * Better or Worse (and why)
      Better as it allowed for more abstraction to occur and let more game states to be implemented smoothly.


* Method changed: updateScore()

    * Why was the change made?
      This change was made so that we could store the score in the game state class instead of passing it around many different classes in the view.

    * Major or Minor (how much they affected your team mate's code)
      Minor change as it just stores the score in the game state API and does not really affect other aspects of the code.

    * Better or Worse (and why)
      Better as it allowed the game state class to contain the score in it and use it within its own class to determine the win and lose conditions.


#### API #2:
PowerUps and Collisions among them

* Method changed: activatePowerUp() in PowerUp class

    * Why was the change made?
      The change was made to implement a power up as its own separate class instead of as either an entity or an obstacle.

    * Major or Minor (how much they affected your team mate's code)
      Minor change as most of the design was implemented beforehand so that it was easily extended and added on easily

    * Better or Worse (and why)
      It was for the better as it allowed power ups to be implemented completely on themselves and have their own features rather than have features of another class.


* Method changed: power up collision handler (collide() in EntityPowerUpCollision class)

    * Why was the change made?
      This change was made to allow for power ups to be able to collide with specifically only the main hero and allows the power up to

    * Major or Minor (how much they affected your team mate's code)
      Minor as it simply extended the design implementation that was set in place before.

    * Better or Worse (and why)
      It was for the better as it allowed the implementation to not have power ups do their own methods rather than use the methods of other classes that would not be used instead.


#### API #3: Save/Load Game
Adding web save/load methods (update and readData in FireBase, and loadGameFromWeb/saveGameToWeb in controller)

* Method changed: update/readData

    * Why was the change made? We added a challenging extension (saving and loading to the web) and then realized we needed to add extra methods to cover this new functionality.

    * Major or Minor (how much they affected your team mate's code): Minor, as we were adding onto existing infrastructure (saving and loading locally).

    * Better or Worse (and why): Better, since we now have the ability to save to the web!


* Method changed: loadGameFromWeb/saveGameToWeb

    * Why was the change made? We needed the controller to be able to tell the FireBase when to save/load (when the web saving slot is clicked)

    * Major or Minor (how much they affected your team mate's code) Minor, we just created similar methods to the local saving methods. Didn’t change any existing functionality.

    * Better or Worse (and why): Better because it creates new functionality while building upon our original API. 

