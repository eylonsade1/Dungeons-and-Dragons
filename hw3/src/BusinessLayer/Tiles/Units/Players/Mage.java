package BusinessLayer.Tiles.Units.Players;

import BusinessLayer.Resources.Health;
import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.Tiles.Units.Enemies.Enemy;

import java.util.List;
import java.util.Random;
//todo comment

public class Mage extends Player {

    private int manaPool;
    private int currentMana;
    private int manaCost;
    private int hitsCount;

    private List<Enemy> enemiesInRange; // a list of enemies in hit range

    public Mage (Position position, String name, Health mageHealth, int attackPoints, int defencePoints, int spellPower, int manaPool, int manaCost, int hitsCount, int abilityRange){
        super(position, name, mageHealth, attackPoints, defencePoints);

        this.abilityName = "Blizzard";
        this.abilityDamage = spellPower;
        this.abilityRange = abilityRange;
        this.manaPool = manaPool;
        this.currentMana = manaPool / 4;
        this.manaCost = manaCost;
        this.hitsCount = hitsCount;

    }

    public void increaseMana (int manaAdded) {
        if (currentMana + manaAdded > manaPool) {
            this.currentMana = manaPool;
        }
        else
            this.currentMana += manaAdded;
    }

    @Override
    public void uponPlayerLevelUp() {
        playerLevelUp();
        this.manaPool += 25 * playerLevel;
        this.increaseMana(manaPool / 4);
        this.abilityDamage += 10 * playerLevel;

    }

    @Override
    public void gameTick() {
        increaseMana(playerLevel);
    }

    @Override
    public String castAbility() {
        if (currentMana < manaCost){
            return "Can not cast " + abilityName + ", " + (manaCost - currentMana) + " more mana needed\n";
        }
        else {
            String string= this.getName()+ " cast " +this.abilityName+ ".\n";
            this.currentMana -= manaCost;
            int hits = 0;
            for (Enemy enemy: this.getAllEnemies()) { //check for enemies in ability range
                if (this.Range(this.getPosition(),enemy.getPosition()) < this.abilityRange)
                    enemiesInRange.add(enemy);
            }
            Random rand = new Random();

            do{
                Enemy defender = enemiesInRange.get(rand.nextInt(enemiesInRange.size())); //choose random enemy in range to attack
                int defenseRoll = rand.nextInt(defender.getDefensePoints()+ 1); //defender roll dice
                int healthDamage = this.abilityDamage - defenseRoll;
                if (healthDamage < 0)
                    healthDamage = 0;
                defender.health.decreaseHealthAmount(healthDamage); // defender got hit
                string += defender.getName()+" rolled "+defenseRoll + " defense points.\n" +
                        this.getName() + " hit "+ defender.getName()+ " for "+ this.abilityDamage+ " ability damage.\n";

                if (defender.health.getHealthAmount() == 0){//if an enemy was killed, remove it from enemies in range list
                    enemiesInRange.remove(defender);
                    this.visitAfterKilling(defender);
                }

                hits += 1;
            } while (hits < hitsCount && !enemiesInRange.isEmpty() );

            return  string;
        }
    }

    @Override
    public String describe() {
        return name + "\t Health: " + health.getHealthAmount() + "\\" + health.getHealthPool() + "\t Attack: " + attackPoints + "\t Defense: " + defensePoints  + "\t Level:" + playerLevel + "\t Experience Value: "
                + experience + "\\" + (50 * playerLevel) + "\t Mana: " + currentMana + "\\" + manaPool + "\t Spell Power: " + abilityDamage;

    }
}