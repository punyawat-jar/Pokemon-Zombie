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
    private MyImageIcon SlowIcon;
    private JLabel SlowAmount;
    private MainApplication program;
    private ArrayList<ZombieThread> zombielist;

    public Slow_Stopwatch(MainApplication program, JLabel x, ArrayList<ZombieThread> zombie_AL) {
        this.program = program;
        zombielist = zombie_AL;
        SlowIcon = new MyImageIcon("items/slow_stopwatch.png").resize(width, height);
        curX = program.getWidth() - (width / 2) * 6;
        curY = height / 2;
        setBounds(curX, curY, width, height);
        setIcon(SlowIcon);

        SlowAmount = new JLabel("x"+amount+"");
        SlowAmount.setBounds(1225,20,50,135);
        SlowAmount.setForeground(Color.WHITE);
        SlowAmount.setBackground(null);
        SlowAmount.setFont(new Font("SanSerif", Font.BOLD , 25));

        addMouseListener(this);
        addMouseMotionListener(this);

        x.add(SlowAmount);
        x.add(this);
        x.validate();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        //program.slowSpeed();
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
