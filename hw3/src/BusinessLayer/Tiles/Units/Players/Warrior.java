package BusinessLayer.Tiles.Units.Players;

import BusinessLayer.Resources.Health;
import BusinessLayer.Tiles.Position;

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
        if (remainingCoolDown > 0){
            return "Can not cast " + abilityName + ", " + remainingCoolDown + " game ticks left until cooldown";
        }
        else {
            this.remainingCoolDown = abilityCoolDown;
            this.health.increaseHealthAmount(10 * this.defensePoints);
        }
        // todo add combat method
        return null;
    }

    @Override
    public String describe() {
        return name + "\t Health: " + health + "\t Attack: " + attackPoints + "\t Defense: " + defensePoints + "\t Level:" + playerLevel
                + "\t Experience Value: " + experience + "\\" + (50*playerLevel) + "\t Cooldown: " + remainingCoolDown + "\\" + abilityCoolDown;
    }
}
