package BusinessLayer.Tiles;

import BusinessLayer.Tiles.Units.Enemies.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;

public abstract class Tile {

    // represents each tile on the game board,
    // each tile has a position representing its coordinates on the board and a char that represents its type:
    // 1.Empty Tile- '.'
    // 2.Wall Tile- '#'
    // 3.Unit Tile- each type of unit will be represented differently

    protected char tileType;
    protected Position position;

    public Tile (char tile, Position position) {

        this.tileType = tile;
        this.position = position;

    }

    public char getTileType() {
        return tileType;
    }

    public void setTileType(char tile) {
        this.tileType = tile;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPosition(int x, int y) {
        this.position.setPosition(x, y);
    }

    // returns the range between 2 points on the game board
    // which is defined by their Euclidean Distance
    public int Range (Position p, Position q) {

        return (int) Math.sqrt((p.getxPosition() - q.getxPosition())^2 + (p.getyPosition() - q.getyPosition())^2);

    }



    public String contactWith(Tile tile) {
        return this.contactWith(tile);
    }


    @Override
    public String toString() {
        return "" + this.tileType;
    }
}
