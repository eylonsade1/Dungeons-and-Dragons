package UI;

import BusinessLayer.GameFlow.GameFlow;
import BusinessLayer.GameFlow.Observer;


import java.io.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Game implements Observable, MessageHandler {
    private List<Observer> observers;

    public Game (){
        this.observers = new ArrayList<>();
    }

    Scanner sc = new Scanner(System.in);

    @Override
    public void addObserver(Observer o) {
        if(!observers.contains(o))
            observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);

    }

    public void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        notifyObserver(scanner.next());
    }

    @Override
    public void notifyObserver(int choice) {
        for (Observer o : observers)
            o.update(choice);
    }

    @Override
    public void notifyObserver(String str) {
        for (Observer o : observers)
            o.update(str);
    }

    @Override
    public void notifyObserver(List<List<String>> level) {
        for (Observer o : observers)
            o.update(level);
    }

    @Override
    public void notifyObserver(MessageHandler messageHandler) {
        for (Observer o : observers)
            o.update(messageHandler);
    }

    @Override
    public void send(String message) {

        System.out.println(message);
    }


    public void choosePlayer() {

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

        int choice = sc.nextInt();
        while (choice < 1 || choice > 7) {
            System.out.println("You didn't choose a player");
            choice = sc.nextInt();
        }
        notifyObserver(choice);

    }

    public static void main (String args[]){
        Game game = new Game();
        game.addObserver(new GameFlow());
        game.notifyObserver(game);
        game.choosePlayer();

        File[] files = new File(args[0]).listFiles(); // read from levels path
        List<List<String>> levels = new ArrayList<List<String>>();
        for (File file : files) {
            if (file.isFile() && file.getName().indexOf("level") != -1) { //read each file
                List<String> lines = new ArrayList<>();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String next;
                    while ((next = reader.readLine()) != null) {
                        lines.add(next);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                } catch (IOException e) {
                    System.out.println(e.getMessage() + "\n" +
                            e.getStackTrace());
                }
                levels.add(lines);
            }
        }
        game.notifyObserver(levels);

        while (true){
             game.getUserInput();
        }

    }
}






