import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.net.ssl.CertPathTrustManagerParameters;
import javax.sound.sampled.*; // for sounds
import java.util.*;
import java.util.Random;
import java.io.*;
import javax.swing.border.*;

//Main Frame
public class MainApplication extends JFrame {
    //------------------------------- Component -------------------------------
    private JPanel contentpane;
    private JLabel drawpane;
    private JComboBox combo;
    private JToggleButton[] tb;
    private JTextField scoreText;
    private JLabel Label;
    private MyImageIcon bgImg, bgImg2, in_gamebg1Img, in_gamebg2Img, in_gamebg3Img, in_gamebg4Img, in_gamebg5Img;
    private MyImageIcon startButton, creditButton, tutorialButton, exitButton;
    private ButtonGroup bgroup;

    private MySoundEffect menuSong, creditSong, beginnerSong, mediumSong, hardSong, nightmareSong, bossSong;
    private MySoundEffect buttonSound, normalHitSound, softHitSound, criHitSound, hurtSound, gameOverSound, winSound, usedItemSound;

    private JLabel zomb1Label, zomb2Label, zomb3Label;
    private JLabel pikaReadyLabel;
    private MyImageIcon zomb1Img, zomb2Img, zomb3Img;
    private MyImageIcon pikaReadyImg;
    // private MySoundEffect hitSound, themeSound;

    private int frameWidth = 1366, frameHeight = 768;
    private int itemWidth = 40, itemHeight = 50;
    private int score = 0;

    Tutorial Tframe;

    private String []mode = {"Vocab/easy.txt","Vocab/beginner.txt"};
    ArrayList<Mode> modeList = new ArrayList<Mode>();  

