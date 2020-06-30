package BusinessLayer.Tiles.Units.Enemies;

import BusinessLayer.Tiles.Units.Health;
import BusinessLayer.Tiles.Position;
//todo comment

public class Trap extends Enemy {

    private final static int startTicksCount = 0;

    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;

    public Trap(char tileType, Position position, String name, Health trapHealth, int attackPoints, int defensePoints, int experienceValue, int visibilityTime, int invisibilityTime) {
        super (tileType, position, name, trapHealth, attackPoints, defensePoints, experienceValue);

        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
        this.visible = true;

    }

    @Override
    public boolean gameTick(Position playerPosition) {
        boolean playerInRange = false;
        this.ticksCount += 1;
        if (visible && ticksCount == visibilityTime) {
            this.ticksCount = 0;
            this.visible = false;
        }
        else if (!visible && ticksCount == invisibilityTime) {
            this.ticksCount = 0;
            this.visible = true;
        }
        if(Range(this.position, playerPosition) < 2) {
            playerInRange = true;
        }
        return playerInRange;
    }

    @Override
    public String describe() {
        return name + "\t Health: " + health.getHealthAmount() + "\\" + health.getHealthPool() +  "\t Attack: " + attackPoints + "\t Defense: " + defensePoints + "\t Experience Value: " + experienceValue + "\n";
    }

}
