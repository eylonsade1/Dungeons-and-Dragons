package BusinessLayer.Tiles;

import BusinessLayer.Tiles.Units.Enemies.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;

public interface TileVisitor {

    String contactWith(Player player);
    String contactWith(Enemy enemy);
    String contactWith(Empty empty);
    String contactWith(Wall wall);
}
