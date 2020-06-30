package BusinessLayer.GameFlow;

import UI.MessageHandler;

import java.util.List;


public interface Observer {

    void update(String choice);
    void update(List<List<String>> lines);
    void update(int choice);
    void update(MessageHandler messageHandler);
//    void sendMessage(String message);

}
