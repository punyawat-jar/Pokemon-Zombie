import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.net.ssl.CertPathTrustManagerParameters;
import javax.sound.sampled.*; // for sounds
import java.util.*;
import java.util.Random;
import java.io.*;
import java.lang.management.BufferPoolMXBean;

import javax.swing.border.*;
import javax.swing.event.MouseInputListener;

public class Bomb extends JButton implements MouseInputListener, MouseMotionListener {
    private int amount = 0;
    private int curX, curY;
    private int width = 50;
    private int height = 50;
    private MyImageIcon bombIcon;
    private JLabel BombAmount;
    private MainApplication program;
    private Player player;
    private ArrayList<ZombieThread> zombielist;

    public Bomb(MainApplication program, JLabel x, ArrayList<ZombieThread> zombie_AL,Player p) {
        this.program = program;
        zombielist = zombie_AL;
        player = p;
        bombIcon = new MyImageIcon("items/bomb.png").resize(width, height);
        curX = program.getWidth() - (width / 2) * 3;
        curY = height / 2;
        setBounds(curX, curY, width, height);
        setIcon(bombIcon);
        
        BombAmount = new JLabel("x"+amount+"");
        BombAmount.setBounds(1300,20,50,135);
        BombAmount.setForeground(Color.WHITE);
        BombAmount.setBackground(null);
        BombAmount.setFont(new Font("SanSerif", Font.BOLD , 25));

        addMouseListener(this);
        addMouseMotionListener(this);

        x.add(BombAmount);
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
            if (zombielist.get(program.threadlist.get(program.getCount_death())).getCurX() + 20 < program.getWidth() ) {// true
                program.kill_zombie(program.threadlist.get(program.getCount_death()));
                player.setscore();
                amount--;
            }
        }
        curX = program.getWidth() - (width / 2) * 3;
        curY = height / 2;
        setLocation(curX, curY);
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
