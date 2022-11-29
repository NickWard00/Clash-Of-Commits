package ooga.controller;

import ooga.model.hero.MainHero;

public class AdventureGameState implements GameState {
  private boolean winGame;
  private boolean loseGame;
  private int score;
  private MainHero hero;

  public AdventureGameState(MainHero hero, int score) {
    this.hero = hero;
    this.score = score;
  }

  @Override
  public boolean determineWin() {
    if (score >= 100) {
      return true;
    }
    return false;
  }

  @Override
  public boolean determineLost() {
    if (hero.getHp() == 0) {
      return true;
    }
    return false;
  }
//
//  @Override
//  public void setHighScore(HighScore addScore) {
//
//  }
//
//  @Override
//  public HighScore getHighScore() {
//    return null;
//  }

//  @Override
//  public WinScene showWin(WinScene scene) {
//    return scene;
//  }
//
//  @Override
//  public LoseScene showLost(LoseScene scene) {
//    return scene;
//  }
}
