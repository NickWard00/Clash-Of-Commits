# Save Game API

#### Mayari Merchant


### Overview
Design Goals:
1. Identically applicable to different game types 
2. Helps protect raw data structures from being passed 
between classes 

How it provides for extension:
There will be a CSVFileHandler and SimFileHandler subclasses 
to deal with each type of file uploaded to the game. 

Classes:
CSVFileHandler 
SimFileHandler

Details:
API specifies methods for initial game state upload, 
sets the different properties correctly using user 
specifications and based on the type of game being played. 

Considerations:
The file format for both CSV and sims will have to be 
finalized before building the upload API 