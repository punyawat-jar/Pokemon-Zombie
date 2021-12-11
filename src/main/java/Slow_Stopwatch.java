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

public class Slow_Stopwatch extends JButton implements MouseInputListener, MouseMotionListener {
    private int amount;
    private int curX, curY;
    private int width = 50;
    private int height = 50;
    private ArrayList<Integer> normalZombSpd;
    private MyImageIcon SlowIcon;
    private JLabel SlowAmount;
    private MainApplication program;
    private ArrayList<ZombieThread> zombielist;

    public Slow_Stopwatch(MainApplication program, JLabel x, ArrayList<ZombieThread> zombie_AL, int a) {
        for (int i = 0; i < zombie_AL.size(); i++) {
            normalZombSpd.add(zombie_AL.get(i).get_zombSpd());
        }
        amount = a;
        this.program = program;
        zombielist = zombie_AL;
        SlowIcon = new MyImageIcon("items/slow_stopwatch.png").resize(width, height);
        curX = program.getWidth() - (width / 2) * 6;
        curY = height / 2;
        setBounds(curX, curY, width, height);
        setIcon(SlowIcon);
        addMouseListener(this);
        addMouseMotionListener(this);
        x.add(this);
        x.validate();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        program.slowSpeed();
        program.normalSpeed(normalZombSpd);
        amount--;
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
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

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
