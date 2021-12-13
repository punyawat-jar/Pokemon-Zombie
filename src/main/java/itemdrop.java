import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;
import javax.net.ssl.CertPathTrustManagerParameters;
import javax.sound.sampled.*; // for sounds
import java.util.*;
import java.util.Random;
import java.io.*;
import javax.swing.border.*;
import java.util.Timer;

public class itemdrop extends Thread {
    JLabel pane;
    MainApplication main;
    boolean end = false, floor = false;
    Thread item;
    ArrayList<JLabel> Item_AL = new ArrayList<JLabel>();

    public itemdrop(JLabel p, MainApplication m, ArrayList<JLabel> iAL) {
        pane = p;
        main = m;
        Item_AL = iAL;
    }

    public void run() {
        while (!end) {
            int rand_time = (int) Math.floor(Math.random() * (20000 - 18000 + 1) + 18000);
            
            try {
                Thread.sleep(rand_time); /// 10000 for debug only, use rand_time
            } catch (Exception e) {
            }
            item = new Thread() {
                public void run() {
                    int CurX = (int) Math.floor(Math.random() * ((main.get_framewidth() - 200) - 450 + 1) + 450);
                    int CurY = 50;
                    int rand_item = (int) Math.floor(Math.random() * (3 - 0 + 1) + 0);
                    // System.out.println("CurX is ==================== " + CurX);
                    pane.add(Item_AL.get(rand_item));
                    Item_AL.get(rand_item).setVisible(true);
                    floor = false;
                    while (!floor) {
                        Item_AL.get(rand_item).setBounds(CurX, CurY, 100, 100);
                        if (CurY < main.get_frameheight() - 150) { // Move item down
                            // System.out.println("CurY is == " + CurY + "frameheight()-150 ==" + (main.get_frameheight() - 150));
                            CurY += 10;
                            pane.repaint();
                            pane.validate();
                        } else {
                            floor = true; // item on the floor
                            pane.remove(Item_AL.get(rand_item));
                        }
                        pane.repaint();
                        pane.validate();
                        try {
                            Thread.sleep(100);
                        } catch (Exception e) {
                        }
                    }

                }
            };
            item.start();
            pane.repaint();
            pane.validate();
        }
    }

    public void setMouseinteracted(boolean x){
        floor = x;
    }

    public void setEndgame(boolean e) {
        end = e;
    }
}
