import javax.swing.*;
import java.awt.event.*;

public class CellularAutomaton {
    private JFrame frame;
    private World world;
    private boolean editing = false;

    private CellularAutomaton(){
        frame = new JFrame("Cellular Automaton");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 650);
        frame.setResizable(true);

        world  = new World();
        world.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(editing){
                    int x = (e.getX()-15)/11, y = (e.getY()-25)/11;
                    world.setCellAlive(y, x, e.getButton()!=MouseEvent.BUTTON3);
                    frame.repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        Timer timer = new Timer(100, e -> {
            world.refresh();
            frame.repaint();
        });
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                String pressedButton = KeyEvent.getKeyText(e.getKeyCode());
                switch (pressedButton) {
                    case "R":
                        timer.start();
                        break;
                    case "S":
                        timer.stop();
                        break;
                    case "E":
                        editing = true;
                        break;
                    case "F":
                        world.refresh();
                        frame.repaint();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        frame.add(world);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CellularAutomaton();
    }
}
