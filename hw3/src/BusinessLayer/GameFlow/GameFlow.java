package BusinessLayer.GameFlow;

import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Units.Enemies.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;
import BusinessLayer.Resources.Health;
import java.util.ArrayList;
import java.util.List;

public class GameFlow implements Observer {

    private Player player;
    private List<Enemy> allEnemyUnits;
    private Board board = new Board();
    private boolean endGame = false;
   private List<List<String>> levels;


    public GameFlow() {
        while (!levels.isEmpty() && !endGame) {
            board.setLevel(levels.get(0));

            board.toString();

        }
    }

    public String setPlayer(Player newPlayer) {
        this.player = newPlayer;
        return "You have selected:\n" + player.getName() +"\n";
    }

    public String setBoard(Board newBoard) {
        this.board = newBoard;
        return newBoard.toString() + "\n";
    }

    public void setAllEnemyUnits(List<Enemy> updatedList) {
        this.allEnemyUnits = updatedList;
    }

    @Override
    public void update(String choice) {

    }

    @Override
    public void update(List<List<String>> gameLevels) {
        this.levels = gameLevels;
    }

    @Override
    public void update(int choice) {
        if (player == null) {
            board.createPlayer(choice, new Position(0, 0));
        }


    }

    @Override
    public void sendMessage(String message) {

    }
}
