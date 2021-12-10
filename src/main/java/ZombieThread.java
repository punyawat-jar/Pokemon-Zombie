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
    private MyImageIcon zomb1Img, zomb2Img, zomb3Img, zomb4Img, zomb5Img, zombBossImg, zombImg;// Beginner & Boss
    private int zombSpeed = 300;
    private int zombCurX, zombCurY;
    private JLabel zombLabel;
    private int zombWidth, zombHeight;
    private String mode;
    private int i; // Order of each thread Ex, zombie'0 1 2 3 4' << i
    private boolean pauseGame = false, killed = false;
    private JLabel tempPane;
    private int tempCount;
    private MainApplication program;
    private Player tempPlayer;
    private MySoundEffect hurtSound;
    private JProgressBar tempProgressBar;
    private ArrayList<Wordbox> wbox = new ArrayList<Wordbox>();
    private MyImageIcon readyGoImg;
    private JLabel readyGoLabel;
    private MySoundEffect readyGoSound;
    private int zombTimeWait;
    private Keyboard_bar keyb;

    // ---------------------- ZombieThread Constructor----------------------
    public ZombieThread(String n, Player player, JLabel pane, String m, int order, int count, JProgressBar PBar,
            MainApplication prog, ArrayList<Wordbox> wb,Keyboard_bar k) {
        super(n);
        // The temp use for run();
        i = order;
        program = prog;
        tempPlayer = player;
        tempPane = pane;
        tempCount = count;
        tempProgressBar = PBar;
        keyb = k;
        zombCurX = pane.getWidth();
        mode = m;
        readyGoSound = new MySoundEffect("sound_effect/321GoCountdown.wav");
        readyGoImg = new MyImageIcon("sound_effect/321_Go.gif");
        readyGoLabel = new JLabel(readyGoImg);
        
        wbox = wb;
        setUpZombie(pane);
        pane.add(zombLabel);
        // pane.validate();
        hurtSound = new MySoundEffect("sound_effect/Hurt_soundeffect.wav");
        start();
    }// end Constructor

    public void setUpZombie(JLabel pane) {
        if (i == 0) {
            readyGoLabel.setBounds(525, 230, 380, 214);
            tempPane.add(readyGoLabel);
            tempPane.validate();
            readyGoSound.playOnce();
        }
        if (mode == "Beginner") {
            // for (int i = 0; i < 10; i++) {
            int zombie = randomNum(4);
            switch (zombie) {
                case 0:
                    zombWidth = 98;
                    zombHeight = 157;
                    zombImg = new MyImageIcon("zombie/z01.png").resize(zombWidth, zombHeight);
                    break;
                case 1:
                    zombWidth = 140;
                    zombHeight = 139;
                    zombImg = new MyImageIcon("zombie/z02.png").resize(140, 139);
                    break;
                case 2:
                    zombWidth = 138;
                    zombHeight = 153;
                    zombImg = new MyImageIcon("zombie/z03.png").resize(138, 153);
                    break;
                case 3:
                    zombWidth = 140;
                    zombHeight = 140;
                    zombImg = new MyImageIcon("zombie/z04.png").resize(140, 140);
                    break;
            }
            zombLabel = new JLabel(zombImg);
            zombCurY = pane.getHeight() - 150 - zombHeight;
            zombSpeed = 25;

            Random r = new Random();
            int low = 5000;
            int high = 9000;
            zombTimeWait = r.nextInt(high - low) + low;
            zombTimeWait = r.nextInt(high - low) + low;
            if (i == 1) {
                zombWidth = 287;
                zombHeight = 290;
                zombImg = new MyImageIcon("zombie/zboss_beginner.png").resize(zombWidth, zombHeight);
                zombLabel = new JLabel(zombImg);
                zombCurY = pane.getHeight() - 150 - zombHeight;
                zombSpeed = 30;
                // break;
            }
            zombLabel.setBounds(zombCurX, zombCurY, zombWidth, zombHeight);
        } // end Beginner
        else if (mode == "Medium") {

            // for (int i = 0; i < 10; i++) {
            int zombie = randomNum(4);
            switch (zombie) {
                case 0:
                    zombWidth = 98;
                    zombHeight = 157;
                    zombImg = new MyImageIcon("zombie/z01.png").resize(zombWidth, zombHeight);
                    break;
                case 1:
                    zombWidth = 140;
                    zombHeight = 139;
                    zombImg = new MyImageIcon("zombie/z02.png").resize(zombWidth, zombHeight);
                    break;
                case 2:
                    zombWidth = 138;
                    zombHeight = 153;
                    zombImg = new MyImageIcon("zombie/z03.png").resize(zombWidth, zombHeight);
                    break;
                case 3:
                    zombWidth = 140;
                    zombHeight = 140;
                    zombImg = new MyImageIcon("zombie/z04.png").resize(zombWidth, zombHeight);
                    break;
            }
            zombLabel = new JLabel(zombImg);
            zombCurY = pane.getHeight() - 150 - zombHeight;
            zombSpeed = 20;
            Random r = new Random();
            int low = 5000;
            int high = 7000;
            zombTimeWait = r.nextInt(high - low) + low;
            zombTimeWait = r.nextInt(high - low) + low;
            if (i == 1) {
                zombWidth = 290;
                zombHeight = 351;
                zombImg = new MyImageIcon("zombie/zboss_medium.png").resize(zombWidth, zombHeight);
                zombLabel = new JLabel(zombImg);
                zombCurY = pane.getHeight() - 150 - zombHeight;
                zombSpeed = 25;
                // break;
            }
            zombLabel.setBounds(zombCurX, zombCurY, zombWidth, zombHeight);
        } // end Medium
        else if (mode == "Hard") {
            int zombie = randomNum(4);
            switch (zombie) {
                case 0:
                    zombWidth = 143;
                    zombHeight = 140;
                    zombImg = new MyImageIcon("zombie/ez01.png").resize(zombWidth, zombHeight);
                    zombCurY = pane.getHeight() - 145 - zombHeight;
                    break;
                case 1:
                    zombWidth = 140;
                    zombHeight = 117;
                    zombImg = new MyImageIcon("zombie/ez02.png").resize(zombWidth, zombHeight);
                    zombCurY = pane.getHeight() - 125 - zombHeight;
                    break;
                case 2:
                    zombWidth = 140;
                    zombHeight = 153;
                    zombImg = new MyImageIcon("zombie/ez03.png").resize(zombWidth, zombHeight);
                    zombCurY = pane.getHeight() - 145 - zombHeight;
                    break;
                case 3:
                    zombWidth = 170;
                    zombHeight = 170;
                    zombImg = new MyImageIcon("zombie/ez04.png").resize(zombWidth, zombHeight);
                    zombCurY = pane.getHeight() - 145 - zombHeight;
                    break;
            }
            zombLabel = new JLabel(zombImg);
            zombSpeed = 18;
            Random r = new Random();
            int low = 4000;
            int high = 6500;
            zombTimeWait = r.nextInt(high - low) + low;
            if (i == 1) {
                zombWidth = 237;
                zombHeight = 290;
                zombImg = new MyImageIcon("zombie/zboss_hard.png").resize(zombWidth, zombHeight);
                zombLabel = new JLabel(zombImg);
                zombCurY = pane.getHeight() - 145 - zombHeight;
                zombSpeed = 23;
                // break;
            }
            zombLabel.setBounds(zombCurX, zombCurY, zombWidth, zombHeight);
        } // end Hard
        else if (mode == "Nightmare") {
            int zombie = randomNum(4);
            switch (zombie) {
                case 0:
                    zombWidth = 140;
                    zombHeight = 140;
                    zombImg = new MyImageIcon("zombie/sz01.png").resize(zombWidth, zombHeight);
                    zombCurY = pane.getHeight() - 130 - zombHeight;
                    break;
                case 1:
                    zombWidth = 187;
                    zombHeight = 140;
                    zombImg = new MyImageIcon("zombie/sz02.png").resize(zombWidth, zombHeight);
                    zombCurY = pane.getHeight() - 165 - zombHeight;
                    break;
                case 2:
                    zombWidth = 140;
                    zombHeight = 140;
                    zombImg = new MyImageIcon("zombie/sz03.png").resize(zombWidth, zombHeight);
                    zombCurY = pane.getHeight() - 165 - zombHeight;
                    break;
                case 3:
                    zombWidth = 140;
                    zombHeight = 140;
                    zombImg = new MyImageIcon("zombie/z04.png").resize(zombWidth, zombHeight);
                    zombCurY = pane.getHeight() - 130 - zombHeight;
                    break;
                case 4:
                    zombWidth = 170;
                    zombHeight = 170;
                    zombImg = new MyImageIcon("zombie/ez04.png").resize(zombWidth, zombHeight);
                    zombCurY = pane.getHeight() - 165 - zombHeight;
                    break;
            }
            zombLabel = new JLabel(zombImg);
            zombSpeed = 15;
            Random r = new Random();
            int low = 2500;
            int high = 5000;
            zombTimeWait = r.nextInt(high - low) + low;
            if (i == 1) {
                zombWidth = 445;
                zombHeight = 300;
                zombImg = new MyImageIcon("zombie/zboss_nightmare.png").resize(zombWidth, zombHeight);
                zombLabel = new JLabel(zombImg);
                zombCurY = pane.getHeight() - 130 - zombHeight;
                zombSpeed = 20;
                // break;
            }
            zombLabel.setBounds(zombCurX, zombCurY, zombWidth, zombHeight);
        } // end NightMare
        else if (mode == "Boss") {
            int zombie = randomNum(11);
            switch (zombie) {
                case 0:
                    zombWidth = 98;
                    zombHeight = 157;
                    zombImg = new MyImageIcon("zombie/z01.png").resize(zombWidth, zombHeight);
                    zombCurY = pane.getHeight() - 215 - zombHeight;
                    break;
                case 1:
                    zombWidth = 140;
                    zombHeight = 139;
                    zombImg = new MyImageIcon("zombie/z02.png").resize(140, 139);
                    zombCurY = pane.getHeight() - 215 - zombHeight;
                    break;
                case 2:
                    zombWidth = 138;
                    zombHeight = 153;
                    zombImg = new MyImageIcon("zombie/z03.png").resize(138, 153);
                    zombCurY = pane.getHeight() - 215 - zombHeight;
                    break;
                case 3:
                    zombWidth = 140;
                    zombHeight = 140;
                    zombImg = new MyImageIcon("zombie/z04.png").resize(140, 140);
                    zombCurY = pane.getHeight() - 215 - zombHeight;
                    break;
                case 4:
                    zombWidth = 140;
                    zombHeight = 140;
                    zombImg = new MyImageIcon("zombie/sz01.png").resize(zombWidth, zombHeight);
                    zombCurY = pane.getHeight() - 220 - zombHeight;
                    break;
                case 5:
                    zombWidth = 187;
                    zombHeight = 140;
                    zombImg = new MyImageIcon("zombie/sz02.png").resize(zombWidth, zombHeight);
                    zombCurY = pane.getHeight() - 270 - zombHeight;
                    break;
                case 6:
                    zombWidth = 140;
                    zombHeight = 140;
                    zombImg = new MyImageIcon("zombie/sz03.png").resize(zombWidth, zombHeight);
                    zombCurY = pane.getHeight() - 270 - zombHeight;
                    break;
                case 7:
                    zombWidth = 140;
                    zombHeight = 140;
                    zombImg = new MyImageIcon("zombie/nz04.png").resize(zombWidth, zombHeight);
                    zombCurY = pane.getHeight() - 215 - zombHeight;
                    break;
                case 8:
                    zombWidth = 143;
                    zombHeight = 140;
                    zombImg = new MyImageIcon("zombie/ez01.png").resize(zombWidth, zombHeight);
                    zombCurY = pane.getHeight() - 215 - zombHeight;
                    break;
                case 9:
                    zombWidth = 140;
                    zombHeight = 153;
                    zombImg = new MyImageIcon("zombie/ez03.png").resize(zombWidth, zombHeight);
                    zombCurY = pane.getHeight() - 215 - zombHeight;
                    break;
                case 10:
                    zombWidth = 170;
                    zombHeight = 170;
                    zombImg = new MyImageIcon("zombie/ez04.png").resize(zombWidth, zombHeight);
                    zombCurY = pane.getHeight() - 215 - zombHeight;
                    break;
            }
            zombLabel = new JLabel(zombImg);
            zombSpeed = 10;

            Random r = new Random();
            int low = 2500;
            int high = 5500;
            zombTimeWait = r.nextInt(high - low) + low;
            if (i == 1) {
                zombWidth = 237;
                zombHeight = 290;
                zombImg = new MyImageIcon("zombie/zboss.png").resize(zombWidth, zombHeight);
                zombLabel = new JLabel(zombImg);
                zombCurY = pane.getHeight() - 210 - zombHeight;
                zombSpeed = 15;
                // break;
            }
            zombLabel.setBounds(zombCurX, zombCurY, zombWidth, zombHeight);
        } // end Boss
    }// end setUpZombie

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
        
        System.out.println("mode = " + mode);
        System.out.println("zombCurX = " + zombCurX + ", zombCurY = " + zombCurY + " zombWidth = " + zombWidth
                + " , zombHeight" + zombHeight);
    
        System.out.println("Thread : " + Thread.currentThread().getName());
    
        waitGetIn(i, zombTimeWait); // loop if PauseGame == true;
    
        System.out.println("pauseGame = " + pauseGame);
    
        program.setPBar();
    
        move(tempPlayer, i);
    
        if (tempPlayer.getHP() == 0) {
            kill_monster(i);
            tempPane.remove(zombLabel);
            tempPane.repaint();
            // removeZombie(10);
            program.setGameResult("GameOver");
            program.addCountStageEnd();
            // return;
        }
    
        else if (program.getCount_death() == 10) { // Win
            tempPane.remove(zombLabel);
            tempPane.repaint();
            program.setGameResult("Win");
            program.addCountStageEnd();
        }
    
        if ((program.getGameResult() == "GameOver" || program.getGameResult() == "Win")
                && program.getCountStageEnd() == 1) {
            // program.setCountStageEnd(0);
            program.stageEnd(mode);
        }
        // --------- Remove Zombie when Hit Pikachu & decrease heart
        if (zombLabel.getBounds().intersects(tempPlayer.getLabel().getBounds())) {
            hurtSound.playOnce();
            tempPlayer.hitplayer(tempPane);
            tempPane.remove(zombLabel);
            tempPane.repaint();
            keyb.clearTypearea();
            kill_monster(i);
    
            program.setCount_death(); // death++
        }
        
        // -------------------------
        
    }// end run

    public void kill_monster(int i) {
        tempPane.remove(zombLabel);
        tempPane.repaint();
        wbox.get(i).setvisible(false);
        killed = true;
    }

    // public void setkill_thread(boolean x){
    //     kill_thread = x;
    // }
    // -------------------- For randoming time Zombie Appear--------------
    // Use static method to lock class * If lock only Obj. all other thread will
    // work and wait together.
    synchronized public static void waitGetIn(int i, int timeWait) {
        if (i == 0) {
            try {
                timeWait = 3000;
                Thread.sleep(timeWait);
            } catch (InterruptedException e) {

                System.out.println("Thread " + i + " wake up.");
            }
        } else {
            try {
                Thread.sleep(timeWait);
            } catch (InterruptedException e) {

                System.out.println("Thread " + i + " wake up.");
            }
        }
        System.out.println("Thread: " + Thread.currentThread().getName() + " Waiting"
                + timeWait / 1000 + " sec");
    }

    // ----------------- If zombie not hit pikachu, it walks toleft----------------
    public void move(Player player, int i) {
        System.out.println("Thread: Zombie" + i + " -> move");
        // While not Hit player & player not die. walk left
        while (!(zombLabel.getBounds().intersects(player.getLabel().getBounds())) &&
                player.getHP() != 0 && killed == false ) {
            zombLabel.setLocation(zombCurX, zombCurY);
            zombCurX = zombCurX - 1;
            zombLabel.repaint();
            wbox.get(i).wbox_move(zombCurX - 10, zombCurY);

            zombLabel.repaint();
            try {
                Thread.sleep(zombSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } // end while
    }// end move

    public int getCurX() {
        return zombCurX;
    }

    public int getTimeWait() {
        return zombTimeWait;
    }
}

// end Class ZombieThread
