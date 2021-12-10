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
    private JLabel wlabel, pane;
    private String word;

    public Wordbox(JLabel p, String s, int i, int n) {
        wlabel = new JLabel(s);
        //dtext = new DoubleText(wlabel,word);
        
        wlabel.setLayout(null);
        wlabel.setForeground(Color.WHITE);
        // wlabel.setFont(new Font(wlabel.getFont().getName(), Font.BOLD, 17));
        // if (n == 4) { // Boss Make Box Larger
        if (i == 1) {
            wlabel.setIcon(new MyImageIcon("Wbox/wbox_boss.png").resize(350, 60));
            wlabel.setSize(350, 60);
        } else {
            wlabel.setIcon(new MyImageIcon("Wbox/wbox.png").resize(120, 40));
            wlabel.setSize(120, 40);
        }
        // } else {
        // wlabel.setIcon(new MyImageIcon("Wbox/wbox.png").resize(120, 40));
        // wlabel.setSize(120, 40);
        // }
        wlabel.setHorizontalTextPosition(JLabel.CENTER);
        wlabel.setOpaque(false);
        wlabel.setVisible(false);

        // wlabel.setBackground(Color.lightGray);
        pane = p;
        pane.add(wlabel);
        word = s;

    }

    public void wbox_move(int cur_x, int cur_y) {
        wlabel.setLocation(cur_x, cur_y - 40);
        // System.out.println("x = " + cur_x + "y = " + cur_y);
        wlabel.setVisible(true);
        // wwlabel.setVisible(true);
        pane.validate();
        pane.repaint();

    }

    public void setWord(String w) {
        word = w;
    }

    public String getWord() {
        return word;
    }

    public void setvisible(boolean x) {
        wlabel.setVisible(x);
        
    }
    public void setfontcolor(Color x){
        wlabel.setForeground(x);
    }

}
