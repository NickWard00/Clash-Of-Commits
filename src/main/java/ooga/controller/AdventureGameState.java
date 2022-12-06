package ooga.controller;

import java.util.Map;
import javafx.stage.Stage;
import ooga.model.hero.MainHero;
import ooga.view.EntityView;

public class AdventureGameState implements GameState {
  private boolean winGame;
  private boolean loseGame;
  private int score;
  private Map<String, EntityView> myViewEntities;
  private MainHero hero;

  public AdventureGameState(Map<String, EntityView> viewEntities) {
    this.myViewEntities = viewEntities;
  }

  @Override
  public boolean determineWin(int score) {
    this.score = score;
    if (score >= 100) {
      return true;
    }
    return false;
  }

  @Override
  public boolean determineLost() {
//    myViewEntities.get("MainHero");
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
//  public WinScreen showWin() {
//
//  }
//
//  @Override
//  public LoseScene showLost(LoseScene scene) {
//    return scene;
//  }
}
