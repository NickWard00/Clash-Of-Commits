interface Upgrade {
  //method that will upgrade the amount of HP of the entity or enemy based on the level of the upgrade
  void upgradeHP(int addHP, int level);

  //method that will upgrade the attack of the enemy or entity based on the level of the upgrade
  void upgradeAttack(Attack attack, int level);

  //method that will upgrade the movement of the enemy or entity based on the level
  void upgradeMovement(Movement movement, int leve);

  //method that will upgrade the ability/power up of the entity
  void upgradeAbility(Ability ability, int level);

  //method that will be used to get and show the level of the upgrade
  Level showLevel();

}