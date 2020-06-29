package BusinessLayer.GameFlow;
import UI.MessageHandler;

public class Message implements MessageHandler  {

    @Override
    public void message(String print) {
        System.out.println(print);
    }
}