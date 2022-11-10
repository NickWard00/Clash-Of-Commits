# WinGame API

#### James Qu

### Overview
The design goals for this API is to allow for a pretty standard and methodical approach to winning
a game. This allows each game to be able to be implemented separate from other games and not have
to depend on other implementation of other games.

The class allows for extension since many types winning game scenarios can be implemented through
subclasses and other abstractions.

### Classes:
WinGame class will be used as an interface

### Details:
This API handles the situation of winning a game and saving and obtaining the high score that was 
achieved through winning the game. This allows both the Model and the View classes to determine if
the game is won or not.

### Considerations:
Considerations to make are that there could be many different ways of winning a game and how we want
to show the user that they have won the game through a different window, scene, text, borders, colors, etc.

