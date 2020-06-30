package BusinessLayer.Tiles;

import BusinessLayer.Tiles.Units.Enemies.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;

public class Empty extends Tile {

    // an empty tile on the game board, represented by '.'
    public Empty(Position position) {
        super('.', position);

    }
    public String acceptContact(TileVisitor tileVisitor) {
        return tileVisitor.contactWith(this);
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
}
