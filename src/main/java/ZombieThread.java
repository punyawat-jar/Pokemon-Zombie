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
    JLabel zombLabel = new JLabel();
    JLabel playerLabel = new JLabel(); // For Check Intersect
    int zombCurX, zombCurY;
    int zombWidth, zombHeight;
    int zombSpeed;
    int low, high;
    JLabel tempPane;
    boolean pauseGame = false;
    MainApplication program;
    MySoundEffect hurtSound;
    static String mode;

    // -------------------------------- ZombieThread Constructor
    // --------------------------
    public ZombieThread(String n, JLabel zl, JLabel pl, int zs, JLabel pane, MySoundEffect hs, String m) {
        super(n);
        zombLabel = zl;
        zombCurX = zl.getX();
        zombCurY = zl.getY();
        zombWidth = zl.getWidth();
        zombHeight = zl.getHeight();
        playerLabel = pl;
        zombSpeed = zs;
        tempPane = pane;
        hurtSound = hs;
        mode = m;
        System.out.println("mode = " + mode);
        System.out.println("zombCur X = " + zombCurX + ", zombCurY = " + zombCurY + " zombWidth = " + zombWidth
                + " , zombHeight" + zombHeight);

        zombLabel.setBounds(zombCurX, zombCurY, zombWidth, zombHeight);
        tempPane.add(zombLabel);
        tempPane.validate();
        start();
    }

    public void setPauseGame(boolean b) {
        pauseGame = b;
    }

    // ------------------------- For randoming time Zombie Appear ----------------
    // Use static method to lock class * If lock only Obj. all other thread will
    // work and wait together.
    synchronized public static void waitGetIn() {
        if (Thread.currentThread().getName() != "Zombie0") {
            Random r = new Random();
            int low = 5000;
            int high = 15000;
            int timeWait = r.nextInt(high - low) + low;
            try {
                Thread.sleep(timeWait);
            } catch (InterruptedException e) {
            }
        }
    }

    synchronized public static void waitGetInHard() {
        if (Thread.currentThread().getName() != "Zombie0") {
            Random r = new Random();
            int low = 5000;
            int high = 10000;
            int timeWait = r.nextInt(high - low) + low;
            try {
                Thread.sleep(timeWait);
            } catch (InterruptedException e) {
            }
        }
    }

    synchronized public static void waitGetInNightmare() {
        if (Thread.currentThread().getName() != "Zombie0") {
            Random r = new Random();
            int low = 4000;
            int high = 7500;
            int timeWait = r.nextInt(high - low) + low;
            try {
                Thread.sleep(timeWait);
            } catch (InterruptedException e) {
            }
        }
    }

    // -------------------- If zombie not hit pikachu, it walks to left
    // --------------------
    public void move() {
        while (!(zombLabel.getBounds().intersects(playerLabel.getBounds()))) {

            zombLabel.setLocation(zombCurX, zombCurY);
            if (!pauseGame) {
                zombCurX = zombCurX - 5;
                zombLabel.repaint();
            } else {
                System.out.println("Pause ZombieThread: " + Thread.currentThread().getName());
            }
            zombLabel.repaint();
            try {
                Thread.sleep(zombSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread = " + this.getName() + " move");
        } // end while

    }

    public void run() {
        System.out.println("thread = " + this.getName());
        if (mode == "Nightmare") {
            waitGetInNightmare();
        } else if (mode == "Hard") {
            waitGetInHard();
        } else {
            waitGetIn();
        }
        move();
        // --------- Remove Zombie when Hit Pikachu & decrease heart
        if (zombLabel.getBounds().intersects(playerLabel.getBounds())) {
            hurtSound.playOnce();
            tempPane.remove(zombLabel);
            tempPane.repaint();
        }
    } // end run
}
