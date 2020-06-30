package BusinessLayer.Tiles.Units.Players;

import BusinessLayer.Tiles.Units.Health;
import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Units.Enemies.Enemy;

import java.util.Random;

public class Hunter extends Player {

    private int range;
    private int  arrowsCount;
    private int ticksCount;
    private Enemy closestEnemy;

    public Hunter(Position position, String name, Health playerHealth, int attackPoints, int defencePoints, int range) {
        super(position, name, playerHealth, attackPoints, defencePoints);
        this.range= range;
        this.arrowsCount = 10;
        ticksCount=0;
        this.abilityName = "Shoot";
    }

    @Override
    public String castAbility() {
        int minRange = 100,tempRange;

        if (this.arrowsCount == 0 )
            return "Can not cast " + this.abilityName + ". " + this.getName() + " Does not have any arrows.\n";
        else {

            for (Enemy enemy : this.getAllEnemies()) { //check who is the closest enemy
                tempRange = this.Range(enemy.getPosition(), this.getPosition());
                if (tempRange < minRange) {
                    minRange = tempRange;
                    closestEnemy = enemy;
                }
            }
            if (minRange < this.range)
                return "Can not cast "+this.abilityName+". Closest enemy is farther then range.\n";
            String string = this.getName() + " fired an arrow at " + closestEnemy.getName()+"\n";
            this.arrowsCount -= 1;
            Random rand = new Random();
            int defenseRoll = rand.nextInt(closestEnemy.getDefensePoints()+ 1); //defender roll dice
            int healthDamage = this.attackPoints - defenseRoll;
            closestEnemy.health.decreaseHealthAmount(healthDamage); // defender got hit

            string+= closestEnemy.getName() + " rolled "+ defenseRoll + "defence points.\n" +
                    this.getName()+" hit "+ closestEnemy.getName()+" for "+this.attackPoints+ " ability damage.\n";

            if (closestEnemy.health.getHealthAmount() == 0) //if the defender died
                this.kill(closestEnemy);

            return string;
        }
    }

    @Override
    public void gameTick() {
        if (this.ticksCount == 10){
            this.arrowsCount += 10;
            this.ticksCount = 0;
        }
        else this.ticksCount += 1;
    }

    @Override
    public void uponPlayerLevelUp() {
        this.arrowsCount += 10*playerLevel;
        this.attackPoints += 2*playerLevel;
        this.defensePoints += playerLevel;
    }

    @Override
    public String describe() {
        return name + "\t Health: " + health.getHealthAmount() + "\\" + health.getHealthPool() + "\t Attack: " + attackPoints + "\t Defense: " + defensePoints + "\t Level:" + playerLevel
                + "\t Experience Value: " + experience + "\\" + (50*playerLevel) + "\t Arrows: " + arrowsCount + "\\" + "\t Range: " + range+"\n";
    }
}