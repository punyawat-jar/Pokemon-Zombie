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
    private ButtonGroup bgroup;
    private boolean pauseGame = false;

    private MyImageIcon bgImg, bgImg2, in_gamebg1Img, in_gamebg2Img, in_gamebg3Img, in_gamebg4Img, in_gamebg5Img;
    private MySoundEffect menuSong, creditSong, beginnerSong, mediumSong, hardSong, nightmareSong, bossSong;

    private MyImageIcon startButton, creditButton, tutorialButton, exitButton, playButton;
    private MySoundEffect buttonSound, normalHitSound, softHitSound, criHitSound, hurtSound, gameOverSound, winSound, usedItemSound;

    private MyImageIcon zomb1Img, zomb2Img, zomb3Img, zomb4Img, zombBossImg;
    private JLabel zomb1Label, zomb2Label, zomb3Label, zomb4Label, zombBossLabel;
    private int zombSpeed = 250;
    ArrayList<JLabel> mobLabel = new ArrayList<JLabel>();
    ArrayList<Integer> mobCurX = new ArrayList<Integer>();
    ArrayList<Integer> mobCurY = new ArrayList<Integer>();
    ArrayList<Integer> mobWidth = new ArrayList<Integer>();
    ArrayList<Integer> mobHeight = new ArrayList<Integer>();
    ArrayList<ZombieThread> mobThread = new ArrayList<ZombieThread>();

    private JProgressBar PBar = new JProgressBar();
    private Keyboard_bar keybar;
    private int frameWidth = 1366, frameHeight = 768;
    private int itemWidth = 40, itemHeight = 50;
    private int score = 0;

    private Player player;

    Tutorial Tframe;

    //private String []mode = {"Vocab/Beginner.txt","Vocab/Easy.txt","Vocab/Medium.txt","Vocab/Hard.txt","Vocab/Nightmare.txt"};
    ArrayList<Vocab> modeList = new ArrayList<Vocab>();
    //------------------------------- Main Method -------------------------------
    public static void main(String[] args) {
        new MainApplication();
    }

    public boolean getPauseGame(){
        return pauseGame;
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
        //readFile(mode);
    }//end MainApplication Constructor;

    public void AddComponents() {
        setUpCursor(contentpane);
        
        bgImg = new MyImageIcon("bg/menu_bg.png").resize(frameWidth, frameHeight);
        bgImg2 = new MyImageIcon("bg/cleanMenu_bg.png").resize(frameWidth, frameHeight);
        in_gamebg1Img = new MyImageIcon("bg/nature-lu.png").resize(frameWidth, frameHeight);
        in_gamebg2Img = new MyImageIcon("bg/bg2.jpg").resize(frameWidth, frameHeight);
        in_gamebg3Img = new MyImageIcon("bg/night_bg2.png").resize(frameWidth, frameHeight);
        in_gamebg4Img = new MyImageIcon("bg/bg1.png").resize(frameWidth, frameHeight);
        in_gamebg5Img = new MyImageIcon("bg/night_bg1.png").resize(frameWidth, frameHeight);

        startButton = new MyImageIcon("button_and_cursor/StartButton.png").resize(138,50);
        creditButton = new MyImageIcon("button_and_cursor/CreditButton.png").resize(138,50);
        tutorialButton = new MyImageIcon("button_and_cursor/TutorialButton.png").resize(138,50);
        exitButton = new MyImageIcon("button_and_cursor/ExitButton.png").resize(138,50);
        playButton = new MyImageIcon("button_and_cursor/PlayButton.png").resize(138,50);

        drawpane = new JLabel();
        drawpane.setIcon(bgImg);
        drawpane.setLayout(null);
        contentpane.add(drawpane, BorderLayout.CENTER);

        
        //------------------------------- Zombie -----------------------------------
        for(int i=0; i<5; i++){
            switch(i){
                case 0: mobWidth.add(87); mobHeight.add(157); break;
                case 1: mobWidth.add(140); mobHeight.add(139); break;
                case 2: mobWidth.add(138); mobHeight.add(153); break;
                case 3: mobWidth.add(140); mobHeight.add(140); break;
                case 4: mobWidth.add(182); mobHeight.add(223); break;
            }
        }
        zomb1Img = new MyImageIcon("zombie/z01.png").resize(mobWidth.get(0), mobHeight.get(0));
        zomb2Img = new MyImageIcon("zombie/z02.png").resize(mobWidth.get(1), mobHeight.get(1));
        zomb3Img = new MyImageIcon("zombie/z03.png").resize(mobWidth.get(2), mobHeight.get(2));
        zomb4Img = new MyImageIcon("zombie/z04.png").resize(mobWidth.get(3), mobHeight.get(3));
        zombBossImg = new MyImageIcon("zombie/zboss.png").resize(mobWidth.get(4), mobHeight.get(4));
        zomb1Label = new JLabel(zomb1Img);
        zomb2Label = new JLabel(zomb2Img);
        zomb3Label = new JLabel(zomb3Img);
        zomb4Label = new JLabel(zomb4Img); 
        zombBossLabel = new JLabel(zombBossImg);
            
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
        setUpButton(button1,startButton);
        setUpButton(button2,creditButton);
        setUpButton(button3,tutorialButton);
        setUpButton(button4,exitButton);
        button1.setBounds((frameWidth / 2) - 50, (frameHeight / 2) - 100, 145,50 );
        button2.setBounds((frameWidth / 2) - 50, (frameHeight / 2) - 33, 145,50);
        button3.setBounds((frameWidth / 2) - 50, (frameHeight / 2) + 33, 145,50);
        button4.setBounds((frameWidth / 2) - 50, (frameHeight / 2) + 100, 145,50);
        drawpane.add(button1);
        drawpane.add(button2);
        drawpane.add(button3);
        drawpane.add(button4);

        button1.addActionListener(new ActionListener() {    // Start button1
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

        button3.addActionListener(new ActionListener() {    // Tutorial button3
            public void actionPerformed(ActionEvent e) {
                buttonSound.playOnce();
                if (Tframe == null) {
                    Tframe = new Tutorial();

                } else {
                    Tframe.setVisible(true);
                }
            }
        });

        button4.addActionListener(new ActionListener() {    // Exit button4
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
        setUpButton(play, playButton);
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
            contentpane.add(drawpane, BorderLayout.CENTER);  
            beginnerSong.playLoop();
            
            for(int i=0; i<10; i++){
                Random random = new Random();
                int zombie = random.nextInt(4);
                mobCurX.add(frameWidth);
                switch(zombie) {
                    case 1: mobLabel.add(zomb1Label); mobCurY.add(frameHeight - 275 - mobHeight.get(0)); break;
                    case 2: mobLabel.add(zomb2Label); mobCurY.add(frameHeight - 275 - mobHeight.get(1));break;
                    case 3: mobLabel.add(zomb3Label); mobCurY.add(frameHeight - 275 - mobHeight.get(2));break;
                    case 4: mobLabel.add(zomb4Label); mobCurY.add(frameHeight - 275 - mobHeight.get(3));break;
                }
            }
            for(int i=0; i<2; i++){
                mobThread.add(new ZombieThread("Zombie"+ String.valueOf(i), mobLabel.get(i), mobCurX.get(i), mobCurY.get(i), player.getLabel(), zombSpeed));
            }
            break;
        case "Easy":
            drawpane.setIcon(in_gamebg2Img);
            drawpane.setLayout(null);
            contentpane.add(drawpane, BorderLayout.CENTER);
            mediumSong.playLoop();
            break;
        case "Normal":
            drawpane.setIcon(in_gamebg3Img);
            drawpane.setLayout(null);
            contentpane.add(drawpane, BorderLayout.CENTER);
            hardSong.playLoop();
            break;
        case "Hard":
            drawpane.setIcon(in_gamebg4Img);
            drawpane.setLayout(null);
            contentpane.add(drawpane, BorderLayout.CENTER);
            nightmareSong.playLoop();
            break;
        case "Nightmare":
            drawpane.setIcon(in_gamebg5Img);
            drawpane.setLayout(null);
            contentpane.add(drawpane, BorderLayout.CENTER);
            bossSong.playLoop();
            break;
        }


        player = new Player();
        player.draw_player(drawpane);
        player.draw_healthbar(drawpane);

        PBar.setValue(0);
        PBar.setBounds(frameWidth-460,frameHeight-(50*2),420,50);
        PBar.setStringPainted(false);
        drawpane.add(PBar);

        keybar = new Keyboard_bar();
        keybar.setPane(drawpane);
        keybar.getTypearea().grabFocus();

        
    }

    //------------------------------- Set Up Zombie Thread(Must in main because have to use "Pause");
    //public void setZombieThread(String n, JLabel zl, int x, int y){
        /*JLabel zombLabel = zl;
        Thread zombThread = new Thread(n){
        public void run()
        {/*
            int zombCurX = x;
            int zombCurY = y;
            //---------------- For randoming time Zombie Appear ----------
            Random r = new Random();
            int low = 1500;
            int high = 4000;
            int timeWait = r.nextInt(high-low) + low;
	        try  { Thread.sleep(50); }  catch (InterruptedException e)  { }
            //-------------------- If zombie not hit pikachu, it walks --------------------
		    while (!(zombLabel.getBounds().intersects(player.getLabel().getBounds())))
		    {
                    zombLabel.setLocation(zombCurX, zombCurY);
                    if (!pauseGame)
                    {
                        //Move Left
			            zombCurX = zombCurX - 10;
                    }
                    else
                    {
                        System.out.println("Pause ZombieThread: " + Thread.currentThread().getName());
                    }
                    repaint(); 
                    try { Thread.sleep(zombSpeed); } 
                    catch (InterruptedException e) { e.printStackTrace(); }
		    } // end while
            } // end run
	}; // end thread creation
    zombThread.start();}*/
    
    //-------------------------------- Set up Cursor & Button ------------------------

    public void setUpButton(JButton button, MyImageIcon img){
        button.setIcon(img);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void setUpCursor(JPanel mainpane){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("button_and_cursor/normalCursor.png");
        Cursor c = toolkit.createCustomCursor(image , new Point(mainpane.getX(), mainpane.getY()), "img");
        mainpane.setCursor(c);
    }

    // Add Vocab
    

    //----------------------------------- Read File ----------------------------------
    public void readFile(String[] mode) {
        for (int i = 0; i < mode.length; i++) {
            enforceFile(mode[i]);
        }
        // System.out.printf("---------------\n");
        //printReadFile();
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
                Vocab m = new Vocab(name, vList);
                modeList.add(m);
            }

            catch (FileNotFoundException e) {
                System.out.println(e);
            }
        } // end while
    }

    public void printReadFile() { // print read file
        //for (int i = 0; i < modeList.size(); i++) {
            modeList.get(2).printFileWord();
        //}
        System.out.println("");
    }
}//end Class MainApplication

class Vocab{
    private String mode;
    private int sizeList;
    private ArrayList<String> vocabList = new ArrayList<String>();
    private Random random = new Random();
  
    public Vocab(){}
    public Vocab(String m, ArrayList<String> li){
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

class Player{
    private int HP, Score;
    private int playerwidth = 200, playerhight = 165,healthbarwidth = 180,healthbarhight = 30;
    private MyImageIcon player,healthbar_pic;
    private JLabel playerLabel,HP_Label;
    private ArrayList<JLabel> HP_AL = new ArrayList<JLabel>();
    String[] HP_bar = {"health bar/H0.png","health bar/1.png","health bar/H2.png","health bar/H3.png","health bar/H4.png","health bar/H5.png"};
    public void player() {}
    public void draw_player(JLabel x){

        player = new MyImageIcon("pokemon/pikachuready.png").resize(playerwidth, playerhight);
        playerLabel = new JLabel(player);
        playerLabel.setBounds(0,440,playerwidth,playerhight);
        x.add(playerLabel);
        x.validate();
    }

    public void draw_healthbar(JLabel x){
        for(int i=0;i<HP_bar.length;i++){
            healthbar_pic = new MyImageIcon(HP_bar[i]).resize(healthbarwidth,healthbarhight);
            HP_Label = new JLabel(healthbar_pic);
            
            HP_Label.setBounds(45,50,healthbarwidth,healthbarhight);
            //HP_Label.setBounds(45,420,healthbarwidth,healthbarhight);
            HP_AL.add(HP_Label);
        }
        HP = HP_AL.size()-1;
        x.add(HP_AL.get(5));
    }

    public void hitplayer(JLabel x){
        x.remove(HP_AL.get(HP));
        HP-=1;
        x.add(HP_AL.get(HP));
    }

    public int getHP(){
        return HP;
    }
    public void setscore(int x){
        this.Score = x;
    }
    public JLabel getLabel(){
        return playerLabel;
    }
}//end Player

class ZombieThread extends Thread{
    JLabel zombLabel = new JLabel();
    JLabel playerLabel = new JLabel(); //For Check Intersect
    int zombCurX, zombCurY;
    int zombSpeed;
    MainApplication program;

    public ZombieThread(String n, JLabel zl, int x, int y, JLabel pl, int zs){
        super(n);
        zombLabel = zl;
        zombCurX = x;
        zombCurY = y;
        playerLabel = pl;
        zombSpeed = zs;
/*
        //---------------- For randoming time Zombie Appear ----------
        Random r = new Random();
        int low = 1500;
        int high = 4000;
        int timeWait = r.nextInt(high-low) + low;
	    try  { Thread.sleep(timeWait); }  catch (InterruptedException e)  { }*/
        start();
    }
    
    public void run()
        {
            //-------------------- If zombie not hit pikachu, it walks --------------------
		   /*while (!(zombLabel.getBounds().intersects(playerLabel.getBounds())))
		    {
                    zombLabel.setLocation(zombCurX, zombCurY);
                    if (!(program.getPauseGame()))
                    {
                        //Move Left
			            zombCurX = zombCurX - 10;
                    }
                    else
                    {
                        System.out.println("Pause ZombieThread: " + Thread.currentThread().getName());
                    }
                    zombLabel.repaint(); 
                    try { Thread.sleep(zombSpeed); } 
                    catch (InterruptedException e) { e.printStackTrace(); }
		    } // end while*/
        } // end run
}


//----------------- Special Class Zone ----------------
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

class Zombie{
    private String name;
}


class Keyboard_bar{
    private JTextArea typearea;
    private int width,height;
    public Keyboard_bar(){
        typearea = new JTextArea();
        typearea.setBounds(50,100,500,30);
        typearea.setFont(new Font("SanSerif", Font.BOLD, 25));
        //typearea.grabFocus();
        typearea.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_SPACE){
                    System.out.println("Hello world");
                }
            }
            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e ) {}
        });
    }

    public JTextArea getTypearea(){
        return typearea;
    }

    public void setPane(JLabel x){
        x.add(typearea);
    }

    public void setposition(int x, int y){
        typearea.setBounds(x,y,width,height);
    }

    
}
