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
    public void moveLeft() {
        this.xPosition -= 1;
    }

    public void moveRight() {
        this.xPosition += 1;
    }

    public void moveDown() {
        this.yPosition += 1;
    }

    public void moveUp() {
        this.yPosition -= 1;
    }

    public void setPosition(int x, int y){
        this.xPosition = x;
        this.yPosition = y;

    }
//    public void setxPosition(int x) {
//        this.xPosition = x;
//    }

//    public void setyPosition(int y) {
//        this.yPosition = y;
//    }
}
