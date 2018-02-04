package in.ac.vnrvjiet;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel implements ActionListener, KeyListener {

    /**
     *
     */
    private int level = 1;
    private static final long serialVersionUID = 1L;

    private Timer timer;

    private Map m;

    private boolean win = false;

    private String Message = " ";

    private Font font = new Font("Serif", Font.BOLD, 48);
    private Font nextLevel = new Font("Serif", Font.BOLD, 24);

    private Player p;

    public Board() {
        //setFocusable(true);
        addKeyListener(this);
        timer = new Timer(25, this);
        startNewGame();
    }

    public void startNewGame() {
        m = new Map(level);
        p = new Player();
        timer.start();
        //repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if (m.getMap(p.getTileX(), p.getTileY()).equals("f")) {
            Message = "Winner";
            win = true;
            //timer.stop();
        }
        repaint();

    }

    public void paint(Graphics g) {
        super.paint(g);
        if (!win) {
            for (int y = 0; y < 14; y++) {
                for (int x = 0; x < 14; x++) {
                    if (m.getMap(x, y).equals("f")) {
                        g.drawImage(m.getFinish(), x * 32, y * 32, null);
                    }

                    if (m.getMap(x, y).equals("g")) {
                        g.drawImage(m.getGrass(), x * 32, y * 32, null);
                    }
                    if (m.getMap(x, y).equals("w")) {
                        g.drawImage(m.getWall(), x * 32, y * 32, null);
                    }

                }

            }
            g.drawImage(p.getPlayer(), p.getTileX() * 32, p.getTileY() * 32, null);
        }
        if (win) {
            g.setColor(Color.ORANGE);
            g.setFont(font);
            g.drawString(Message, 150, 200);
            g.setFont(nextLevel);
            g.setColor(Color.RED);
            g.drawString("Press <Space> for next Level", 100, 350);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();

        if (win && keycode == KeyEvent.VK_SPACE) {
            level += 2;
            startNewGame();
            win = false;
            return;
        }

        if (keycode == KeyEvent.VK_UP) {
            if (!m.getMap(p.getTileX(), p.getTileY() - 1).equals("w")) {

                p.move(0, -1);
            }

        }
        if (keycode == KeyEvent.VK_DOWN) {
            if (!m.getMap(p.getTileX(), p.getTileY() + 1).equals("w")) {
                p.move(0, 1);
            }

        }
        if (keycode == KeyEvent.VK_LEFT) {
            if (!m.getMap(p.getTileX() - 1, p.getTileY()).equals("w")) {
                p.move(-1, 0);
            }
        }
        if (keycode == KeyEvent.VK_RIGHT) {
            System.out.println(p.getTileX());
            if(p.getTileX()<13){
            if (!m.getMap(p.getTileX() + 1, p.getTileY()).equals("w")) {
                p.move(1, 0);
            }
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
