package display;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import Charactor.*;
import Element.Element;
import event.Event;

public class Game extends JPanel implements KeyListener{

    private static final long serialVersionUID = 1L;

    private static int speed = 20, girlSize = 100 , waveHeight = 50;
    private static int base = 400, xStart = 1000;

    private long point = 0, lastPress = 0;

    private Girl girl = new Girl(100, base-100);
    static Display display;

    // รูปภาพ
    private Image sky;
    private Image ground;
    private Image heart;

    // Wave
    private Wave[] waveSet = makeWave(4);

    // Cloud
    private Environment[] envSet = makeEnv(2,Environment.CLOUD);

    private Environment building = new Environment(xStart-100,base-150,this,Environment.BUILDING,4);

    public Game(){

        this.setBounds(0,0,1000,600);
        this.addKeyListener(this);
        this.setLayout(null);
        this.setFocusable(true);

        try{
            sky = ImageIO.read(new File("img\\ing.png"));
            ground = ImageIO.read(new File("img\\design.png"));
            heart = ImageIO.read(new File("img\\heart.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        drawBackground(g2);

        // POINT
        g2.setFont(Element.getFont(30));
        g2.setColor(Color.white);
        g2.drawString("Point : "+point,750,40);

        // HEALTH
        drawDogHealth(g2);

        // GIRL
        g2.drawImage(girl.getImage(),girl.x,girl.y,girlSize,girlSize,null);

        // WAVE
        for(Wave item : waveSet) {
            drawWave(item,g2);
        }

        point++;

    }

    private void drawBackground(Graphics2D g2){

        g2.drawImage(sky,0,0,1536,1024,null);
        g2.drawImage(building.getImage(),building.x,building.y,500,200,null);
        g2.drawImage(ground,0,base+10,2000,220,null);

        for(Environment item:envSet) {
            g2.drawImage(item.getImage(),item.x,item.y,250,160,null);
        }
    }

    private void drawDogHealth(Graphics2D g2){

        g2.drawImage(heart,10,20,20,20,null);

        g2.setStroke(new BasicStroke(18.0f));
        g2.setColor(new Color(241, 98, 69));
        g2.drawLine(60,30,60+girl.health,30);

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(6.0f));
        g2.drawRect(50,20,200,20);

    }

    private Wave[] makeWave(int size) {

        Wave[] waveSet = new Wave[size];

        int far = 500;

        for(int i=0;i<size;i++) {

            waveSet[i] = new Wave(xStart+far,base,speed,this);
            far += 500;

        }

        return waveSet;
    }

    private Environment[] makeEnv(int size,int eType){

        Environment[] envSet = new Environment[size];

        int far = 0;

        for(int i=0;i<size;i++) {

            envSet[i] = new Environment(xStart+far,20,this,eType,10);
            far += 600;

        }

        return envSet;
    }

    private void drawWave(Wave wave,Graphics2D g2) {

        g2.drawImage(wave.getImage(),wave.x,(wave.y-waveHeight),40,waveHeight+10,null);

        if(Event.checkHit(girl,wave,girlSize,waveHeight)){

            g2.setColor(new Color(241,98,69));
            g2.fillRect(0,0,1000,1000);

            girl.health -= 20;

            if(girl.health <= 0){

                display.endGame(this.point);

                girl.health = new Girl().health;

                this.point = 0;

            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(System.currentTimeMillis() - lastPress > 600) {

            if(e.getKeyCode()==32 || e.getKeyCode()==38) {

                girl.jump(this);
                lastPress = System.currentTimeMillis();

            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyReleased(KeyEvent e){}

    public static void main(String[] arg) {

        display = new Display();

    }
}