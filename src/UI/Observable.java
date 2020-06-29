package UI;

import BusinessLayer.GameFlow.Observer;

import java.util.List;

public interface Observable {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObserver(int num);
    void notifyObserver(String str);
    void notifyObserver(List<List<String>> levels);
    void notifyObserver(Message message);
    void send(String message);
}
