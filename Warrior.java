package BusinessLayer.Tiles.Units.Players;

import BusinessLayer.Resources.Health;
import BusinessLayer.Tiles.Empty;
import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.Tiles.Units.Enemies.Enemy;

import java.util.*;
//todo comment
public class Warrior extends Player {

    private int abilityCoolDown; //how much game ticks does the warrior has to wait between casting ability

    private int remainingCoolDown; //how much waiting left for casting ability
    private int abilityHeal;
    private int abilityDamage;
    private List<Enemy> enemiesInRange; // a list of the enemies in range of less then 3

    public Warrior(Position position, String name, Health warriorHealth, int attackPoints, int defensePoints, int abilityCoolDown) {
        super(position, name, warriorHealth, attackPoints, defensePoints);

        this.abilityCoolDown = abilityCoolDown;
        this.remainingCoolDown = 0;
        this.abilityName = "Avenger's Shield";
        this.abilityDamage = (int) (health.getHealthPool() * 0.1);
        this.abilityRange = 3;
        this.abilityHeal = 10 * defensePoints;
    }

    @Override
    public void uponPlayerLevelUp() {
        playerLevelUp();
        this.remainingCoolDown = 0;
        this.health.increaseHealthPool(5 * playerLevel);
        this.attackPoints += 2 * playerLevel;
        this.defensePoints += playerLevel;
        this.abilityDamage = (int)(health.getHealthPool() * 0.1);
    }

    @Override
    public void gameTick() {
        this.remainingCoolDown -= 1;
    }

    @Override
    public String castAbility() {
        if (remainingCoolDown > 0){ // the warrior has to wait unit using special ability
            return "Can not cast " + abilityName + ", " + remainingCoolDown +"/"+ abilityCoolDown+" game ticks left until cooldown";
        }
        else {
            this.remainingCoolDown = abilityCoolDown; //set remaining coolDown
            this.health.increaseHealthAmount(10 * this.defensePoints); //warrior's health increase
            for (Enemy enemy: this.getAllEnemies()) { //check for enemies in range < 3
                if (this.Range(this.getPosition(),enemy.getPosition()) < 3)
                    enemiesInRange.add(enemy);
            }
            Random rand = new Random(); // choose random enemy in range to attack
            Enemy defender = enemiesInRange.get(rand.nextInt(enemiesInRange.size())); //choose random enemy to attack
            int defenseRoll = rand.nextInt(defender.getDefensePoints()+ 1); //defender roll dice
            int healthDamage = this.abilityDamage - defenseRoll;
            int healed =min(this.health.getHealthAmount()+this.defensePoints*10,this.health.getHealthPool());
            this.health.increaseHealthAmount(healed);
            if (healthDamage < 0)
                healthDamage =0;
            defender.health.decreaseHealthAmount(healthDamage); // defender got hit

            if (defender.health.getHealthAmount() == 0) //if the defender died
                this.visitAfterKilling(defender);

            return this.getName()+ " used "+this.abilityName+", healing for "+healed+".\n"
                    + defender.getName()+ "rolled "+defenseRoll +"defense points.\n"+
                    this.getName()+"hit "+defender.getName() +"for "+this.abilityDamage +"ability damage.\n";
        }
    }

    @Override
    public String describe() {
        return name + "\t Health: " + health + "\t Attack: " + attackPoints + "\t Defense: " + defensePoints + "\t Level:" + playerLevel
                + "\t Experience Value: " + experience + "\\" + (50*playerLevel) + "\t Cooldown: " + remainingCoolDown + "\\" + abilityCoolDown;
    }
    public int min (int a, int b){
        if (a<b)
            return a;
        return b;
    }
    public void setRemainingCoolDown(int remainingCoolDown) {
        this.remainingCoolDown = remainingCoolDown;
    }


}
