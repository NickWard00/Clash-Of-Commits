public WinCommitGame implements WinGame {
  private boolean winGame;
  private int highScore;
  private WinScene winScene;
  private GameScene gameScene;
  public WinCommitGame() {
    winGame = false;
  }

  public int determineWin() {
    //will replace with constant WINNING_SCORE once implemented
    if (score > 100) {
      //will replace with constant WIN_GAME once implemented
      winGame = true;
      return winGame;
    }
    return winGame;
  }

  public void setHighScore(Score addScore) {
    highScore += addScore;
  }

  public Score getHighScore() {
    return highScore;
  }

  public WinScene showWin() {
    winScene = new WinScene();
    return winScene;
  }
}