package BusinessLayer.Tiles.Units.Enemies;

import BusinessLayer.Resources.Health;
import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.Tiles.Units.Players.Player;
import BusinessLayer.Tiles.Units.UnitVisited;
import BusinessLayer.Tiles.Units.UnitVisitor;

import java.util.Random;
//todo comment

public abstract class Enemy extends Unit implements UnitVisitor, UnitVisited {

    protected int experienceValue;

    protected Position PlayersP ;

    public Enemy(char tileType, Position position, String name, Health enemyHealth, int attackPoints, int defensePoints, int experienceValue) {
        super(tileType, position, name, enemyHealth, attackPoints, defensePoints);

        this.experienceValue = experienceValue;
    }

    public int getExperienceValue () {
        return experienceValue;
    }

    @Override
    public String visitAfterKilling(Player playerKilled) {
        String killSummary = playerKilled.getName() + "was killed by " + this.name + ".\n"
                + "You lost.\n";
        playerKilled.died();
        return killSummary;
    }

    @Override
    public String visitAfterKilling(Enemy enemy) {
        return null;
    }


    public void Movement () {
        int dx = this.position.getxPosition()-getPlayersP().getxPosition();
        int dy = this.position.getyPosition()-getPlayersP().getyPosition();
        if (Math.abs(dx)-Math.abs(dy)>0){
            if (dx > 0)
                position.moveLeft();
            else position.moveRight();
        }
        else {
            if (dy > 0)
                position.moveUp();
            else position.moveDown();
        }
    }

    public void randomMovement () {
        Random rand = new Random();
        int randomMove = rand.nextInt(4);
        switch (randomMove){
            case 0:
                position.moveDown();
                break;
            case 1:
                position.moveUp();
                break;
            case 2:
                position.moveLeft();
                break;
            case 3:
                position.moveRight();
                break;
        }
    }



    public String acceptToKill(UnitVisitor visitor){
        return visitor.visitAfterKilling(this);
    }

    public void setPlayersP(Position playersP) {
        PlayersP = playersP;
    }
    public Position getPlayersP() {
        return PlayersP;
    }
}
