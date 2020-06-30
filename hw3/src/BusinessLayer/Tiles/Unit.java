package BusinessLayer.Tiles;

import BusinessLayer.Resources.Health;
import BusinessLayer.Tiles.Units.Enemies.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;

import java.util.Random;

// the unit tiles on the board are separated in to 2 groups: enemies and players
public abstract class Unit extends Tile{

    // todo add comments
    protected String name;
    protected int attackPoints;
    protected int defensePoints;
    protected Health health;


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
        String combatSummary = this.getName() + " engaged in combat with " + defender + ".\n" + this.describe() + "\n"
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

//    @Override
//    public void acceptContact(TileVisitor visitor) {
//         visitor.contactWith(this);
//    }

    public String contactWith(Empty empty) {
        Position changePosition = this.getPosition();
        this.setPosition(empty.getPosition());
        empty.setPosition(changePosition);
        return "";
    }

    public String contactWith(Wall wall) { return "";}

    public String acceptToKill(Enemy enemy){
        return enemy.kill(this);
    }
    public String getName() {
        return name;
    }

    public abstract String describe();



}
