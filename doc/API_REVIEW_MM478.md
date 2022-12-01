I talked with Daniel Feinblatt about our respective APIs. 
I discussed the Collision and CollisionHandler 
class's methods of my team's Ooga project, looking specifically 
at the different ways to create a new Collision depending 
on which two objects are colliding. Daniel liked this approach, 
given that whatever called the collision would not have to 
deal with figuring out what kinds of objects were colliding. 
However, he mentioned (and I agree) that there is 
still duplicated code using 
this approach, and that there are definitely better 
ways to achieve the same result. 

Furthermore, in looking at Daniel's Deck and Cards APIs, 
I really liked the way they handle errors and exceptions, 
and will be using that in implementing error handling for our 
project, as well.