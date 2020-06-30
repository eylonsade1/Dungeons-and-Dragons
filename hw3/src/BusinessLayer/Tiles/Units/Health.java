package BusinessLayer.Tiles.Units;

public class Health {

    // max health amount
    protected int healthPool;
    // current health amount
    protected int healthAmount;


    public Health (int healthPool) {
        this.healthPool = healthPool;
        this.healthAmount = healthPool;
    }

    // when a player levels up the max health amount raises & the current health amount increases to the max
    public void healthLevelingUp(int level) {
        this.healthPool += 10 * level;
        this.healthAmount = this.healthPool;

    }

    // returns the max health amount
    public int getHealthPool() {
        return healthPool;
    }

    // increases current health amount, while limiting the current amount to the max possible
    public void increaseHealthAmount (int healthAdded) {
        if (healthAmount + healthAdded > healthPool) {
            this.healthAmount = healthPool;
        }
        else
            this.healthAmount += healthAdded;
    }

    public void decreaseHealthAmount (int healthLost) {
        this.healthAmount -= healthLost;

    }

    // increases the max health amount
    public void increaseHealthPool (int healthAdded) {
        this.healthPool += healthAdded;

    }

    public int getHealthAmount() {
        return healthAmount;
    }
}
