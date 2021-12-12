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

public class Speed_Stopwatch extends JButton implements MouseInputListener, MouseMotionListener {
    private int amount;
    private int curX, curY;
    private int width = 50;
    private int height = 50;
    private MyImageIcon SpeedIcon;
    private JLabel SpeedAmount,pane;
    private MainApplication program;
    private ArrayList<ZombieThread> zombielist;
    private Keyboard_bar keybr;
    private Boolean useitem;
    private MySoundEffect use_Speed_sound = new MySoundEffect("sound_effect/speedUp.wav");

    public Speed_Stopwatch(MainApplication program, JLabel x, ArrayList<ZombieThread> zombie_AL,Keyboard_bar kb) {
        this.program = program;
        zombielist = zombie_AL;
        pane = x;
        keybr = kb;
        SpeedIcon = new MyImageIcon("items/speed_stopwatch.png").resize(width, height);
        curX = program.getWidth() - (width / 2) * 3;
        curY = height / 2;
        setBounds(curX, curY, width, height);
        setIcon(SpeedIcon);

        if(program.getcount_pic() == 4){
            amount = 2;
        }
        
        SpeedAmount = new JLabel("x"+amount+"");
        SpeedAmount.setBounds(1300,80,50,20);
        SpeedAmount.setForeground(Color.WHITE);
        SpeedAmount.setBackground(null);
        SpeedAmount.setFont(new Font("SanSerif", Font.BOLD , 25));

        addMouseListener(this);
        addMouseMotionListener(this);

        pane.add(SpeedAmount);
        pane.add(this);
        pane.validate();
        pane.repaint();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(amount != 0 && !program.getUse_speed() ){    
            for(int i=0; i<zombielist.size(); i++){
                zombielist.get(i).speedUp();
            }
            amount--;
            SpeedAmount.setText("x"+amount+"");
            use_Speed_sound.playOnce();
            pane.validate();
            pane.repaint();
            keybr.getTypearea().grabFocus();
        }
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
    public void setAmount(){
        amount++;
        SpeedAmount.setText("x"+amount+"");
        pane.repaint();
        pane.validate();
    }

}