# Overview:
The design goals for this API is to allow for an easier implementation of different upgrades
and be able to separate the Model and the View classes effectively. Different upgrades will be easier
to handle because of this implementation.

The class allows for extension since many types of upgrades can be implemented in different classes
through subclasses.


# Classes:
Upgrade class will be used as an interface


# Details
The API handles any upgrade logic that the view classes attempts to do. Thus, when the view class
clicks an upgrade icon, then it will send the logic to the Upgrade API to handle the upgrade.

# Considerations
Some considerations would be what type of upgrades to include here since some might upgrade many different
elements at once and some might upgrade different types of attacks or animations or etc.