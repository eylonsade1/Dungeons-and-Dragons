package BusinessLayer.Tiles.Units;

import BusinessLayer.Tiles.Units.Enemies.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;

public interface UnitVisitor {
    String visitAfterKilling (Player player);
    String visitAfterKilling (Enemy enemy);
}
