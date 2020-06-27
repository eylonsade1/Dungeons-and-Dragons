package BusinessLayer.Tiles;

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

    // returns the range between 2 points on the game board
    // which is defined by their Euclidean Distance
    public int Range (Position p, Position q) {

        return (int) Math.sqrt((p.getxPosition() - q.getxPosition())^2 + (p.getyPosition() - q.getyPosition())^2);

    }

    @Override
    public String toString() {
        return "" + this.tileType;
    }
}
