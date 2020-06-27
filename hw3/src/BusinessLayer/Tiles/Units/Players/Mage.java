package BusinessLayer.Tiles.Units.Players;

import BusinessLayer.Resources.Health;
import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Unit;

import java.util.List;
//todo comment

public class Mage extends Player {

    private int spellPower;
    private int range;
    private int manaPool;
    private int currentMana;
    private int manaCost;
    private int hitsCount;

    public Mage (Position position, String name, Health mageHealth, int attackPoints, int defencePoints, List<Unit> allUnitsInRange, int spellPower, int range, int manaPool, int manaCost, int hitsCount, int abilityRange){
        super(position, name, mageHealth, attackPoints, defencePoints, allUnitsInRange);

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
        this.spellPower += 10 * playerLevel;

    }

    @Override
    public void gameTick() {
        increaseMana(playerLevel);

    }

    @Override
    public String castAbility() {
        if (currentMana < manaCost){
            return "Can not cast " + abilityName + ", " + (manaCost - currentMana) + " more mana needed";
        }
        else {
            this.currentMana -= manaCost;
            int hits = 0;

            while (hits < hitsCount && allUnitsInRange.size() > 0){
                // todo add combat method and live unit detection method

                hits += 1;
            }
        }
        return null;
    }

    @Override
    public String describe() {
        return name + "\t Health: " + health + "\t Attack: " + attackPoints + "\t Defense: " + defensePoints  + "\t Level:" + playerLevel + "\t Experience Value: "
                + experience + "\\" + (50 * playerLevel) + "\t Mana: " + currentMana + "\\" + manaPool + "\t Spell Power: " + abilityDamage;

    }
}
