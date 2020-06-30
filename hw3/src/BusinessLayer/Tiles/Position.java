package BusinessLayer.Tiles;

public class Position {

    // representing the (x,y) coordinates of a tile on the board
    private int xPosition;
    private int yPosition;

    public Position (int x, int y) {
        this.xPosition = x;
        this.yPosition = y;

    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    // movement on the board
    public void moveUp() {
        this.xPosition -= 1;
    }

    public void moveDown() {
        this.xPosition += 1;
    }

    public void moveRight() {
        this.yPosition += 1;
    }

    public void moveLeft() {
        this.yPosition -= 1;
    }

    public void setPosition(int x, int y){
        this.xPosition = x;
        this.yPosition = y;

    }

}
