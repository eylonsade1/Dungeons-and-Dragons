package BusinessLayer.GameFlow;

import BusinessLayer.Resources.Health;
import BusinessLayer.Tiles.*;
import BusinessLayer.Tiles.Units.Enemies.Monster;
import BusinessLayer.Tiles.Units.Enemies.Trap;
import BusinessLayer.Tiles.Units.Players.*;

import java.util.List;

public class Board implements Observer {

//    private Observable observable;
    private Tile[][] Board;
    private Player player = null;
    private int playerChoice;
    private List<Tile> allEnemies;

    public Board(List<String> lines) {

        Board = new Tile[lines.size()][lines.get(0).length()]; // The Board

        for (int x = 0 ; x < lines.size(); x++) {
            for (int y = 0; y < lines.get(0).length(); y++) {
                char tileType = lines.get(x).charAt(y);
                Tile newTile;
                switch (tileType) {
                    case '.':
                        newTile = new Empty(new Position(x, y));
                        break;

                    case '#':
                        newTile = new Wall(new Position(x, y));
                        break;

                    case '@':
                        player.setPosition(x, y);
                        break;

                    case 's':
                        newTile = new Monster('s', new Position(x, y), "Lannister Solider", new Health(80), 8, 3, 25, 3);
                        allEnemies.add(newTile);
                        break;

                    case 'k':
                        newTile = new Monster('k', new Position(x, y), "Lannister Knight", new Health(200), 14, 8, 50, 4);
                        allEnemies.add(newTile);
                        break;

                    case 'q':
                        newTile = new Monster('q', new Position(x, y), "Queen's Guard", new Health(400), 20, 15, 5, 100);
                        allEnemies.add(newTile);
                        break;

                    case 'z':
                        newTile = new Monster('z', new Position(x, y), "Wright", new Health(600), 30, 15, 3,100);
                        allEnemies.add(newTile);
                        break;

                    case 'b':
                        newTile = new Monster('b', new Position(x, y), "Bear-Wright", new Health(1000), 75, 30, 4, 250);
                        allEnemies.add(newTile);
                        break;

                    case 'g':
                        newTile = new Monster('g', new Position(x, y), "Giant-Wright", new Health(1500), 100, 40, 5, 500);
                        allEnemies.add(newTile);
                        break;

                    case 'w':
                        newTile = new Monster('w', new Position(x, y), "White Walker", new Health(2000), 150, 50, 6, 1000);
                        allEnemies.add(newTile);
                        break;

                    case'M':
                        newTile = new Monster('M', new Position(x, y), "The Mountain", new Health(1000), 60, 25, 6, 500);
                        allEnemies.add(newTile);
                        break;

                    case 'C':
                        newTile = new Monster('C', new Position(x, y), "Queen Cersei", new Health(100), 10, 10, 1, 1000);
                        allEnemies.add(newTile);
                        break;

                    case 'K':
                        newTile = new Monster('K', new Position(x, y), "Knight's King", new Health(5000), 300, 150, 8, 5000);
                        allEnemies.add(newTile);
                        break;

                    case 'B':
                        newTile = new Trap('B', new Position(x, y), "Bonus Trap", new Health(1), 1, 1, 250, 1, 5);
                        allEnemies.add(newTile);
                        break;

                    case 'Q':
                        newTile = new Trap('Q', new Position(x, y), "Queen's Trap", new Health(250), 50, 10, 100, 3, 7);
                        allEnemies.add(newTile);
                        break;

                    case 'D':
                        newTile = new Trap('D', new Position(x, y), "Death Trap", new Health(500), 100, 20, 250, 1, 10);
                        allEnemies.add(newTile);
                        break;
                }
            }
        }

    }

    public void createPlayer(int playerChoice, Position playerPosition) {

        switch (playerChoice) {
            case 1:
                this.player = new Warrior(playerPosition, "Jon Snow", new Health(300), 30, 4, 3);
            case 2:
                this.player = new Warrior(playerPosition, "The Hound", new Health(400), 20, 6, 5);
            case 3:
                this.player = new Mage(playerPosition, "Melisandre", new Health(100), 5, 1, 15, 300, 30, 5,6);
            case 4:
                this.player = new Mage(playerPosition, "Thoros of Myr", new Health(250), 25, 4, 20, 150, 20, 3, 4);
            case 5:
                this.player = new Rogue(playerPosition, "Arya Stark", new Health(150), 40, 2, 20);
            case 6:
                this.player = new Rogue(playerPosition, "Bronn", new Health(250), 35, 3, 50);
            case 7:
                this.player = new Hunter(playerPosition, "Ygritte", new Health(220), 30, 2, 6);
        }

    }

    @Override
    public String toString() {
        String board="";
        for (int i=0;i<Board.length;i++) {
            for (int j = 0; j < Board[0].length; j++) {
                board+=Board[i][j].toString();
            }
            board+="\n";
        }
        return board;
    }

    @Override
    public void update(String choice) {

    }

    @Override
    public void update(List<String> lines) {
        new Board(lines);
    }

    @Override
    public void update(int choice) {
        if (player == null) {
            createPlayer(playerChoice, new Position(0, 0));
        }
    }
}
