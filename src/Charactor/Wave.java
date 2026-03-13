package Charactor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Wave {

    public int x;
    public int y;
    public int speed;

    public Timer timeMove;
    private BufferedImage image;

    public Wave(int x, int y, int speed, JPanel page) {

        this.x = x;
        this.y = y;
        this.speed = speed;

        loadImage();
        move(page);
    }

    private void loadImage() {

        try {
            image = ImageIO.read(new File("img/Rab.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void move(JPanel page) {

        timeMove = new Timer(30, new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (x <= -100) {
                    x = (int) (1000 + Math.random() * 500);
                }

                x -= speed;

                page.repaint();
            }
        });

        timeMove.start();
    }

    public BufferedImage getImage() {
        return image;
    }
}
