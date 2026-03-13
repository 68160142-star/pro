package Charactor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Environment {

    public int x;
    public int y;
    public int startX;
    public int speed;
    public int eType;

    public static int CLOUD = 0;
    public static int BUILDING = 1;

    private Timer timeMove;

    public Environment(int x, int y, JPanel page, int eType, int speed) {
        this.x = x;
        this.y = y;
        this.startX = x;
        this.speed = speed;
        this.eType = eType;

        move(page);
    }

    public void move(JPanel page) {

        timeMove = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (x + 400 < 0) {
                    x = startX;
                }

                x -= speed;
                page.repaint();
            }
        });

        timeMove.start();
    }

    public void stop() {
        if (timeMove != null) {
            timeMove.stop();
        }
    }

    public String getEnvType(int eType) {

        String[] name = {"cloud.png", "building.png"};

        if (eType < 0 || eType >= name.length) {
            return name[0];
        }

        return name[eType];
    }

    public BufferedImage getImage() {

        BufferedImage image = null;

        try {
            image = ImageIO.read(new File("src/img/" + getEnvType(this.eType)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }
}