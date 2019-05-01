import java.awt.*;

public class Cell {
    private int x, y;
    private boolean alive = false;

    Cell(int x, int y){
        this.x=x;
        this.y=y;
    }

    void draw(Graphics g){
        g.setColor(alive?Color.GREEN:Color.RED);
        g.fillRect(x*11, y*11, 11, 11);
        g.setColor(Color.BLACK);
        g.drawRect(x*11, y*11, 11, 11);
    }

    boolean isAlive(){
        return alive;
    }
    void setAlive(boolean b){
        alive = b;
    }
}