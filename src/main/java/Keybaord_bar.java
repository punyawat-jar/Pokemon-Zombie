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
    private String a,b;
    public Keyboard_bar(ArrayList<Wordbox> wAL) {
        word_AL = wAL;
        typearea = new JTextArea();
        
        typearea.setBounds(50, 100, 500, 30);
        typearea.setFont(new Font("SanSerif", Font.BOLD, 25));
        // typearea.grabFocus();
        typearea.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    //if(typearea.getText().trim().contains(word_AL.get(main.getCount_death()).getWord().trim())){
                        
                        //main.kill_monster(main.threadlist.get(main.getCount_death()));

                    //}
                    
                    a = typearea.getText().trim();
                    b = word_AL.get(main.getCount_death()).getWord().trim();
                    
                    if(a.equals(b)){
                        ///score++
                        main.kill_monster(main.threadlist.get(main.getCount_death()));
                        
                        System.out.println("mY name is your");
                    }
                    typearea.setText(null);
                    
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE && main.getPauseGame() == false){
                    main.setPauseGame(true);
                }
                else if (e.getKeyCode() == KeyEvent.VK_ESCAPE && main.getPauseGame() == true){
                    main.setPauseGame(false);
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

    public void setPane(JLabel x,MainApplication m) {
        main = m;
        x.add(typearea);
    }

    public void setposition(int x, int y) {
        typearea.setBounds(x, y, width, height);
    }
}



class Compare_text implements CaretListener{
    private JTextArea in;
    private Wordbox out;

    public Compare_text(JTextArea p, Wordbox t){ 
	in = p;
    out = t;
	in.addCaretListener( this );
    }

    
    public void caretUpdate(CaretEvent e) {

        //out.setText( in.getText() );
    }
};
