package BusinessLayer.Tiles.Units.Players;

import BusinessLayer.Resources.Health;
import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Unit;

import java.util.List;

//todo comment

public class Rogue extends Player {

    private final static int maxEnergy = 100;

    private int energyCost;
    private int currentEnergy;

    public Rogue (Position position, String name, Health rogueHealth, int attackPoints, int defencePoints, List<Unit> allUnitsInRange, int cost) {
        super(position, name, rogueHealth, attackPoints, defencePoints, allUnitsInRange);

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
        if (currentEnergy < energyCost) {
            return "Can not cast " + abilityName + ", " + (energyCost - currentEnergy) + " more energy needed";
        }
        else {
            this.currentEnergy -= energyCost;
            // todo add combat method
        }
        return null;
    }

    @Override
    public String describe() {
        return name + "\t Health: " + health + "\t Attack: " + attackPoints + "\t Defense: " + defensePoints + "\t Level:" + playerLevel +  "\t Experience Value: "
                + experience + "\\" + (50 * playerLevel) + "\t Energy: " + currentEnergy + "\\"+ maxEnergy +"\t Spell Power: " + abilityDamage;

    }
}
