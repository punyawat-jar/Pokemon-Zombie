import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.net.ssl.CertPathTrustManagerParameters;
import javax.sound.sampled.*; // for sounds
import java.util.*;
import java.util.Random;
import java.io.*;
import javax.swing.border.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

class Keyboard_bar {
    private JTextArea typearea;
    private int width, height, score;
    private MainApplication main;
    private ArrayList<Wordbox> word_AL;
    private MySoundEffect correct, wrong;
    private Compare_text compare;
    private boolean use_speed = false;

    public Keyboard_bar(ArrayList<Wordbox> wAL, MainApplication m) {
        word_AL = wAL;
        main = m;
        typearea = new JTextArea();
        typearea.setBounds(450, 600, 505, 40);
        typearea.setBackground(new Color(255, 221, 89, 215));
        typearea.setFont(new Font("SanSerif", Font.BOLD | Font.ITALIC, 25));

        typearea.setForeground(new Color(45, 20, 107));
        typearea.setBorder(
                new BevelBorder(BevelBorder.RAISED, new Color(255, 158, 89, 255), new Color(255, 158, 89, 255)));

        correct = new MySoundEffect("sound_effect/correct_sound.wav");
        wrong = new MySoundEffect("sound_effect/wrong_sound.wav");
        try {
            compare = new Compare_text(typearea, word_AL, main);
        } catch (Exception e) {
        }

        typearea.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (main.getUse_speed()) {
                        score = 2;
                    } else
                        score = 1;

                    if (typearea.getText().trim()
                            .equals(word_AL.get(main.threadlist.get(main.getCount_death())).getWord().trim())) {
                        word_AL.get(main.threadlist.get(main.getCount_death())).setvisible(false);
                        System.out.println();
                        System.out.println("Score ->>>>>>>>>>" + score);
                        main.getPlayer().setscore(score);
                        main.kill_zombie(main.threadlist.get(main.getCount_death()));
                        System.out.println("Current score -------------> " + main.getPlayer().getScore());

                        correct.playOnce();

                    } else {
                        wrong.playOnce();
                    }
                    typearea.setText(null);

                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    // main.pause();

                }
            }

            public void keyTyped(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {
            }
        });
    }

    public JTextArea getTypearea() {
        return typearea;
    }

    public void setPane(JLabel x, MainApplication m) {
        main = m;
        x.add(typearea);
    }

    public void setposition(int x, int y) {
        typearea.setBounds(x, y, width, height);
    }

    public void clearTypearea() {
        typearea.setText(null);
    }

    public void setUse_speed(boolean x) {
        use_speed = x;
    }
}

class Compare_text implements CaretListener {
    private JTextArea text_type;
    private ArrayList<Wordbox> wbox;
    private MainApplication main;
    private String[] word;
    private String[] text;

    public Compare_text(JTextArea T, ArrayList<Wordbox> W, MainApplication m) {
        text_type = T;
        wbox = W;
        main = m;
        text_type.addCaretListener(this);

    }

    public void caretUpdate(CaretEvent e) {
        try {
            int flag = 0;
            word = wbox.get(main.threadlist.get(main.getCount_death())).getWord().split("");
            wbox.get(main.threadlist.get(main.getCount_death())).setfontcolor(Color.YELLOW);
            for (int i = 0; i < word.length; i++) {
                text = text_type.getText().trim().split("");
            }
            for (int i = 0; i < text.length; i++) {
                try {

                    if ((text[i].trim().equals(word[i]) || text[0].isEmpty() == true) && flag != 1) {
                        wbox.get(main.threadlist.get(main.getCount_death())).setfontcolor(Color.WHITE);
                    } else if (!(text[i].trim().equals(word[i]))) {
                        flag = 1;
                        wbox.get(main.threadlist.get(main.getCount_death())).setfontcolor(Color.RED);
                    }
                } catch (Exception error) {
                    wbox.get(main.threadlist.get(main.getCount_death())).setfontcolor(Color.RED);
                }

            }

        } catch (Exception error) {
        }

    }
}
