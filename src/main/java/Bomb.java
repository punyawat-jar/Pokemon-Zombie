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
    private JLabel BombAmount,pane;
    private MainApplication program;
    private Player player;
    private ArrayList<ZombieThread> zombielist;
    private ArrayList<Wordbox> word_AL;
    private Keyboard_bar keybr;
    private MySoundEffect use_Bomb_sound = new MySoundEffect("sound_effect/Explosion.wav");

    public Bomb(MainApplication program, JLabel x, ArrayList<ZombieThread> zombie_AL,Player p,Keyboard_bar kb,ArrayList<Wordbox> w_AL) {
        this.program = program;
        zombielist = zombie_AL;
        player = p;
        pane = x;
        word_AL = w_AL;
        keybr = kb;
        bombIcon = new MyImageIcon("items/bomb.png").resize(width, height);
        curX = program.getWidth() - (width / 2) * 12;
        curY = height / 2;
        setBounds(curX, curY, width, height);
        setIcon(bombIcon);
        if(program.getcount_pic() == 1){
            amount = 2;
        }
        
        BombAmount = new JLabel("x"+amount+"");
        BombAmount.setBounds(1075,80,50,20);
        BombAmount.setForeground(Color.WHITE);
        BombAmount.setBackground(null);
        BombAmount.setFont(new Font("SanSerif", Font.BOLD , 25));

        addMouseListener(this);
        addMouseMotionListener(this);

        pane.add(BombAmount);
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

        if(amount!=0 && (!program.getUse_speed() || !program.getUse_slow())){
            try{
                for (int i = 0; i < 2; i++) {
                    if (zombielist.get(program.threadlist.get(program.getCount_death())).getCurX() + 20 < program.getWidth() ) {// true
                        use_Bomb_sound.playOnce();
                        program.kill_zombie(program.threadlist.get(program.getCount_death()));
                        word_AL.get(program.threadlist.get(program.getCount_death())).setvisible(false);;
                        player.setscore(1);
                    }   
                }
                amount--;
                BombAmount.setText("x"+amount+"");
                pane.validate();
                pane.repaint();
                keybr.getTypearea().grabFocus();
            }
            catch (Exception error) {}

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

    public void setAmount(){
        amount++;
        BombAmount.setText("x"+amount+"");
        pane.repaint();
        pane.validate();
    }

    public void resetbtn(){
        curX = program.getWidth() - (width / 2) * 12;
        curY = height / 2;
        setLocation(curX, curY);
    }
}
