package BusinessLayer.Tiles.Units.Players;

import BusinessLayer.Resources.Health;
import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Units.Enemies.Enemy;

import java.util.List;
import java.util.Random;

//todo comment

public class Rogue extends Player {

    private final static int maxEnergy = 100;

    private int energyCost;
    private int currentEnergy;
    private List<Enemy> enemiesInRange;

    public Rogue(Position position, String name, Health rogueHealth, int attackPoints, int defencePoints, int cost) {
        super(position, name, rogueHealth, attackPoints, defencePoints);

        this.energyCost = cost;
        this.currentEnergy = maxEnergy;
    }

    public void increaseCurrentEnergy(int energyAdded) {
        if (currentEnergy + energyAdded > maxEnergy) {
            this.currentEnergy = maxEnergy;
        }
        else
            this.currentEnergy += energyAdded;
    }

    @Override
    public void uponPlayerLevelUp() {
        playerLevelUp();
        this.currentEnergy = maxEnergy;
        this.attackPoints += 3 * playerLevel;

    }

    @Override
    public void gameTick() {
        increaseCurrentEnergy(10);
    }

    @Override
    public String castAbility() {
        if (this.currentEnergy < this.energyCost) {
            return "Can not cast " + this.abilityName + ", " + (this.energyCost - this.currentEnergy) + " more energy needed";
        }
        else {
            String string = this.getName()+" cast "+ this.abilityName+".\n";
            this.currentEnergy -= this.energyCost;
            for (Enemy enemy: this.getAllEnemies()) { //check for enemies in range <2
                if (this.Range(this.getPosition(),enemy.getPosition()) < 2)
                    this.enemiesInRange.add(enemy);
            }
            Random rand = new Random();
            for (Enemy defender: this.enemiesInRange) { // attack all enemies in range
                int defenseRoll = rand.nextInt(defender.getDefensePoints()+ 1); //defender roll dice
                int healthDamage = this.attackPoints - defenseRoll;
                defender.health.decreaseHealthAmount(healthDamage); //defender got hit
                if (defender.health.getHealthAmount() == 0) //if the defender died
                    this.visitAfterKilling(defender);
                string += defender.getName()+ " rolled " + defenseRoll+"  defense points.\n"+
                        this.getName() + " hit " +defender.getName()+ " for " + this.attackPoints + "ability damage.\n";
            }
            return string;
        }
    }

    @Override
    public String describe() {
        return name + "\t Health: " + health + "\t Attack: " + attackPoints + "\t Defense: " + defensePoints + "\t Level:" + playerLevel +  "\t Experience Value: "
                + experience + "\\" + (50 * playerLevel) + "\t Energy: " + currentEnergy + "\\"+ maxEnergy +"\t Spell Power: " + abilityDamage;

    }
}
