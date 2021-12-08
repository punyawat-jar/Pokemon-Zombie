import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.net.ssl.CertPathTrustManagerParameters;
import javax.sound.sampled.*; // for sounds
import java.util.*;
import java.util.Random;
import java.io.*;
import javax.swing.border.*;

class ZombieThread extends Thread {
    private MyImageIcon zomb1Img, zomb2Img, zomb3Img, zomb4Img, zomb5Img, zombBossImg; // Beginner & Boss
    private int zombSpeed = 300;
    private int zombCurX, zombCurY;
    private JLabel zombLabel;
    private int zombWidth, zombHeight;
    private String mode;
    private int i; // Order of each thread Ex, zombie'0 1 2 3 4' << i
    private boolean pauseGame = false,killed = false;
    private JLabel tempPane;
    private int tempCount;
    private MainApplication program;
    private Player tempPlayer;
    private MySoundEffect hurtSound;
    private JProgressBar tempProgressBar;
    private ArrayList<Wordbox> wbox = new ArrayList<Wordbox>();

    // ---------------------- ZombieThread Constructor----------------------
    public ZombieThread(String n, Player player, JLabel pane, String m, int order, int count, JProgressBar PBar,
            MainApplication prog,ArrayList<Wordbox> wb) {
        super(n);
        // The temp use for run();
        i = order;
        program = prog;
        tempPlayer = player;
        tempPane = pane;
        tempCount = count;
        tempProgressBar = PBar;
        zombCurX = pane.getWidth();
        mode = m;
        wbox = wb;
        importZombie(pane);
        pane.add(zombLabel);
        pane.validate();
        hurtSound = new MySoundEffect("sound_effect/Hurt_soundeffect.wav");
        start();
    }// end Constructor

