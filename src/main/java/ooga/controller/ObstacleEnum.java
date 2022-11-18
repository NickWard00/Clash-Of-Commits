package ooga.controller;

public enum ObstacleEnum {

  BUSH("BUSH", 0, "DestroyableWall"),
  NORMAL_GRASS("NORMALGRASS", 1, "Feature"),
  STUMP("STUMP", 2, "ImmovableWall"),
  WINTER_GRASS("WINTERGRASS", 3, "Feature");

  private String imageType;
  private String obstacle;
  private int parserValue;

  ObstacleEnum(String image, int value, String obstacle) {
    this.imageType = image;
    this.parserValue = value;
    this.obstacle = obstacle;
  }

  public String getObstacle() {
    return this.obstacle;
  }
}
