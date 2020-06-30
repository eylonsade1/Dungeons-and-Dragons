package BusinessLayer.Tiles.Units.Players;

import BusinessLayer.Resources.Health;
import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Units.Enemies.Enemy;

import java.util.Random;

//todo comment
public class Warrior extends Player {

    private int abilityCoolDown;
    private int remainingCoolDown;
    private int abilityHeal;

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
            int healed =min(this.health.getHealthAmount()+this.defensePoints*10,this.health.getHealthPool());
            this.health.increaseHealthAmount(healed);
            this.health.increaseHealthAmount(this.defensePoints * 10);
            this.remainingCoolDown = abilityCoolDown; //set remaining coolDown
            for (Enemy enemy: this.getAllEnemies()) { //check for enemies in range < 3
                if (this.Range(this.getPosition(),enemy.getPosition()) < 3)
                    enemiesInRange.add(enemy);
            }

            Random rand = new Random(); // choose random enemy in range to attack
            Enemy defender = enemiesInRange.get(rand.nextInt(enemiesInRange.size())); //choose random enemy to attack
            int defenseRoll = rand.nextInt(defender.getDefensePoints()+ 1); //defender roll dice
            int healthDamage = this.abilityDamage - defenseRoll;
            if (healthDamage < 0)
                healthDamage =0;
            defender.health.decreaseHealthAmount(healthDamage); // defender got hit

            if (defender.health.getHealthAmount() == 0) //if the defender died
                this.visitAfterKilling(defender);

            return this.getName()+ " used "+this.abilityName+", healing for " + healed + ".\n"
                    + defender.getName()+ "rolled "+defenseRoll +"defense points.\n"+
                    this.getName()+"hit "+defender.getName() +"for "+this.abilityDamage +"ability damage.\n";
        }
    }

    public int min (int a, int b){
        if (a<b)
            return a;
        return b;
    }

    @Override
    public String describe() {
        return name + "\t Health: " + health.getHealthAmount() + "\\" + health.getHealthPool() + "\t Attack: " + attackPoints + "\t Defense: " + defensePoints + "\t Level:" + playerLevel
                + "\t Experience Value: " + experience + "\\" + (50*playerLevel) + "\t Cooldown: " + remainingCoolDown + "\\" + abilityCoolDown + "\n";
    }
}