    public void importZombie(JLabel pane) {
        if (mode == "Beginner") {
            // for (int i = 0; i < 10; i++) {
            int zombie = randomNum(4);
            switch (zombie) {
                case 0:
                    zombWidth = 98;
                    zombHeight = 157;
                    zomb1Img = new MyImageIcon("zombie/z01.png").resize(zombWidth, zombHeight);
                    zombLabel = new JLabel(zomb1Img);
                    break;
                case 1:
                    zombWidth = 140;
                    zombHeight = 139;
                    zomb2Img = new MyImageIcon("zombie/z02.png").resize(140, 139);
                    zombLabel = new JLabel(zomb2Img);
                    break;
                case 2:
                    zombWidth = 138;
                    zombHeight = 153;
                    zomb3Img = new MyImageIcon("zombie/z03.png").resize(138, 153);
                    zombLabel = new JLabel(zomb3Img);
                    break;
                case 3:

                    zombWidth = 140;
                    zombHeight = 140;
                    zomb4Img = new MyImageIcon("zombie/z04.png").resize(140, 140);
                    zombLabel = new JLabel(zomb4Img);
                    break;
            }
            zombCurY = pane.getHeight() - 185 - zombHeight;
            zombLabel.setBounds(zombCurX, zombCurY, zombWidth, zombHeight);
            zombSpeed = 250;
        } // end Beginner
        else if (mode == "Medium") {

            // for (int i = 0; i < 10; i++) {
            int zombie = randomNum(4);
            switch (zombie) {
                case 0:
                    zombWidth = 98;
                    zombHeight = 157;
                    zomb1Img = new MyImageIcon("zombie/z01.png").resize(zombWidth, zombHeight);
                    zombLabel = new JLabel(zomb1Img);
                    break;
                case 1:
                    zombWidth = 140;
                    zombHeight = 139;
                    zomb2Img = new MyImageIcon("zombie/z02.png").resize(zombWidth, zombHeight);
                    zombLabel = new JLabel(zomb2Img);
                    break;
                case 2:
                    zombWidth = 138;
                    zombHeight = 153;
                    zomb3Img = new MyImageIcon("zombie/z03.png").resize(zombWidth, zombHeight);
                    zombLabel = new JLabel(zomb3Img);
                    break;
                case 3:
                    zombWidth = 140;
                    zombHeight = 140;
                    zomb4Img = new MyImageIcon("zombie/z04.png").resize(zombWidth, zombHeight);
                    zombLabel = new JLabel(zomb4Img);
                    break;
            }
            zombCurY = pane.getHeight() - 185 - zombHeight;
            zombLabel.setBounds(zombCurX, zombCurY, zombWidth, zombHeight);
            zombSpeed = 200;
        } // end Medium
        else if (mode == "Hard") {
            int zombie = randomNum(4);
            switch (zombie) {
                case 0:
                    zombWidth = 143;
                    zombHeight = 140;
                    zomb1Img = new MyImageIcon("zombie/ez01.png").resize(zombWidth, zombHeight);
                    zombLabel = new JLabel(zomb1Img);
                    zombCurY = pane.getHeight() - 180 - zombHeight;
                    break;
                case 1:
                    zombWidth = 140;
                    zombHeight = 117;
                    zomb2Img = new MyImageIcon("zombie/ez02.png").resize(zombWidth, zombHeight);
                    zombLabel = new JLabel(zomb2Img);
                    zombCurY = pane.getHeight() - 160 - zombHeight;
                    break;
                case 2:
                    zombWidth = 140;
                    zombHeight = 153;
                    zomb3Img = new MyImageIcon("zombie/ez03.png").resize(zombWidth, zombHeight);
                    zombLabel = new JLabel(zomb3Img);
                    zombCurY = pane.getHeight() - 180 - zombHeight;
                    break;
                case 3:
                    zombWidth = 170;
                    zombHeight = 170;
                    zomb4Img = new MyImageIcon("zombie/ez04.png").resize(zombWidth, zombHeight);
                    zombLabel = new JLabel(zomb4Img);
                    zombCurY = pane.getHeight() - 180 - zombHeight;
                    break;
            }
            zombLabel.setBounds(zombCurX, zombCurY, zombWidth, zombHeight);
            zombSpeed = 150;
        } // end Hard
        else if (mode == "Nightmare") {
            int zombie = randomNum(4);
            switch (zombie) {
                case 0:
                    zombWidth = 140;
                    zombHeight = 140;
                    zomb1Img = new MyImageIcon("zombie/sz01.png").resize(zombWidth, zombHeight);
                    zombLabel = new JLabel(zomb1Img);
                    zombCurY = pane.getHeight() - 170 - zombHeight;
                    break;
                case 1:
                    zombWidth = 187;
                    zombHeight = 140;
                    zomb2Img = new MyImageIcon("zombie/sz02.png").resize(zombWidth, zombHeight);
                    zombLabel = new JLabel(zomb2Img);
                    zombCurY = pane.getHeight() - 200 - zombHeight;
                    break;
                case 2:
                    zombWidth = 140;
                    zombHeight = 140;
                    zomb3Img = new MyImageIcon("zombie/sz03.png").resize(zombWidth, zombHeight);
                    zombLabel = new JLabel(zomb3Img);
                    zombCurY = pane.getHeight() - 200 - zombHeight;
                    break;
                case 3:
                    zombWidth = 140;
                    zombHeight = 140;
                    zomb4Img = new MyImageIcon("zombie/z04.png").resize(zombWidth, zombHeight);
                    zombLabel = new JLabel(zomb4Img);
                    zombCurY = pane.getHeight() - 170 - zombHeight;
                    break;
                case 4:
                    zombWidth = 170;
                    zombHeight = 170;
                    zomb5Img = new MyImageIcon("zombie/ez04.png").resize(zombWidth, zombHeight);
                    zombLabel = new JLabel(zomb4Img);
                    zombCurY = pane.getHeight() - 170 - zombHeight;
                    break;
            }
            zombCurY = pane.getHeight() - 185 - zombHeight;
            zombLabel.setBounds(zombCurX, zombCurY, zombWidth, zombHeight);
            zombSpeed = 100;
        } // end NightMare
    }

    public void setPauseGame(boolean b) {
        pauseGame = b;
    }

    public boolean getPauseGame(){
        return pauseGame;
    }

