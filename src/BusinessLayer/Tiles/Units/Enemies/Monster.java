package BusinessLayer.Tiles.Units.Enemies;

import BusinessLayer.Resources.Health;
import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Unit;

import java.util.List;
//todo comment

public class Monster extends Enemy {

    private int visionRange;

    public Monster (char tileType, Position position, String name, Health monsterHealth, int attackPoints, int defensePoints, int experienceValue, int visionRange) {
        super(tileType, position, name, monsterHealth, attackPoints, defensePoints, experienceValue);

        this.visionRange = visionRange;
    }

    @Override
    public String describe() {
        return name + "\t Health: " + health + "\t Attack: " + attackPoints + "\t Defense: " + defensePoints
                + "\t Experience Value: " + experienceValue + "Vision Range:" + visionRange + "\n";

    }

    //    public void movement() {
//        if (Range(position, player position) < visionRange){
//            int dx = position.getxPosition() - player.position.getxPosition();
//            int dy = position.getyPosition() - player.position.getyPosition();
//
//            if (Math.abs(dx) > Math.abs(dy)) {
//                if (dx > 0) {
//                    position.moveLeft();
//                }
//                else
//                    position.moveRight();
//            }
//            else {
//                if (dy > 0) {
//                    position.moveUp();
//                }
//                else
//                    position.moveDown();
//            }
//        }
//        else {
//            // todo if no player in vision range select movement randomly or dont move at all
//        }
//    }

}
