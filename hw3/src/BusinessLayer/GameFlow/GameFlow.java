package BusinessLayer.GameFlow;

import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Units.Enemies.Enemy;
import UI.MessageHandler;
import java.util.List;

public class GameFlow implements Observer {

    private Board board;
    private boolean endGame = false;
    private List<List<String>> levels;
    private MessageHandler summary;
    private String tickSummary;

    public GameFlow() {
      this.board = new Board();
    }

    public void run() {
            this.board.player.gameTick();

           for(Enemy enemy : board.getAllEnemies()) {
               Position lastEnemyPosition = new Position(enemy.getUnitPosition().getxPosition(), enemy.getUnitPosition().getyPosition());
               if(enemy.gameTick(this.board.player.getUnitPosition())){
                  tickSummary += enemy.engageCombat(this.board.getPlayer());
               }
               this.board.setTileByPosition(enemy.getUnitPosition(), lastEnemyPosition);
        }
        summary.send(board.toString());
           summary.send(tickSummary);
           return;
    }


    //  updates the user input during the his move
    @Override
    public void update(String choice) {
        if (choice.equals("e")){
           tickSummary += this.board.player.castAbility();
        }
        else {
            Position moveTo = new Position(this.board.player.getPosition().getxPosition(),this.board.player.getPosition().getyPosition());
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
                case "q":
                    break;
                default:
                    return;
            }
            Position lastPlayerPosition = new Position(board.getPlayer().getUnitPosition().getxPosition(), board.getPlayer().getUnitPosition().getyPosition());
            tickSummary = this.board.player.contactWith(board.getTile(moveTo));
            this.board.setTileByPosition(board.getPlayer().getUnitPosition(), lastPlayerPosition);
        }
        run();
        return;
    }


    //  the Observer updates the GameFlow once the levels are loaded from the directory
    //  once the levels are received the first level on the list is sent to be created in the board class
    //  at last the board's description is sent to be printed
    @Override
    public void update(List<List<String>> gameLevels) {
        this.levels = gameLevels;
        board.setLevel(levels.get(0));
        summary.send(board.toString());
        return;
    }


    //  the Observer updates the GameFlow once the user chooses a player
    //  the players choice is sent to the board where it will be created
    @Override
    public void update(int choice) {
        if (board.getPlayer() == null) {
            summary.send(board.createPlayer(choice, new Position(0, 0)));
        }
    }


    //  the Observer updates the GameFlow once a new message handler is created
    @Override
    public void update(MessageHandler messageHandler) {
        this.summary = messageHandler;
    }

}
