import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.net.ssl.CertPathTrustManagerParameters;
import javax.sound.sampled.*; // for sounds
import java.util.*;
import java.util.Random;
import java.io.*;
import javax.swing.border.*;
import java.math.RoundingMode;
import java.text.NumberFormat; 

class Player {
    private int HP, Score;
    private int playerwidth = 200, playerhight = 165, healthbarwidth = 180, healthbarhight = 30;
    private MyImageIcon player, healthbar_pic;
    private JLabel playerLabel, HP_Label,pane,scoreLabel ;
    private ArrayList<JLabel> HP_AL = new ArrayList<JLabel>();
    private String[] poke_list;
    private int custom, mode;
    String[] HP_bar = { "health bar/H0.png", "health bar/H1.png", "health bar/H2.png", "health bar/H3.png",
            "health bar/H4.png", "health bar/H5.png" };

    String[] list_player = { "custom_poke/poke11.png", "custom_poke/poke22.png", "custom_poke/poke33.png",
            "custom_poke/poke44.png", "custom_poke/poke55.png" };

    public Player(JLabel x, int c, int m) {
        custom = c;
        mode = m;
        pane = x;
        scoreLabel = new JLabel();
        player = new MyImageIcon(list_player[custom]).resize(playerwidth, playerhight);
        playerLabel = new JLabel(player);
        // System.out.println("Play heyqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
        if (mode == 4) {
    
            playerLabel.setBounds(0, 370, playerwidth, playerhight);
        } else
            playerLabel.setBounds(0, 455, playerwidth, playerhight);
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
        setscore(0);
    }

    public void hitplayer(JLabel x) {
        x.remove(HP_AL.get(HP));
        HP -= 1;
        x.add(HP_AL.get(HP));
    }

    public void resetHP() {
        HP = 4;
    }

    synchronized public int getHP() {
        return HP;
    }

    synchronized public void setscore(int x) {
        // this.Score = x;
        Score+=x;
        System.out.printf("Your Score: %d\n", Score);
        showScore(pane);
    }

    public void heal(JLabel x) {
        if (HP < 5) {
            x.remove(HP_AL.get(HP));
            HP += 1;
            System.out.println("HP is " + HP);
            x.add(HP_AL.get(HP));
        }
    }

    public JLabel getLabel() {
        return playerLabel;
    }

    public int getScore() {
        return Score;
    }

    public JLabel getScoreLabel(){
        return scoreLabel;
    }

    public void showScore(JLabel x){
        NumberFormat scoreFormat = NumberFormat.getInstance();
        scoreFormat.setGroupingUsed(true);

        scoreLabel.setText("score : "+scoreFormat.format(Score));
        scoreLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBackground(null);
        scoreLabel.setBounds(45, healthbarhight+55, 200, 30);
        
        x.add(scoreLabel);
        scoreLabel.repaint();
    }

}// end Player
