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
    private JLabel SlowAmount, pane;
    private MainApplication program;
    private ArrayList<ZombieThread> zombielist;
    private Keyboard_bar keybr;
    private MySoundEffect use_Slow_sound = new MySoundEffect("sound_effect/slowDown.wav");

    public Slow_Stopwatch(MainApplication program, JLabel x, ArrayList<ZombieThread> zombie_AL, Keyboard_bar kb) {
        this.program = program;
        zombielist = zombie_AL;
        pane = x;
        keybr = kb;
        SlowIcon = new MyImageIcon("items/slow_stopwatch.png").resize(width, height);
        curX = program.getWidth() - (width / 2) * 6;
        curY = height / 2;
        setBounds(curX, curY, width, height);
        setIcon(SlowIcon);
        if (program.getcount_pic() == 3) {
            amount = 2;
        }
        SlowAmount = new JLabel("x" + amount + "");
        SlowAmount.setBounds(1225, 80, 50, 20);
        SlowAmount.setForeground(Color.WHITE);
        SlowAmount.setBackground(null);
        SlowAmount.setFont(new Font("SanSerif", Font.BOLD, 25));

        addMouseListener(this);
        addMouseMotionListener(this);

        pane.add(SlowAmount);
        pane.add(this);
        pane.validate();
        pane.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (amount != 0 && !program.getUse_speed()) {
            for (int i = 0; i < zombielist.size(); i++) {
                zombielist.get(i).slowDown();
            }
            amount--;
            SlowAmount.setText("x" + amount + "");
            use_Slow_sound.playOnce();
            pane.validate();
            pane.repaint();
            keybr.getTypearea().grabFocus();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public void setAmount() {
        amount++;
        SlowAmount.setText("x" + amount + "");
        pane.repaint();
        pane.validate();
    }

}
