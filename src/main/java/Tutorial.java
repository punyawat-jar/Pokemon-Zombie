import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Tutorial extends JFrame{

    private JPanel Tcontentpane;
    private JLabel Tdrawpane,bgImg;
    private String[] p = {"tutorial/p1.jpg","tutorial/p2.jpg","tutorial/p3.jpg"};
    private MyImageIcon bg;
    private ArrayList<JLabel> pic_AL = new ArrayList<JLabel>();
    private int count = 0;
    private int frameWidth = 800, frameHeight = 600;
    private MySoundEffect buttonSound;

    

    public static void main(String[] args){
        new Tutorial();
    }

    public Tutorial(){
        setTitle("Tutorial");
        setBounds(50,50,frameWidth,frameHeight);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Tcontentpane = (JPanel) getContentPane();
        Tcontentpane.setLayout(new BorderLayout());
        
        AddComponents();
    }

    public void AddComponents(){
        
        Tdrawpane = new JLabel();
        Tdrawpane.setLayout(null);
        Tcontentpane.add(Tdrawpane,BorderLayout.CENTER);

        Tdrawpane.setIcon(new ImageIcon("pokemon/view.gif"));
        JButton button1 = new JButton("Next");
        JButton button2 = new JButton("Previous");

        buttonSound = new MySoundEffect("sound_effect/button_soundeffect.wav");

        button1.setBounds((frameWidth / 2) + 120, (frameHeight / 2) + 150, 150, 50);     ///Size and position of btn
        button2.setBounds((frameWidth / 2) - 220, (frameHeight / 2) + 150, 150, 50);

        Tdrawpane.add(button1);
        Tdrawpane.add(button2);

        readpic();
        Tdrawpane.add(pic_AL.get(0));

        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                buttonSound.playOnce();
                if(count<pic_AL.size()-1){;
                    Tdrawpane.remove(pic_AL.get(count));
                    count+=1;
                    Tdrawpane.add(pic_AL.get(count));
                    repaint();
                    ///Use arraylist to create list picture label then use add/remove
                }
                
            }
        });

        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                buttonSound.playOnce();
                if(count>0){
                    Tdrawpane.remove(pic_AL.get(count));
                    count-=1;
                    Tdrawpane.add(pic_AL.get(count));
                    
                    repaint();
                }
            }
        });
        
        validate();
        repaint();
    }

    public void readpic(){
        for(int i=0;i<p.length;i++){
            JLabel label = new JLabel(new ImageIcon(p[i]));
            label.setBounds(170,40,500,250);
            pic_AL.add(label);
        }
    }

}
