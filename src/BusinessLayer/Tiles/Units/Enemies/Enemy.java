package BusinessLayer.Tiles.Units.Enemies;

import BusinessLayer.Resources.Health;
import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.Tiles.Units.Players.Player;
import BusinessLayer.Tiles.Units.UnitVisited;
import BusinessLayer.Tiles.Units.UnitVisitor;
//todo comment

public abstract class Enemy extends Unit {

    protected int experienceValue;

    public Enemy(char tileType, Position position, String name, Health enemyHealth, int attackPoints, int defensePoints, int experienceValue) {
        super(tileType, position, name, enemyHealth, attackPoints, defensePoints);

        this.experienceValue = experienceValue;

    }

    public int getExperienceValue () {
        return experienceValue;
    }

    @Override
    public String visitAfterKilling(Player playerKilled) {
        String killSummary = playerKilled.getName() + "was killed by " + this.name + ".\n"
                + "You lost.\n";

        playerKilled.died();
        return killSummary;
    }

    @Override
    public String visitAfterKilling(Enemy enemy) {
        return null;
    }

    public String acceptToKill(UnitVisitor visitor){
        return visitor.visitAfterKilling(this);
    }


}
