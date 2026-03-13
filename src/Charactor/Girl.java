package Charactor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Girl {

    public int x;
    public int y;
    public int health = 180;
    public static int speed = 90;

    private BufferedImage image; // เก็บรูปไว้

    public Girl() {
        loadImage();
    }

    public Girl(int x, int y) {
        this.x = x;
        this.y = y;
        loadImage();
    }

    private void loadImage() {
        try {
            image = ImageIO.read(new File("img/Untitled.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void jump(JPanel page) {

        this.y -= speed;
        page.repaint();

        Timer timer = new Timer(450, new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                y += speed;
                page.repaint();

            }
        });

        timer.setRepeats(false);
        timer.start();
    }

    public BufferedImage getImage() {
        return image;
    }
}
