package BusinessLayer.Tiles;

import BusinessLayer.Tiles.Units.Health;
import BusinessLayer.Tiles.Units.Enemies.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;
import BusinessLayer.Tiles.Units.UnitVisited;
import BusinessLayer.Tiles.Units.UnitVisitor;

import java.util.Random;

// the unit tiles on the board are separated in to 2 groups: enemies and players
public abstract class Unit extends Tile implements UnitVisited, UnitVisitor {

    // todo add comments
    protected String name;
    protected int attackPoints;
    protected int defensePoints;
    public Health health;


    public Unit(char tileType, Position position, String name, Health unitHealth, int attackPoints, int defensePoints) {
        super(tileType, position);

        this.name = name;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.health = unitHealth;


    }

    public String engageCombat(Unit defender) {
        Random rollDice =  new Random();
        int attackRoll = rollDice.nextInt(this.attackPoints + 1);
        int defenseRoll = rollDice.nextInt(defender.defensePoints + 1);
        int healthDamage = attackRoll - defenseRoll;
        String combatSummary = this.getName() + " engaged in combat with " + defender.getName() + ".\n" + this.describe()
                + defender.describe() + "\n" + this.getName() + " rolled " + attackRoll + " attack points.\n"
                + defender.getName() + " rolled " + defenseRoll + " attack points.\n";

        if (healthDamage < 0) {
            healthDamage = 0;
        }
        defender.health.decreaseHealthAmount(healthDamage);
        combatSummary += this.getName() + " dealt " + healthDamage + " damage to " + defender.getName() + ".\n";
        if (defender.health.getHealthAmount() < 1){
            combatSummary += this.kill(defender);
        }

        return combatSummary;
    }

    public Position getUnitPosition(){return this.position;}

    @Override
    public String contactWith(Empty empty) {
        Position changePosition = this.getPosition();
        this.setPosition(empty.getPosition());
        empty.setPosition(changePosition);
        return "";
    }

    @Override
    public String contactWith(Player player) {
        return engageCombat(player);
    }

    @Override
    public String contactWith(Enemy enemy) {
        return this.engageCombat(enemy);
    }

    @Override
    public String contactWith(Wall wall) {
        return "";
    }

    public String getName() {
        return name;
    }

    public abstract String describe();

    public String kill(UnitVisitor visitor) {
        return this.acceptKill(visitor);
    }

    public int getDefensePoints() {
        return this.defensePoints;
    }
}
