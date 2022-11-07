# Save Game API

#### Melanie Wang


### Overview
Design Goals: 
1. Reduces programming effort

How it provides for extension:

Allows user to create save files from a certain state in a game, which enables easy repetitive user testing.
Pulling the information about a game into a file also allows the user to look at the current values to make sure 
everything is updating correctly

Classes:
GameSaver (interface)

Details:

API handles saving of games (one of the basic extensions)
The API may collaborate with the loading game API in order to save and load games.
The API may be extended to save the files to an online database to allow loading of games from the web. 

Considerations:
The file format for both CSV and sims will have to be finalized before building the save API. We need to know exactly 
what information should be packaged into these files in order to generate an accurate save.



