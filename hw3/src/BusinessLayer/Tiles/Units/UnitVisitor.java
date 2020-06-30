package BusinessLayer.Tiles.Units;

import BusinessLayer.Tiles.Units.Enemies.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;

public interface UnitVisitor {
    String kill(Player player);
    String kill(Enemy enemy);
}
