# Create HUD API

#### Melanie Wang


### Overview
Design Goals: 
1. Reduces programming effort
2. Fosters software reuse

How it provides for extension:
Allows developers to create a HUD that is can be personalized. This can be used in new games or to easily edit
a current game's HUD.

Classes:
CreateHUD

Details:

API handles creation of a HUD (a block in a scene that displays dynamic game information)
This API can be extended (ex. you want to add a new statistic to the HUD) by adding a new method to extract the desired information
and then updating the method that creates the view of the HUD to include this new information.

Considerations:
The size of the HUD needs to be fixed to a certain extent as it needs to be a relatively small compared to the actual 
game screen. Thus, it is limited in that adding too many features will prevent the features from being easily read by
the user. 


