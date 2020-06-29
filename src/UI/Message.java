package UI;

public class Message implements MessageHandler{

    @Override
    public void send(String print) {
        System.out.println(print);
    }
}