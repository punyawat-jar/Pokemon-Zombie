import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.net.ssl.CertPathTrustManagerParameters;
import javax.sound.sampled.*; // for sounds
import java.util.Random;
import javax.swing.border.*;

public class MainApplication extends JFrame {

    private JPanel contentpane;
    private JLabel drawpane;
    private JComboBox combo;
    private JToggleButton[] tb;
    private JTextField scoreText;
    private JLabel Label;
    private MyImageIcon bgImg, in_gamebg1Img, in_gamebg2Img, in_gamebg3Img, in_gamebg4Img, in_gamebg5Img;
    private ButtonGroup bgroup;
    // private MySoundEffect hitSound, themeSound;

    private int frameWidth = 1366, frameHeight = 768;
    private int itemWidth = 40, itemHeight = 50;
    private int score = 0;
 
    Tutorial Tframe;
    // main method
    public static void main(String[] args) {
        new MainApplication();
    }

    public MainApplication() {
        setTitle("PokemonGame!");
        setBounds(50, 50, frameWidth, frameHeight);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentpane = (JPanel) getContentPane();
        contentpane.setLayout(new BorderLayout());

        AddComponents();
    }

    public void AddComponents() {
        bgImg = new MyImageIcon("pokemon/night_bg2.png").resize(frameWidth, frameHeight);
        in_gamebg1Img = new MyImageIcon("pokemon/bg2.jpg").resize(frameWidth, frameHeight);

        drawpane = new JLabel();
        drawpane.setIcon(bgImg);
        drawpane.setLayout(null);
        contentpane.add(drawpane, BorderLayout.CENTER);

        // sound fx and bg music
        // hitSound = new MySoundEffect("resources/beep.wav");
        // themeSound = new MySoundEffect("resources/theme.wav");
        // themeSound.playLoop();

        // main menu button
        JButton button1 = new JButton("Start");
        JButton button2 = new JButton("Credit");
        JButton button3 = new JButton("Tutorial");
        JButton button4 = new JButton("Exit");
        button1.setBounds((frameWidth / 2) - 50, (frameHeight / 2) - 100, 100, 50);
        button2.setBounds((frameWidth / 2) - 50, (frameHeight / 2) - 33, 100, 50);
        button3.setBounds((frameWidth / 2) - 50, (frameHeight / 2) + 33, 100, 50);
        button4.setBounds((frameWidth / 2) - 50, (frameHeight / 2) + 100, 100, 50);
        drawpane.add(button1);
        drawpane.add(button2);
        drawpane.add(button3);
        drawpane.add(button4);

        // Cbutton_main.add(button1);
        // Cbutton_main.add(button2);
        // Cbutton_main.add(button3);

        // mode button
        JButton button5 = new JButton("Beginner");
        JButton button6 = new JButton("Easy");
        JButton button7 = new JButton("Normal");
        JButton button8 = new JButton("Hard");
        JButton button9 = new JButton("Nightmare");



        button3.addActionListener(new ActionListener() {     //Tutorial button3
            public void actionPerformed(ActionEvent e){
                if(Tframe == null){
                    Tframe = new Tutorial();
                }
                else{
                    Tframe.setVisible(true);
                }
            }
        });
            
        

        validate();
    }
}

class MyImageIcon extends ImageIcon {
    public MyImageIcon(String fname) {
        super(fname);
    }

    public MyImageIcon(Image image) {
        super(image);
    }

    public MyImageIcon resize(int width, int height) {
        Image oldimg = this.getImage();
        Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new MyImageIcon(newimg);
    }
};
