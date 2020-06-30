package BusinessLayer.Tiles.Units.Enemies;

import BusinessLayer.Resources.Health;
import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.GameFlow.Board;
import BusinessLayer.Tiles.Units.Players.Player;

import java.util.List;
import java.util.Random;
//todo comment

public class Monster extends Enemy {

    private int visionRange;

    public Monster (char tileType, Position position, String name, Health monsterHealth, int attackPoints, int defensePoints, int experienceValue, int visionRange) {
        super(tileType, position, name, monsterHealth, attackPoints, defensePoints, experienceValue);

        this.visionRange = visionRange;
    }



    @Override
    public String describe() {
        return name + "\t Health: " + health.getHealthAmount() + "\\" + health.getHealthPool() + "\t Attack: " + attackPoints + "\t Defense: " + defensePoints
                + "\t Experience Value: " + experienceValue + "Vision Range:" + visionRange + "\n";

    }

    @Override
    public boolean gameTick(Position playerPosition) {
        boolean playerInRange = false;
        if (Range(this.position, playerPosition) < visionRange){
            int dx = this.position.getxPosition() - playerPosition.getxPosition();
            int dy = this.position.getyPosition() - playerPosition.getyPosition();

            if (Math.abs(dx) > Math.abs(dy)) {  // player in vision range
                playerInRange = true;
                if (dx > 0) {
                    position.moveLeft();
                }
                else
                    position.moveRight();
            }
            else {
                if (dy > 0) {
                    position.moveUp();
                }
                else
                    position.moveDown();
            }
        }
        else { //player not in vision range
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
        return playerInRange;
        }

    @Override
    public String contactWith(Unit unit) {
        return this.contactWith(unit);
    }
}
