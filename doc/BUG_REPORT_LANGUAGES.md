## Description

After starting a new game, the HUD does not include the new language chosen. It still continues to be in English

## Expected Behavior

I would expect the language to be chosen correctly as it works on the next screen. The hud should include the score, but in a new language that was chosen at the beginning.

## Current Behavior

The game continues to be in english, even after choosing another language

## Steps to Reproduce

Provide detailed steps for reproducing the issue.

1. Open game
2. Select German, Spanish, or Simlish
3. Open a new game or load a save
4. The HUD continues to be in english

## Failure Logs

org.opentest4j.AssertionFailedError:
Expected :This game is made by Team 6:  Nicki Lee, Mayari Merchant, James Qu, Melanie Wang, and Nick Ward.
Actual   :Dieses Spiel wurde von Team 6 entwickelt: Nicki Lee, Mayari Merchant, James Qu, Melanie Wang und Nick Ward.
<Click to see difference>

## Hypothesis for Fixing the Bug

If I created a new HUD test that sets the language to German, it will fail the testAboutPopup() test.
If I pass the view the correct label bundle, which passes it to the MainGameScreen and then finally passes it to the HUD and making it a global variable, it will work as intended.