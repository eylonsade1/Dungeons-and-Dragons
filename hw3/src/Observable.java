import BusinessLayer.GameFlow.Observer;

import java.util.List;

public interface Observable {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObserver(int num);
    void notifyObserver(String str);
    void notifyObserver(List<String> level);


}
