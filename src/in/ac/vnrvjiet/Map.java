package in.ac.vnrvjiet;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class Map {

    private Scanner m;
    private String Map[] = new String[14];
    private static final int ROWS = 14;
    private static final int WALL_COUNT = 8;
    private Image grass, finish, wall;

    public void initMap() {
        for (int i = 0; i < ROWS; i++) {
            Map[i] = "gggggggggggggg";
        }
    }
    Random rand = new Random();

    public void placeWalls(int wallCount) {
        for (int i = 0; i < wallCount;) {
            int r = rand.nextInt(ROWS);
            int c = rand.nextInt(ROWS);

            if (getMap(r, c).equals("g")) {
                StringBuilder sb = new StringBuilder(Map[r]);
                sb.setCharAt(c, 'w');
                Map[r] = sb.toString();
                i++;
            }
        }
    }

    public void placeFlag() {
        for (int i = ROWS - 1; i >= 0; i--) {
            int j = Map[i].lastIndexOf('g');
            StringBuilder sb = new StringBuilder(Map[i]);
            if (j != -1) {
                sb.setCharAt(j, 'f');
                Map[i] = sb.toString();
                break;
            }

        }
    }

    public Map(int level) {

        ImageIcon img = new ImageIcon("grass.png");
        grass = img.getImage();
        img = new ImageIcon("wall.png");
        wall = img.getImage();
        img = new ImageIcon("finish.png");
        finish = img.getImage();
        
        /*
        openFile();
        readFile();
        closeFile();
        */
        
        initMap();
        placeWalls(level+7);
        placeFlag();
    }

    public Image getGrass() {
        return grass;
    }

    public Image getWall() {
        return wall;
    }

    public Image getFinish() {
        return finish;
    }

    public String getMap(int x, int y) {
        String index = Map[y].substring(x, x + 1);
        return index;
    }

    public void openFile() {
        try {
            File f = new File("Map.txt");

            /*
             BufferedReader br = new BufferedReader(new FileReader("map.txt"));
             String line = "";
             while((line=br.readLine()) != null)
             {
             System.out.println(line);
             }
             br.close();
             */
            m = new Scanner(f);
        } catch (Exception e) {
            System.out.println("error loading map");
        }
    }

    public void readFile() {
        while (m.hasNext()) {
            for (int i = 0; i < 14; i++) {
                Map[i] = m.next();
            }
        }
    }

    public void closeFile() {
        m.close();

    }

}
