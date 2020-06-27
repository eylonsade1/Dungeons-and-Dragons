package BusinessLayer.Tiles.Units.Enemies;

import BusinessLayer.Resources.Health;
import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Unit;

import java.util.List;
//todo comment

public class Trap extends Enemy {

    private final static int startTicksCount = 0;

    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;

    public Trap (char tileType, Position position, String name, Health trapHealth, int attackPoints, int defensePoints, int experienceValue, List<Unit> allUnitsInRange, int visibilityTime, int invisibilityTime) {
        super (tileType, position, name, trapHealth, attackPoints, defensePoints, experienceValue, allUnitsInRange);

        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
        this.visible = true;

    }

    public void gameTick() {
        this.ticksCount += 1;
        if (visible && ticksCount == visibilityTime) {
            this.ticksCount = 0;
            this.visible = false;
        }
        else if (!visible && ticksCount == invisibilityTime) {
            this.ticksCount = 0;
            this.visible = true;
        }
    }

    @Override
    public String describe() {
        return name + "\t Health: " + health + "\t Attack: " + attackPoints + "\t Defense: " + defensePoints + "\t Experience Value: " + experienceValue;
    }
}
