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
    boolean end = false,floor=false;
    ArrayList<JLabel> Item_AL = new ArrayList<JLabel>();
    public itemdrop(JLabel p,MainApplication m,ArrayList<JLabel> iAL){
        pane = p;
        main = m;
        Item_AL = iAL;
    }

    public void run(){
        while(!end){
            int rand_time = (int) Math.floor(Math.random()*(30000-15000+1)+15000);
            //Item_AL.add(new JLabel(new MyImageIcon("item_fall/potion.png").resize(100,100)));
            floor = false;
            Item_AL.get(0).setVisible(true);
            try{
                Thread.sleep(15000);         /// 1000 for debug only, use rand_time
            }
            catch(Exception e) {}
            Thread item = new Thread(){
                public void run(){
                    Random r = new Random();
                    
                    int CurX = r.nextInt(main.get_framewidth());
                    int CurY = 50;
                    
                    //Random ran_item = new Random(3);
                    int rand_item = (int) Math.floor(Math.random()*(3-0+1)+0);
                    //Item_AL.get(0);
                    pane.add(Item_AL.get(rand_item)); ///Note change 0 to rand_item
                    while(!floor){
                        Item_AL.get(rand_item).setBounds(CurX,CurY,100,100);
                        if(CurY < main.get_frameheight() - 150){        //Move item down
                            CurY += 10;
                        }
                        else{
                            floor = true;                               //item on the floor
                            pane.remove(Item_AL.get(rand_item));
                        }
                        pane.repaint();
                        try{
                            Thread.sleep(100);
                        }
                        catch(Exception e) {}
                    }

                }
            };
            item.start();
            pane.repaint();
            pane.validate();
        }
    }

    public void setEndgame(boolean e){
        end = e;
    }
}