    public int randomNum(int amount) {
        Random random = new Random();
        int randomNum = random.nextInt(amount);
        System.out.println(randomNum);
        return randomNum;
    }

    public void setZombSpeed(int spd) { // Can use for slowing down item
        zombSpeed = spd;
    }


    public void run() {
        // -------------------------
        System.out.println("mode = " + mode);
        System.out.println("zombCurX = " + zombCurX + ", zombCurY = " + zombCurY + " zombWidth = " + zombWidth
                + " , zombHeight" + zombHeight);

        System.out.println("Thread : " + Thread.currentThread().getName());
        if (mode == "Nightmare") {
            waitGetInNightmare(i);
        } else if (mode == "Hard") {
            waitGetInHard(i);
        } else {
            waitGetIn(i);
        }
        program.setPBar();
        move(tempPlayer, i);

        if (tempPlayer.getHP() == 0) {
            tempPane.remove(zombLabel);
            tempPane.repaint();
            // removeZombie(10);
            program.setGameResult("GameOver");
            return;
        }
        // --------- Remove Zombie when Hit Pikachu & decrease heart
        if (zombLabel.getBounds().intersects(tempPlayer.getLabel().getBounds())) {
            hurtSound.playOnce();
            tempPlayer.hitplayer(tempPane);
            kill_monster(i);
        }
    }// end run
    
    public void kill_monster(int i ){
        tempPane.remove(zombLabel);
        tempPane.repaint();
        wbox.get(i).setvisible(false);
        killed = true;
    }

    // -------------------- For randoming time Zombie Appear--------------
    // Use static method to lock class * If lock only Obj. all other thread will
    // work and wait together.
    synchronized public static void waitGetIn(int i) {
        int timeWait = 0;
        if (i == 0) {
            try {
                timeWait = 3000;
                Thread.sleep(timeWait);
            } catch (InterruptedException e) {
            }
        } else {
            Random r = new Random();
            int low = 5000;
            int high = 15000;
            timeWait = r.nextInt(high - low) + low;
            try {
                Thread.sleep(timeWait);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Thread: " + Thread.currentThread().getName() + " Waiting"
                + timeWait / 1000 + "sec");
    }

    synchronized public static void waitGetInHard(int i) {
        int timeWait = 0;
        if (i == 0) {
            try {
                timeWait = 3000;
                Thread.sleep(timeWait);
            } catch (InterruptedException e) {
            }
        } else {
            Random r = new Random();
            int low = 5000;
            int high = 10000;
            timeWait = r.nextInt(high - low) + low;
            try {
                Thread.sleep(timeWait);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Thread: " + Thread.currentThread().getName() + " Waiting"
                + timeWait / 1000 + "sec");
    }

    synchronized public static void waitGetInNightmare(int i) {
        int timeWait;
        if (i == 0) {
            timeWait = 3000;
            try {
                Thread.sleep(timeWait);
            } catch (InterruptedException e) {
            }
        } else {
            Random r = new Random();
            int low = 4000;
            int high = 7500;
            timeWait = r.nextInt(high - low) + low;
            try {
                Thread.sleep(timeWait);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Thread: " + Thread.currentThread().getName() + " Waiting"
                + timeWait / 1000 + "sec");
    }

    // ----------------- If zombie not hit pikachu, it walks toleft----------------
    public void move(Player player, int i) {
        System.out.println("Thread: Zombie" + i + " -> move");
        // While not Hit player & player not die. walk left
        while (!(zombLabel.getBounds().intersects(player.getLabel().getBounds())) &&
                player.getHP() != 0 && killed == false ) {
            zombLabel.setLocation(zombCurX, zombCurY);
            if (!pauseGame) {
                zombCurX = zombCurX - 5;
                zombLabel.repaint();
                wbox.get(i).wbox_move(zombCurX,zombCurY);
            } else {
                System.out.println("Pause ZombieThread: " + Thread.currentThread().getName());
            }
            zombLabel.repaint();
            try {
                Thread.sleep(zombSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } // end while
    }// end move

}// end Class ZombieThread
