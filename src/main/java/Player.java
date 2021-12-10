import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.net.ssl.CertPathTrustManagerParameters;
import javax.sound.sampled.*; // for sounds
import java.util.*;
import java.util.Random;
import java.io.*;
import javax.swing.border.*;

class Player {
    private int HP, Score;
    private int playerwidth = 200, playerhight = 165, healthbarwidth = 180, healthbarhight = 30;
    private MyImageIcon player, healthbar_pic;
    private JLabel playerLabel, HP_Label;
    private ArrayList<JLabel> HP_AL = new ArrayList<JLabel>();
    String[] HP_bar = { "health bar/H0.png", "health bar/H1.png", "health bar/H2.png", "health bar/H3.png",
            "health bar/H4.png", "health bar/H5.png" };

    public Player(JLabel x) {
        player = new MyImageIcon("pokemon/pikachuready.png").resize(playerwidth, playerhight);
        playerLabel = new JLabel(player);
        playerLabel.setBounds(0, 440, playerwidth, playerhight);
        x.add(playerLabel);
        x.validate();

        for (int i = 0; i < HP_bar.length; i++) {
            healthbar_pic = new MyImageIcon(HP_bar[i]).resize(healthbarwidth, healthbarhight);
            HP_Label = new JLabel(healthbar_pic);

            HP_Label.setBounds(45, 50, healthbarwidth, healthbarhight);
            // HP_Label.setBounds(45,420,healthbarwidth,healthbarhight);
            HP_AL.add(HP_Label);
        }
        HP = HP_AL.size() - 1;
        x.add(HP_AL.get(5));

        Score = 0;
    }

    public void hitplayer(JLabel x) {
        x.remove(HP_AL.get(HP));
        HP -= 1;
        System.out.println("HP is " + HP);
        x.add(HP_AL.get(HP));
    }

    public void resetHP() {
        HP = 4;
    }

    synchronized public int getHP() {
        return HP;
    }

    synchronized public void setscore(/*int x*/) {
        //this.Score = x;
        Score++;
        System.out.printf("Your Score: %d\n",Score);
    }

    public JLabel getLabel() {
        return playerLabel;
    }
    
    public int getScore(){
        return Score;
    }
}// end Player
