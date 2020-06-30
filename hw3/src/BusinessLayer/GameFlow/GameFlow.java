package BusinessLayer.GameFlow;

import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Units.Enemies.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;

import UI.MessageHandler;


import java.util.List;
import java.util.spi.AbstractResourceBundleProvider;

public class GameFlow implements Observer {

    private List<Enemy> allEnemyUnits;
    private Board board;
    private boolean endGame = false;
    private List<List<String>> levels;
    private MessageHandler summary;
    private String tickSummary;


    public GameFlow() {
      this.board = new Board();

    }

    public void run() {
            summary.send(board.toString());
            this.board.player.gameTick();
           for(Enemy enemy : allEnemyUnits) {
               boolean attack = enemy.gameTick(this.board.player.getPlayerPosition());
               if(attack){
                  tickSummary += enemy.engageCombat(this.board.player);
               }
        }
           summary.send(tickSummary);
           return;
    }

    public void setAllEnemyUnits(List<Enemy> updatedList) {
        this.allEnemyUnits = updatedList;
    }

    // updates the user input during the his move
    @Override
    public void update(String choice) {
        if (choice == "e"){
            this.board.player.castAbility();
        }
        else {
            Position moveTo = this.board.player.getPosition();
            switch (choice) {
                case "a":
                    moveTo.moveLeft();
                    break;
                case "s":
                    moveTo.moveDown();
                    break;

                case "d":
                    moveTo.moveRight();
                    break;
                case "w":
                    moveTo.moveUp();
                    break;
            }
            tickSummary = this.board.player.contactWith(board.getTile(moveTo));
        }
        run();
        return;
    }

    @Override
    public void update(List<List<String>> gameLevels) {
        this.levels = gameLevels;
        board.setLevel(levels.get(0));
        summary.send(board.toString());
        return;
    }


    @Override
    public void update(int choice) {
        if (board.getPlayer() == null) {
            board.createPlayer(choice, new Position(0, 0));
        }
    }

    @Override
    public void update(MessageHandler messageHandler) {
        this.summary = messageHandler;
    }

}
