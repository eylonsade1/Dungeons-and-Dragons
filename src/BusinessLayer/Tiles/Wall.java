package BusinessLayer.Tiles;

public class Wall extends Tile {

    // a wall tile on the game board, represented by '#'
    public Wall(Position position) {
        super('#', position);

    }

    @Override
    public void visitContact(Unit unit) {

    }

    @Override
    public void visitContact(Empty Empty) {

    }

    @Override
    public void visitContact(Wall wall) {

    }

    @Override
    public void acceptContact(TileVisitor visitor) {
        visitor.visitContact(this);
    }
}
