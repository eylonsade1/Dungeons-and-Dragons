package BusinessLayer.GameFlow;

import BusinessLayer.Tiles.Units.Health;
import BusinessLayer.Tiles.*;
import BusinessLayer.Tiles.Units.Enemies.Enemy;
import BusinessLayer.Tiles.Units.Enemies.Monster;
import BusinessLayer.Tiles.Units.Enemies.Trap;
import BusinessLayer.Tiles.Units.Players.*;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private Tile[][] Board;
    protected Player player;
    private List<Enemy> allEnemies;

    public Board(){

    }

    public String createPlayer(int playerChoice, Position playerPosition) {

        switch (playerChoice) {
            case 1:
                this.player = new Warrior(playerPosition, "Jon Snow", new Health(300), 30, 4, 3);
                break;
            case 2:
                this.player = new Warrior(playerPosition, "The Hound", new Health(400), 20, 6, 5);
                break;
            case 3:
                this.player = new Mage(playerPosition, "Melisandre", new Health(100), 5, 1, 15, 300, 30, 5,6);
                break;
            case 4:
                this.player = new Mage(playerPosition, "Thoros of Myr", new Health(250), 25, 4, 20, 150, 20, 3, 4);
                break;
            case 5:
                this.player = new Rogue(playerPosition, "Arya Stark", new Health(150), 40, 2, 20);
                break;
            case 6:
                this.player = new Rogue(playerPosition, "Bronn", new Health(250), 35, 3, 50);
                break;
            case 7:
                this.player = new Hunter(playerPosition, "Ygritte", new Health(220), 30, 2, 6);
                break;
        }
        return "You have selected:\n" + player.getName() +"\n";
    }

    public void setLevel(List<String> lines){
        Tile newTile = null;
        allEnemies = new ArrayList<Enemy>();
        Board = new Tile[lines.size()][lines.get(0).length()]; // The Board
        for (int x = 0 ; x < lines.size(); x++) {
            for (int y = 0; y < lines.get(0).length(); y++) {
                char tileType = lines.get(x).charAt(y);

                switch (tileType) {
                    case '.':
                        newTile = new Empty(new Position(x, y));
                        break;

                    case '#':
                        newTile = new Wall(new Position(x, y));
                        break;

                    case '@':
                        this.player.setPosition(x, y);
                        newTile = player;
                        break;

                    case 's':
                        newTile = new Monster('s', new Position(x, y), "Lannister Solider", new Health(80), 8, 3, 25, 3);
                        allEnemies.add(new Monster('s', new Position(x, y), "Lannister Solider", new Health(80), 8, 3, 25, 3));
                        break;

                    case 'k':
                        newTile = new Monster('k', new Position(x, y), "Lannister Knight", new Health(200), 14, 8, 50, 4);
                        allEnemies.add(new Monster('k', new Position(x, y), "Lannister Knight", new Health(200), 14, 8, 50, 4));
                        break;

                    case 'q':
                        newTile = new Monster('q', new Position(x, y), "Queen's Guard", new Health(400), 20, 15, 5, 100);
                        allEnemies.add(new Monster('q', new Position(x, y), "Queen's Guard", new Health(400), 20, 15, 5, 100));
                        break;

                    case 'z':
                        newTile = new Monster('z', new Position(x, y), "Wright", new Health(600), 30, 15, 3,100);
                        allEnemies.add(new Monster('z', new Position(x, y), "Wright", new Health(600), 30, 15, 3,100));
                        break;

                    case 'b':
                        newTile = new Monster('b', new Position(x, y), "Bear-Wright", new Health(1000), 75, 30, 4, 250);
                        allEnemies.add(new Monster('b', new Position(x, y), "Bear-Wright", new Health(1000), 75, 30, 4, 250));
                        break;

                    case 'g':
                        newTile = new Monster('g', new Position(x, y), "Giant-Wright", new Health(1500), 100, 40, 5, 500);
                        allEnemies.add(new Monster('g', new Position(x, y), "Giant-Wright", new Health(1500), 100, 40, 5, 500));
                        break;

                    case 'w':
                        newTile = new Monster('w', new Position(x, y), "White Walker", new Health(2000), 150, 50, 6, 1000);
                        allEnemies.add( new Monster('w', new Position(x, y), "White Walker", new Health(2000), 150, 50, 6, 1000));
                        break;

                    case'M':
                        newTile = new Monster('M', new Position(x, y), "The Mountain", new Health(1000), 60, 25, 6, 500);
                        allEnemies.add(new Monster('M', new Position(x, y), "The Mountain", new Health(1000), 60, 25, 6, 500));
                        break;

                    case 'C':
                        newTile = new Monster('C', new Position(x, y), "Queen Cersei", new Health(100), 10, 10, 1, 1000);
                        allEnemies.add(new Monster('C', new Position(x, y), "Queen Cersei", new Health(100), 10, 10, 1, 1000));
                        break;

                    case 'K':
                        newTile = new Monster('K', new Position(x, y), "Knight's King", new Health(5000), 300, 150, 8, 5000);
                        allEnemies.add(new Monster('K', new Position(x, y), "Knight's King", new Health(5000), 300, 150, 8, 5000));
                        break;

                    case 'B':
                        newTile = new Trap('B', new Position(x, y), "Bonus Trap", new Health(1), 1, 1, 250, 1, 5);
                        allEnemies.add(new Trap('B', new Position(x, y), "Bonus Trap", new Health(1), 1, 1, 250, 1, 5));
                        break;

                    case 'Q':
                        newTile = new Trap('Q', new Position(x, y), "Queen's Trap", new Health(250), 50, 10, 100, 3, 7);
                        allEnemies.add(new Trap('Q', new Position(x, y), "Queen's Trap", new Health(250), 50, 10, 100, 3, 7));
                        break;

                    case 'D':
                        newTile = new Trap('D', new Position(x, y), "Death Trap", new Health(500), 100, 20, 250, 1, 10);
                        allEnemies.add(new Trap('D', new Position(x, y), "Death Trap", new Health(500), 100, 20, 250, 1, 10));
                        break;
                }
                Board[x][y] = newTile;
            }
        }
        player.setAllEnemies(allEnemies);
    }

    @Override
    public String toString() {
        String board="";
        for (int i=0;i<Board.length;i++) {
            for (int j = 0; j < Board[0].length; j++) {
                board+=Board[i][j].toString();
            }
            board += "\n";
        }
        return board + "\n" + player.describe();
    }

    //  sets the tile's position on the board's array
    public Board setTileByPosition(Position pos1, Position pos2){
        Tile tile1 = getTile(pos1);
        Tile tile2 = getTile(pos2);

        Board[pos1.getxPosition()][pos1.getyPosition()] = tile2;
        Board[pos2.getxPosition()][pos2.getyPosition()] = tile1;
        return this;
    }

    public List<Enemy> getAllEnemies(){
        return this.allEnemies;
    }

    public Player getPlayer() {
        return player;
    }

    //  get's the tile from the board using its position
    public Tile getTile(Position position){
        return this.Board[position.getxPosition()][position.getyPosition()];
    }

}
