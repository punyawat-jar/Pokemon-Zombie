import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.net.ssl.CertPathTrustManagerParameters;
import javax.sound.sampled.*; // for sounds
import java.util.*;
import java.util.Random;
import java.io.*;
import javax.swing.border.*;
import javax.swing.event.MouseInputListener;

public class Potion extends JButton implements MouseInputListener, MouseMotionListener {
    private int amount = 0;
    private int curX, curY;
    private int width = 50;
    private int height = 50;
    private MyImageIcon PotionIcon;
    private JLabel PotionAmount;
    private MainApplication program;
    private Player player;

    public Potion(MainApplication program, JLabel x, Player player) {
        this.program = program;
        this.player = player;
        PotionIcon = new MyImageIcon("items/bomb.png").resize(width, height);
        curX = program.getWidth() - (width / 2) * 12;
        curY = height / 2;
        setBounds(curX, curY, width, height);
        setIcon(PotionIcon);
        addMouseListener(this);
        addMouseMotionListener(this);
        x.add(this);
        x.validate();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}