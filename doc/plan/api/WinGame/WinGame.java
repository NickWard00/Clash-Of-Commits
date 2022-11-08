interface WinGame {

  //method to determine if the game is won or not
  boolean determineWin();

  //method to show the high score of the win in the game
  void setHighScore(Score addScore);

  //method used to get the high score of the game won
  Score getHighScore();

  //method to switch the type of screen to show in the view
  WinScene showWin();

}