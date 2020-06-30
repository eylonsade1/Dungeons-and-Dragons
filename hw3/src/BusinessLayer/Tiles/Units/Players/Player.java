package BusinessLayer.Tiles.Units.Players;
import BusinessLayer.Resources.Health;
import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.Tiles.Units.Enemies.Enemy;


// players in the game are defined as unit tiles
public abstract class Player extends Unit  {

    // each player will start the game at the first player level and zero experience point
    private final static int startExperience = 0;
    private final static int startPlayerLevel = 1;

    protected int experience;
    protected int playerLevel;

    // each player type has a different special ability that will be used in combat
    // each ability will be defined by a name, the amount of damage it causes
    // and the range of distance that it can be used for
    protected String abilityName;
    protected int abilityDamage;
    protected int abilityRange;

    public Player(Position position, String name, Health playerHealth, int attackPoints, int defencePoints) {
        super('@', position, name, playerHealth, attackPoints, defencePoints);

        this.experience = startExperience;
        this.playerLevel = startPlayerLevel;
    }

    // todo : add comment
    public void playerLevelUp() {
        this.experience -= 50 * playerLevel;
        this.playerLevel += 1;
        this.health.healthLevelingUp(playerLevel);
        this.attackPoints += 4 * playerLevel;
        this.defensePoints += playerLevel;
    }

    @Override
    public String kill(Enemy enemyKilled) {
        int experienceGained = enemyKilled.getExperienceValue();
        String killSummary = enemyKilled.getName() + "died. " + this.name + " gained " + experienceGained + "experience.\n";
        this.experience += experienceGained;
        // todo : remove enemy from game - how ?

        this.setPosition(enemyKilled.getPosition());
        return killSummary;
    }

    public Position getPlayerPosition(){return this.position;}

    @Override
    public String kill(Player player) {
        return "";
    }

    // once a player dies it is represented by 'X' on the game board and the game ends
    public boolean died() {
        this.tileType = 'X';
        return true;
        // todo: print screen after death with player info and 'UI.Game Over'
    }

    // abstract functions: all players- todo finish comment
    public abstract String castAbility();
    public abstract void gameTick();
    public abstract void uponPlayerLevelUp();

}
