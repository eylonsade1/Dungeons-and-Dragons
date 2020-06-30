package BusinessLayer.Tiles.Units.Enemies;

import BusinessLayer.Resources.Health;
import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Units.HeroicUnit;
import BusinessLayer.Tiles.Units.Players.Player;

import java.util.Random;

public class Boss extends Enemy implements HeroicUnit {

    private int visionRange ;
    private int abilityFrequency;
    private int combatTicks;

    private Player player;

    public void setPlayer (Player p) {
        this.player =p;
    }


    public Boss(char tileType, Position position, String name, Health enemyHealth, int attackPoints, int defensePoints, int experienceValue, int visionRange, int abilityFrequency) {
        super(tileType, position, name, enemyHealth, attackPoints, defensePoints, experienceValue);
        this.visionRange = visionRange;
        this.abilityFrequency= abilityFrequency;
        this.combatTicks =0;
    }

    @Override
    public String castAbility() {
        Random rollDice =  new Random();
        int playerRoll = rollDice.nextInt(player.getDefensePoints()+1);
        int healthDamage = this.attackPoints - playerRoll;
        String summary = this.getName() + " rolled " + this.attackPoints + " attack points.\n"
                + player.getName() + " rolled " + playerRoll + " attack points.\n";
        if (healthDamage < 0) {
            healthDamage = 0;
        }
        player.health.decreaseHealthAmount(healthDamage);
        summary += this.getName() + " dealt " + healthDamage + " damage to " + player.getName() + ".\n";
        if (player.health.getHealthAmount() < 1){
            summary += this.acceptToKill(player);
        }
        return summary;
    }

    public void gameTick () {
        if (this.Range(this.position,getPlayersP()) < visionRange){
            if (combatTicks == abilityFrequency){
                combatTicks = 0;
                this.castAbility();
            }
            else {
                combatTicks += 1;
                this.Movement();
            }
        }
        else {
            combatTicks =0;
            this.randomMovement();
        }
    }





    @Override
    public String describe() {
        return name + "\t Health: " + health + "\t Attack: " + attackPoints + "\t Defense: " + defensePoints
                + "\t Experience Value: " + experienceValue + "\t Vision Range:" + visionRange;
    }
}
