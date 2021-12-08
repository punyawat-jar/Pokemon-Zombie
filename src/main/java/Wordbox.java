import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.net.ssl.CertPathTrustManagerParameters;
import javax.sound.sampled.*;
import java.util.*;
import java.util.Random;
import java.io.*;
import javax.swing.border.*;

public class Wordbox {
    private JLabel wlabel,pane;
    private String word;
    
    public Wordbox(JLabel p,String s) {
        wlabel = new JLabel(s,SwingConstants.CENTER);
        //dtext = new DoubleText(wlabel,word);
        wlabel.setLayout(new FlowLayout());
        wlabel.setSize(120, 40);
        wlabel.setOpaque(true);
        wlabel.setVisible(false);
        wlabel.setBackground(Color.lightGray);
        pane = p;
        pane.add(wlabel);
        word = s;
        
    }

    public void wbox_move(int cur_x, int cur_y){
        wlabel.setLocation(cur_x,cur_y);
        //System.out.println("x = " + cur_x + "y = " + cur_y);
        wlabel.setVisible(true);
        pane.validate(); 
        pane.repaint();
    }
    public void setWord(String w){
        word = w;
    }
    public String getWord(){
        return word;
    }
    public void setvisible(boolean x){
        wlabel.setVisible(x);
    }
    public void compareword(String s){

    }
}
