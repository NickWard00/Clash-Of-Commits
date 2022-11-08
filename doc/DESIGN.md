# OOGA Design Final
### Clash of Commits

## Team Roles and Responsibilities

 * Mayari Merchant

 * James Qu

 * Nick Ward

 * Nicki Lee: 

 * Melanie Wang: frontend, sprite/asset design


## Design goals

#### What Features are Easy to Add

## High-level Design

Summary:
A side scroller game where you play as a programmer who must traverse the map, 
avoiding and defeating "bad coding practice" enemies. The hero programmer can
activate various "good coding practice" power-ups and abilities to help them 
reach the end of the map.

#### Core Classes
Hero
Ability
Enemy
AbilityView
EnemyView
Game
Controller
GameScreen

## Subclasses
Extends Enemy - Bug, MagicValue, Static, Switch, SetGet, InfiniteLoop, MergeConflict
Extends Ability - Testing, Reflection, Refactoring, OfficeHours, Polymorphism, ExceptionThrower

### Abilities - 

Refactoring:
Tower Defense Version: (Single use) The Refactorer deals massive damage in one lane of the board.
Side Scroller Version: powerful long range laser-like attack that can only be used once

Office Hours:
Tower Defense Version: (Single use) The TA pushes all enemies x squares backward.
Side Scroller Version: defensive ability that makes hero invincible for a few seconds

Polymorphism:
Tower Defense Version: The Polymorpher shoots in three lanes at a time.
Side Scroller Version: a close range attack that damages enemies in all directions around the hero

Reflection:
Tower Defense Version: Has lots of HP, acts as a shield for other heroes.
Side Scroller Version: basic long range attack

Testing:
Tower Defense Version: The Tester is the most basic hero and shoots in one lane.
Side Scroller Version: basic short range attack

Exception Thrower:
Tower Defense Version: (Single use) The exception thrower acts as a land mine and explodes with a certain AOE when stepped on by an enemy
Side Scroller Version: sets a land mine

### Enemies 
Magic Values:
Long range attacker, shoots spells.

Switch Cases:
Tower Defense Version: Switches lanes at random.
Side Scroller Version: ???

Setter/Getter:
Tower Defense Version: Gets rid of the first hero it touches, but has relatively low health.
Side Scroller Version: Maybe a type of enemy that always comes in a pair?

Merge Conflict (boss battle?):
Tower Defense Version: Disables? Destroys? some heroes.
Side Scroller Version: When the enemy is killed, it explodes and damages the hero if nearby?

Bug:
The most basic short range enemy.

Infinite Loop:
Keeps replicating itself every x seconds.

Static Variable:
High HP, but moves slowly.


## Assumptions that Affect the Design

#### Features Affected by Assumptions


## Significant differences from Original Plan


## New Features HowTo

#### Easy to Add Features

#### Other Features not yet Done

