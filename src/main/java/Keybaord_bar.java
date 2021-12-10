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
    private int width, height;
    private MainApplication main;
    private ArrayList<Wordbox> word_AL;
    private MySoundEffect correct, wrong;
    Compare_text compare;

    // private String a,b;
    public Keyboard_bar(ArrayList<Wordbox> wAL) {
        word_AL = wAL;
        typearea = new JTextArea();
        typearea.setBounds(50, 100, 500, 30);
        typearea.setFont(new Font("SanSerif", Font.BOLD, 25));
        correct = new MySoundEffect("sound_effect/correct_sound.wav");
        wrong = new MySoundEffect("sound_effect/wrong_sound.wav");
        // typearea.grabFocus();

        typearea.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    // if(typearea.getText().trim().contains(word_AL.get(main.getCount_death()).getWord().trim())){
                    // main.kill_monster(main.threadlist.get(main.getCount_death()));
                    // }
                    // a = typearea.getText().trim();
                    // b = word_AL.get(main.threadlist.get(main.getCount_death())).getWord().trim();
                    // System.out.println("-" + a + "-");
                    // System.out.println("*" + b + "*");
                    // System.out.println("death count = " + main.getCount_death() );
                    // for(int i =0;i<10;i++){
                    // System.out.println("WordAL = " + word_AL.get(i).getWord());
                    // }
                    if (typearea.getText().trim()
                            .equals(word_AL.get(main.threadlist.get(main.getCount_death())).getWord().trim())) {
                        /// score++
                        main.kill_zombie(main.threadlist.get(main.getCount_death()));
                        correct.playOnce();
                        System.out.println("count is = " + main.getCount_death());
                        System.out.println("thread is = " + main.threadlist);
                        System.out.println("mY name is your");

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
                // compare = new Compare_text( typearea, word_AL,main,2);
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
}

class Compare_text implements CaretListener {
    private JTextArea text_type;
    private ArrayList<Wordbox> wbox;
    private MainApplication main;
    int position;

    public Compare_text(JTextArea T, ArrayList<Wordbox> W, MainApplication m, int count) {
        text_type = T;
        wbox = W;
        main = m;
        text_type.addCaretListener(this);
        text_type.addCaretListener(this);
        position = count;
    }

    public void caretUpdate(CaretEvent e) {
        if (text_type.getText().contains(wbox.get(main.threadlist.get(main.getCount_death())).getWord().trim())) {
            if (text_type.getText().contains(wbox.get(main.threadlist.get(main.getCount_death())).getWord().trim())) {
                System.out.println(wbox.get(main.threadlist.get(main.getCount_death())).getWord().trim());
            }

            // out.setText( in.getText() );
        }
    }
}