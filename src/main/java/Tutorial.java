import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.util.*;

public class Tutorial extends JFrame {

    private JPanel Tcontentpane;
    private JLabel Tdrawpane, bgImg;
    private String[] p = { "tutorial/1.png", "tutorial/2.png", "tutorial/3.png", "tutorial/4.png" };
    private MyImageIcon bg, prevButton, nextButton;
    private ArrayList<JLabel> pic_AL = new ArrayList<JLabel>();
    private int count = 0;
    private int frameWidth = 800, frameHeight = 600;
    private MySoundEffect buttonSound;

    public static void main(String[] args) {
        new Tutorial();
    }

    public Tutorial() {
        setTitle("Tutorial");
        setBounds(50, 50, frameWidth, frameHeight);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Tcontentpane = (JPanel) getContentPane();
        Tcontentpane.setLayout(new BorderLayout());

        AddComponents();
    }

    public void AddComponents() {
        setUpCursor(Tcontentpane);
        Tdrawpane = new JLabel();
        Tdrawpane.setLayout(null);
        Tcontentpane.add(Tdrawpane, BorderLayout.CENTER);

        Tdrawpane.setIcon(new ImageIcon("bg/view.gif"));
        prevButton = new MyImageIcon("button_and_cursor/PreviousButton.png").resize(138, 50);
        nextButton = new MyImageIcon("button_and_cursor/NextButton.png").resize(138, 50);

        buttonSound = new MySoundEffect("sound_effect/button_soundeffect.wav");

        JButton button1 = new JButton("Next");
        JButton button2 = new JButton("Previous");
        setUpButton(button1, nextButton);
        setUpButton(button2, prevButton);
        button1.setBounds((frameWidth / 2) + 120, (frameHeight / 2) + 150, 145, 50); /// Size and position of btn
        button2.setBounds((frameWidth / 2) - 220, (frameHeight / 2) + 150, 145, 50);

        Tdrawpane.add(button1);
        Tdrawpane.add(button2);

        readpic();
        Tdrawpane.add(pic_AL.get(0));

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonSound.playOnce();
                if (count < pic_AL.size() - 1) {
                    ;
                    Tdrawpane.remove(pic_AL.get(count));
                    count += 1;
                    Tdrawpane.add(pic_AL.get(count));
                    repaint();
                    /// Use arraylist to create list picture label then use add/remove
                }

            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonSound.playOnce();
                if (count > 0) {
                    Tdrawpane.remove(pic_AL.get(count));
                    count -= 1;
                    Tdrawpane.add(pic_AL.get(count));

                    repaint();
                }
            }
        });

        validate();
        repaint();
    }

    public void readpic() {
        for (int i = 0; i < p.length; i++) {
            MyImageIcon pic = new MyImageIcon(p[i]).resize(600, 324);
            ;

            JLabel label = new JLabel(pic);
            label.setBounds(100, 30, 600, 324);
            pic_AL.add(label);
        }
    }

    // -------------------------------- Set up Cursor & Button
    // ------------------------
    public void setUpButton(JButton button, MyImageIcon img) {
        button.setIcon(img);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void setUpCursor(JPanel mainpane) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("button_and_cursor/normalCursor.png");
        Cursor c = toolkit.createCustomCursor(image, new Point(mainpane.getX(), mainpane.getY()), "img");
        mainpane.setCursor(c);
    }
}
