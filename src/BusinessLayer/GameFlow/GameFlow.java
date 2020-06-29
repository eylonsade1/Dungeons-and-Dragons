package BusinessLayer.GameFlow;

import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Units.Enemies.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;
import UI.Message;
import UI.MessageHandler;


import java.util.List;
import java.util.spi.AbstractResourceBundleProvider;

public class GameFlow implements Observer {

    private Player player;
    private List<Enemy> allEnemyUnits;
    private Board board;
    private boolean endGame = false;
    private List<List<String>> levels;
    private MessageHandler summary;


    public GameFlow() {
      this.board = new Board();
      summary = new Message();
    }

    public void run() {

        board.setLevel(levels.get(0));

        while (!endGame) {
            summary.send(board.toString());
            break;



        }
    }

    public void setAllEnemyUnits(List<Enemy> updatedList) {
        this.allEnemyUnits = updatedList;
    }

    // updates the user input during the his move
    @Override
    public void update(String choice) {
        if (choice == "e"){
            player.castAbility();
        }
        else {
            Position moveTo = player.getPosition();
            switch (choice) {
                case "a":
                    moveTo.moveLeft();
                case "s":
                    moveTo.moveDown();
                case "d":
                    moveTo.moveRight();
                case "w":
                    moveTo.moveUp();
            }
            this.player.onMove(board.getTile(moveTo));
        }
    }

    @Override
    public void update(List<List<String>> gameLevels) {
        this.levels = gameLevels;
        run();
    }

    @Override
    public void update(int choice) {
        if (board.getPlayer() == null) {
            board.createPlayer(choice, new Position(0, 0));
        }
    }

}
