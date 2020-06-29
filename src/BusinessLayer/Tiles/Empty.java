package BusinessLayer.Tiles;

public class Empty extends Tile {

    // an empty tile on the game board, represented by '.'
    public Empty(Position position) {
        super('.', position);

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
