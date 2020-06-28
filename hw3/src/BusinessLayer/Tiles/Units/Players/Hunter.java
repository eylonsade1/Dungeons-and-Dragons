package BusinessLayer.Tiles.Units.Players;

import BusinessLayer.Resources.Health;
import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Unit;

import java.util.List;

public class Hunter extends Player {

    private int range;
    private int  arrowsCount;
    private int ticksCount;

    public Hunter(Position position, String name, Health playerHealth, int attackPoints, int defencePoints, int range) {
        super(position, name, playerHealth, attackPoints, defencePoints);
        this.range= range;
        this.arrowsCount = 10;
        ticksCount=0;

    }

    @Override
    public String castAbility() {
        return null;
    }

    @Override
    public void gameTick() {

    }

    @Override
    public void uponPlayerLevelUp() {
        this.arrowsCount += 10*playerLevel;
        this.attackPoints += 2*playerLevel;
        this.defensePoints += playerLevel;
    }

    @Override
    public String describe() {
        return null;
    }
}
