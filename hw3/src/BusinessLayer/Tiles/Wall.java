package BusinessLayer.Tiles;

import BusinessLayer.Tiles.Units.Enemies.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;

public class Wall extends Tile {

    // a wall tile on the game board, represented by '#'
    public Wall(Position position) {
        super('#', position);

    }

    @Override
    public String contactWith(Player player) {
        return "";
    }

    @Override
    public String contactWith(Enemy enemy) {
         return "";
    }

    @Override
    public String contactWith(Empty empty) {
        return "";
    }

    @Override
    public String contactWith(Wall wall) {
        return "";
    }

    @Override
    public String acceptContact(TileVisitor tileVisitor) {
        return tileVisitor.contactWith(this);
    }
}
