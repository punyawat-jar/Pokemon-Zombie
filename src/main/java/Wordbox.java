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
    JLabel wlabel;
    
    public void Wordbox(JLabel pane) {
        wlabel = new JLabel();
        wlabel.setLayout(new FlowLayout());
        wlabel.setBounds(500, 500, 400, 35);
        wlabel.setOpaque(true);
        wlabel.setBackground(Color.lightGray);

        pane.add(wlabel);

    }

}
