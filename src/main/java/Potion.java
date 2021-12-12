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
    private JLabel PotionAmount, pane;
    private MainApplication program;
    private Player player;
    private Keyboard_bar keybr;
    private MySoundEffect use_potion_sound = new MySoundEffect("sound_effect/usepotion_soundeffect.wav");

    public Potion(MainApplication program, JLabel x, Player p, Keyboard_bar kb) {
        this.program = program;
        player = p;
        pane = x;
        keybr = kb;
        PotionIcon = new MyImageIcon("items/potion.png").resize(width, height);
        curX = program.getWidth() - (width / 2) * 9;
        curY = height / 2;
        setBounds(curX, curY, width, height);
        setIcon(PotionIcon);
        if (program.getcount_pic() == 2) {
            amount = 2;
        }

        PotionAmount = new JLabel("x" + amount + "");
        PotionAmount.setBounds(1150, 80, 50, 20);
        PotionAmount.setForeground(Color.WHITE);
        PotionAmount.setBackground(null);
        PotionAmount.setFont(new Font("SanSerif", Font.BOLD, 25));

        addMouseListener(this);
        addMouseMotionListener(this);

        pane.add(PotionAmount);
        pane.add(this);
        pane.validate();
        pane.repaint();
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
        if (this.getBounds().intersects(player.getLabel().getBounds()) && player.getHP() < 5 && amount != 0) {
            player.heal(pane);
            amount--;
            PotionAmount.setText("x" + amount + "");
            use_potion_sound.playOnce();
            pane.validate();
            pane.repaint();
            keybr.getTypearea().grabFocus();
        }
        resetbtn();

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

    public void setAmount() {
        amount++;
        PotionAmount.setText("x" + amount + "");
        pane.repaint();
        pane.validate();
    }

    public void resetbtn() {
        curX = program.getWidth() - (width / 2) * 9;
        curY = height / 2;
        setLocation(curX, curY);
    }
}