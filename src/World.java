import javax.swing.*;
import java.awt.*;

public class World extends JPanel {
    private Cell[][] cells = new Cell[50][50];
    private Cell[][] newCell = new Cell[50][50];

    World(){
        for(int y=0; y<50; y++){
            for(int x=0; x<50; x++){
                cells[x][y] = new Cell(x, y);
            }
        }
    }

    void setCellAlive(int x, int y, boolean b){
        cells[y][x].setAlive(b);
    }

    void refresh(){

        Thread thread0 = new Thread(new Runnable() {
            @Override
            public void run() {
                ref(false);
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ref(true);
            }
        });
        thread0.start();
        thread1.start();
        try {
            thread0.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i=0; i<50; i++) System.arraycopy(newCell[i], 0, cells[i], 0, 50);
    }

    private void ref(boolean a){
        for(int y=0; y<50; ++y){
            for(int x=!a?0:25; x<(!a?25:50); ++x){
                newCell[x][y] = new Cell(x, y);
                newCell[x][y].setAlive(cells[x][y].isAlive());
                int al = searchForAliveCells(x, y);
                if(al<2){
                    newCell[x][y].setAlive(false);
                } else if(al==3){
                    newCell[x][y].setAlive(true);
                } else if(al>3){
                    newCell[x][y].setAlive(false);
                }
            }
        }
    }

    private int searchForAliveCells(int x, int y){
        int count = 0;
        for(int i=-1; i<2; i++){
            if(x+i<0 || x+i>49){
                continue;
            }
            for(int j=-1; j<2; j++){
                if(!(i==0 && j==0)){
                    if(!(y+j<0 || y+j>49)){
                        if(cells[x+i][y+j].isAlive()) count++;
                    }
                }
            }
        }
        return count;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.translate(15, 25);
        for(int i=0; i<cells.length; ++i){
            for(int j=0; j<cells[i].length; ++j){
                cells[j][i].draw(g);
            }
        }
        g.translate(-15, -25);
    }
}
