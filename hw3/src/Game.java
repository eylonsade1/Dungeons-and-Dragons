import BusinessLayer.Resources.Health;
import BusinessLayer.Tiles.Position;
import BusinessLayer.Tiles.Units.Players.*;

import java.io.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Game {


    public static void main (String args[]){

        Scanner sc = new Scanner(System.in);

        File[] files = new File(args[0]).listFiles(); // read from levels path
        for (File file : files) {
            if (file.isFile() && file.getName().indexOf("level")!=-1) { //read each file
                List<String> lines = new ArrayList<>();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String next;
                    while ((next = reader.readLine()) != null) {
                        lines.add(next);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println ("File not found ");
                } catch (IOException e) {
                    System.out.println(e.getMessage() + "\n" +
                            e.getStackTrace());
                }
                char[][] Board = new char[lines.size()][lines.get(0).length()]; // The Board
                int PlayerX=0;
                int PlayerY=0;
                for (int i=0 ; i < lines.size(); i++) {
                    for (int j=0; j<lines.get(0).length(); j++) {
                        Board[i][j] = lines.get(i).charAt(j);
                        if (Board[i][j] == '@'){
                            PlayerX=i;
                            PlayerY=j;
                        } // Save Player's position
                    }
                }
                System.out.println("Select player:");
                System.out.println("1. Jon Snow1. Jon Snow             Health: 300/300         Attack: 30              Defense: 4              Level: 1");
                System.out.println("        Experience: 0/50                Cooldown: 0/3");
                System.out.println("2. The Hound            Health: 400/400         Attack: 20              Defense: 6              Level: 1");
                System.out.println("        Experience: 0/50                Cooldown: 0/5");
                System.out.println("3. Melisandre           Health: 100/100         Attack: 5               Defense: 1              Level: 1");
                System.out.println("        Experience: 0/50                Mana: 75/300            Spell Power: 15");
                System.out.println("4. Thoros of Myr                Health: 250/250         Attack: 25              Defense: 4              Level: 1");
                System.out.println("                Experience: 0/50                Mana: 37/150            Spell Power: 20");
                System.out.println("5. Arya Stark           Health: 150/150         Attack: 40              Defense: 2              Level: 1");
                System.out.println("        Experience: 0/50                Energy: 100/100");
                System.out.println("6. Bronn                Health: 250/250         Attack: 35              Defense: 3              Level: 1");
                System.out.println("        Experience: 0/50                Energy: 100/100");
                System.out.println("7. Ygritte              Health: 220/220         Attack: 30              Defense: 2              Level: 1");
                System.out.println("        Experience: 0/50                Arrows: 10              Range: 6");

                Position PlayersP = new Position(PlayerX,PlayerY) ;
                int choice = sc.nextInt();
                Player P;
                switch (choice){  
                    case 1:
                        P = new Warrior(PlayersP, "Jon Snow", new Health(300),30, 4, );
                        break;
                    case 2:
                        P = new Warrior(PlayersP, "The Hound", new Health(400),20, 6, );
                        break;
                    case 3:
                        P = new Mage(PlayersP, "Melisandre", new Health(100),30, 4, );
                        break;
                    case 4:
                        P = new Mage(PlayersP, "Thoros of Myr", new Health(250),30, 4, );
                        break;
                    case 5:
                        P = new Rogue(PlayersP, "Arya Stark", new Health(150),30, 4, );
                        break;
                    case 6:
                        P = new Rogue(PlayersP, "Bronn", new Health(250),30, 4, );
                        break;
                    case 7:
                        P = new Hunter(PlayersP, "Ygritte", new Health(220),30, 4, );
                        break;
                    default:
                        System.out.println("You didn't choose a player");
                }





            }
        }









    new Game();



}}