    //------------------------------- Main Method -------------------------------
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
        // add Vocab
        readFile(mode);
    }//end MainApplication Constructor;

    public void AddComponents() {
        bgImg = new MyImageIcon("pokemon/menu_bg.png").resize(frameWidth, frameHeight);
        bgImg2 = new MyImageIcon("pokemon/cleanMenu_bg.png").resize(frameWidth, frameHeight);
        in_gamebg1Img = new MyImageIcon("pokemon/nature-lu.png").resize(frameWidth, frameHeight);
        in_gamebg2Img = new MyImageIcon("pokemon/bg2.jpg").resize(frameWidth, frameHeight);
        in_gamebg3Img = new MyImageIcon("pokemon/night_bg2.png").resize(frameWidth, frameHeight);
        in_gamebg4Img = new MyImageIcon("pokemon/bg1.png").resize(frameWidth, frameHeight);
        in_gamebg5Img = new MyImageIcon("pokemon/night_bg1.png").resize(frameWidth, frameHeight);

        startButton = new MyImageIcon("pokemon/StartButton.png").resize(138,50);
        creditButton = new MyImageIcon("pokemon/CreditButton.png").resize(138,50);
        tutorialButton = new MyImageIcon("pokemon/TutorialButton.png").resize(138,50);
        exitButton = new MyImageIcon("pokemon/ExitButton.png").resize(138,50);


        drawpane = new JLabel();
        drawpane.setIcon(bgImg);
        drawpane.setLayout(null);
        contentpane.add(drawpane, BorderLayout.CENTER);

        //------------------------------- Pokemon -----------------------------------
        
        pikaReadyImg = new MyImageIcon("pokemon/pikachuready.png").resize(200, 165);
        pikaReadyLabel = new JLabel(pikaReadyImg);
        pikaReadyLabel.setBounds(0,440, 200, 165);
        //---------------------------- Sound --------------------------------------
        buttonSound = new MySoundEffect("sound_effect/button_soundeffect.wav");
	    normalHitSound   = new MySoundEffect("sound_effect/NormalHit_soundeffect.wav");
	    softHitSound   = new MySoundEffect("sound_effect/SoftHit_soundeffect.wav");
        criHitSound   = new MySoundEffect("sound_effect/CriticalHit_soundeffect.wav");
        hurtSound   = new MySoundEffect("sound_effect/Hurt_soundeffect.wav");
        gameOverSound   = new MySoundEffect("sound_effect/GameOver_soundeffect.wav");
        winSound   = new MySoundEffect("sound_effect/Win_soundeffect.wav");
        usedItemSound   = new MySoundEffect("sound_effect/UsedItem_soundeffect.wav");
        
	    menuSong = new MySoundEffect("song/menu_song.wav"); 
	    creditSong = new MySoundEffect("song/credit_song.wav"); 
	    beginnerSong = new MySoundEffect("song/beginner_and_tutorial_song.wav"); 
	    mediumSong = new MySoundEffect("song/medium_song.wav"); 
	    hardSong = new MySoundEffect("song/hard_song.wav");
	    nightmareSong = new MySoundEffect("song/nightmare_song.wav"); 
	    bossSong = new MySoundEffect("song/boss_song.wav"); 

        menuSong.playLoop();

        // main menu button
        JButton button1 = new JButton("Start");
        JButton button2 = new JButton("Credit");
        JButton button3 = new JButton("Tutorial");
        JButton button4 = new JButton("Exit");
        button1.setIcon(startButton);
        button2.setIcon(creditButton);
        button3.setIcon(tutorialButton);
        button4.setIcon(exitButton);
        button1.setBounds((frameWidth / 2) - 50, (frameHeight / 2) - 100, 138,50 );
        button2.setBounds((frameWidth / 2) - 50, (frameHeight / 2) - 33, 138,50);
        button3.setBounds((frameWidth / 2) - 50, (frameHeight / 2) + 33, 138,50);
        button4.setBounds((frameWidth / 2) - 50, (frameHeight / 2) + 100, 138,50);
        drawpane.add(button1);
        drawpane.add(button2);
        drawpane.add(button3);
        drawpane.add(button4);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonSound.playOnce();
                button1.setVisible(false);
                button2.setVisible(false);
                button3.setVisible(false);
                button4.setVisible(false);
                drawpane.setIcon(bgImg2);
                mode_panel();
            }
        });

        button3.addActionListener(new ActionListener() { // Tutorial button3
            public void actionPerformed(ActionEvent e) {
                buttonSound.playOnce();
                if (Tframe == null) {
                    Tframe = new Tutorial();

                } else {
                    Tframe.setVisible(true);
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonSound.playOnce();
                System.exit(0);
            }
        });

        validate();
    }//end AddComponent

    public void mode_panel() {
        // mode button
        String[] mode = { "--- Please select difficulty ---", "Beginner", "Easy", "Normal", "Hard", "Nightmare" };
        combo = new JComboBox(mode);
        combo.setBounds(frameWidth / 4, frameHeight / 4, 200, 50);

        // Play button
        JButton play = new JButton("Play!!");
        play.setBounds(frameWidth / 4, frameHeight / 2, 200, 50);
        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonSound.playOnce();
                if ((String) combo.getSelectedItem() != "--- Please select difficulty ---") {
                    combo.setVisible(false);
                    play.setVisible(false);
                    main_game((String) combo.getSelectedItem());
                }
            }
        });
        drawpane.add(combo);
        drawpane.add(play);
    }//end mode Panel

    public void main_game(String mode) {
        menuSong.stop();
        switch (mode) {

        case "Beginner":
            drawpane.setIcon(in_gamebg1Img);
            drawpane.setLayout(null);
            drawpane.add(pikaReadyLabel);
            contentpane.add(drawpane, BorderLayout.CENTER);   
            repaint();   
            beginnerSong.playLoop();
            break;
        case "Easy":
            drawpane.setIcon(in_gamebg2Img);
            drawpane.setLayout(null);
            contentpane.add(drawpane, BorderLayout.CENTER);
            break;
        case "Normal":
            drawpane.setIcon(in_gamebg3Img);
            drawpane.setLayout(null);
            contentpane.add(drawpane, BorderLayout.CENTER);
            break;
        case "Hard":
            drawpane.setIcon(in_gamebg4Img);
            drawpane.setLayout(null);
            contentpane.add(drawpane, BorderLayout.CENTER);
            break;
        case "Nightmare":
            drawpane.setIcon(in_gamebg5Img);
            drawpane.setLayout(null);
            contentpane.add(drawpane, BorderLayout.CENTER);

            break;

        }
    }

    //----------------------------------- Read File ----------------------------------
    public void readFile(String[] mode) {
        for (int i = 0; i < mode.length; i++) {
            enforceFile(mode[i]);
        }
        // System.out.printf("---------------\n");
        // printReadFile();
    }

    public void enforceFile(String fname) {
        String fileName = fname;
        int countLine = 0;
        boolean opensuccess = false;
        ArrayList<String> vList = new ArrayList<String>();
        String name = "";

        while (!opensuccess) {
            try (Scanner filescan = new Scanner(new File(fileName));) {
                opensuccess = true;
                while (filescan.hasNext()) {
                    String line = filescan.nextLine();
                    String[] buf = line.split(",");
                    if (countLine == 0) {
                        name = buf[0].trim();
                        countLine++;
                    } else {
                        vList.add(buf[0].trim());
                    }
                }
                Mode m = new Mode(name, vList);
                modeList.add(m);
            }

            catch (FileNotFoundException e) {
                System.out.println(e);
            }
        } // end while
    }

    public void printReadFile() { // print read file
        for (int i = 0; i < modeList.size(); i++) {
            modeList.get(i).printFileWord();
        }
        System.out.println("");
    }
}//end Class MainApplication

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

class Mode {
    private String mode;
    private int sizeList;
    private ArrayList<String> vocabList = new ArrayList<String>();
    private Random random = new Random();
  
    public Mode(){}
    public Mode(String m, ArrayList<String> li){
      mode = m;
      vocabList = li;
      sizeList = vocabList.size();
    }
    public String getMode(){
      return mode;
    }
    public String randomWord(){ //Random word
      int ran = random.nextInt(sizeList);
      return vocabList.get(ran);
    }
    public void printFileWord(){ //check Reading File
      System.out.printf("====== Mode %-9s reading... =====\n",mode);
      for(int i = 0 ;i<vocabList.size();i++){
        System.out.printf("\t [%8s] %-15s \n",mode,vocabList.get(i));
      }
      System.out.println("");
    }
  }

class MySoundEffect
{
    private Clip clip;

    public MySoundEffect(String filename)
    {
	try
	{
            java.io.File file = new java.io.File(filename);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
	}
	catch (Exception e) { e.printStackTrace(); }
    }
    public void playOnce()   { clip.setMicrosecondPosition(0); clip.start(); }
    public void playLoop()   { clip.loop(Clip.LOOP_CONTINUOUSLY); }
    public void stop()       { clip.stop(); }
}
