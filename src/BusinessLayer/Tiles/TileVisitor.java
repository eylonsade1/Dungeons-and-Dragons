package BusinessLayer.Tiles;

public interface TileVisitor {
    void visitContact (Unit unit);
    void visitContact (Empty Empty);
    void visitContact (Wall wall);


}
