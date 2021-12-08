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
        wlabel = new JLabel();
        wlabel.setLayout(new FlowLayout());
        wlabel.setSize(20, 30);
        wlabel.setOpaque(true);
        wlabel.setVisible(true);
        wlabel.setBackground(Color.lightGray);
        pane = p;
        pane.add(wlabel);
        word = s;
    }

    public void wbox_move(int cur_x, int cur_y){
        wlabel.setLocation(cur_x,cur_y);
        System.out.println("x = " + cur_x + "y = " + cur_y);
        pane.validate(); 
        pane.repaint();
    }
    public void setWord(String w){
        word = w;
    }

}
