package BusinessLayer.Tiles.Units.Enemies;

import BusinessLayer.Tiles.Units.Health;
import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.Tiles.Units.Players.Player;
import BusinessLayer.Tiles.TileVisitor;
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
    public String kill(Player playerKilled) {
        String killSummary = playerKilled.getName() + "was killed by " + this.name + ".\n"
                + "You lost.\n";

        playerKilled.died();
        return killSummary;
    }

    @Override
    public String acceptContact(TileVisitor tileVisitor) {
        return tileVisitor.contactWith(this);
    }

    @Override
    public String acceptKill(UnitVisitor visitor) {
        return visitor.kill(this);
    }

    @Override
    public String kill(Enemy enemy) {
        return "";
    }

    public abstract boolean gameTick(Position playerPosition);

}
