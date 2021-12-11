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

public class Bomb extends JButton implements MouseInputListener, MouseMotionListener {
    private int amount = 0;
    private int curX, curY;
    private int width = 50;
    private int height = 50;
    private ImageIcon bombIcon;
    private MainApplication program;
    private ArrayList<ZombieThread> zombielist;

    public Bomb(MainApplication program, JLabel x, ArrayList<ZombieThread> zombie_AL) {
        this.program = program;
        zombielist = zombie_AL;
        bombIcon = new MyImageIcon("items/bomb.png").resize(width, height);
        // bombLabel = new JLabel(bombIcon);
        curX = program.getWidth() - (width / 2) * 3;
        curY = height / 2;
        // bombLabel.setBounds(curX, curY, width, height);
        setBounds(curX, curY, width, height);
        setIcon(bombIcon);
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
        for (int i = 0; i < 2; i++) {
            if (zombielist.get(program.threadlist.get(program.getCount_death())).getCurX() < program.getWidth()) {// true
                program.kill_zombie(program.threadlist.get(program.getCount_death()));
            }
        }

        curX = program.getWidth() - (width / 2) * 3;
        curY = height / 2;
        setLocation(curX, curY);

        amount--;
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
        curX = curX + e.getX();
        curY = curY + e.getY();
        setLocation(curX, curY);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
