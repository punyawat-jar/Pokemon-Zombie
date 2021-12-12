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

        wlabel.setLayout(null);
        wlabel.setForeground(Color.WHITE);
        if (i == 1) {
            wlabel.setIcon(new MyImageIcon("Wbox/wbox_boss.png").resize(350, 60));
            wlabel.setSize(350, 60);
        } else {
            wlabel.setIcon(new MyImageIcon("Wbox/wbox.png").resize(120, 40));
            wlabel.setSize(120, 40);
        }
        wlabel.setHorizontalTextPosition(JLabel.CENTER);
        wlabel.setOpaque(false);
        wlabel.setVisible(false);

        pane = p;
        pane.add(wlabel);
        word = s;

    }

    public void wbox_move(int cur_x, int cur_y) {
        wlabel.setLocation(cur_x, cur_y - 40);
        wlabel.setVisible(true);
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
        System.out.println("111 Word box --------------------------------> " + wlabel.isVisible());
        wlabel.setVisible(x);
        pane.repaint();
        System.out.println("222 Word box --------------------------------> " + wlabel.isVisible());

    }

    public void setfontcolor(Color x) {
        wlabel.setForeground(x);
    }

}
