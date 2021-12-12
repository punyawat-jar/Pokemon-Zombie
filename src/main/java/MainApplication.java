import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.net.ssl.CertPathTrustManagerParameters;
import javax.sound.sampled.*; // for sounds
import java.util.*;
import java.util.Random;
import java.io.*;
import javax.swing.border.*;
import java.util.Timer;

//Main Frame
public class MainApplication extends JFrame {
    private static MainApplication program;// Use to call this class' function in other class
    /* For Pausing Game, use setPauseGame(true) zombieThread will pause. */
    // ------------------------------- Component -------------------------------
    private JPanel contentpane;
    private JLabel drawpane;
    private JComboBox combo;
    private JToggleButton[] tb;
    private JTextField scoreText;
    private JLabel Label, custom_bg_label;
    private ButtonGroup bgroup;
    private String modeSelected;
    private MyImageIcon readyGoImg;
    private JLabel readyGoLabel;
    private MySoundEffect readyGoSound;
    private boolean comeIn;
    private boolean gameEnd = false;
    private String gameResult;
    private int countStageEnd = 0;
    private MyImageIcon bgImg, bgImg2, in_gamebg1Img, in_gamebg2Img, in_gamebg3Img, in_gamebg4Img, in_gamebg5Img,
            custom_bg;
    private MySoundEffect menuSong, creditSong, beginnerSong, mediumSong, hardSong, nightmareSong, bossSong;

    private JRadioButton[] radio;
    private String[] accessory = { "No Item", "Conan's Bow", "Joy's Hat", "Pajamas", "Just Sunglasses" };
    private String[] message = {};
    private MyImageIcon emptyButton, startButton, creditButton, tutorialButton, exitButton, playButton,
            menuButton,
            nextButton, backButton;
    private MySoundEffect buttonSound, normalHitSound, softHitSound, criHitSound, gameOverSound, winSound,
            usedItemSound, ding;
    private MyImageIcon winGif, gameOverGif, pokeWinGif, pokeGameOverGif;
    JLabel winLabel, gameOverLabel, pokeWinLabel, pokeGameOverLabel;

    // ArrayList<Thread> mobThread = new ArrayList<Thread>();
    ArrayList<Wordbox> wbox_AL = new ArrayList<Wordbox>();
    ArrayList<JLabel> custom_poke_AL = new ArrayList<JLabel>();
    ArrayList<JLabel> custom_info_AL = new ArrayList<JLabel>();
    ArrayList<JLabel> itemdrop_AL = new ArrayList<JLabel>();
    ArrayList<Integer> threadlist = new ArrayList<Integer>();
    ArrayList<ZombieThread> zombielist = new ArrayList<ZombieThread>();
    ArrayList<JLabel> themePicLabel_AL = new ArrayList<JLabel>();

    private JProgressBar PBar = new JProgressBar();
    private Keyboard_bar keybar;
    private int frameWidth = 1366, frameHeight = 768;
    private int itemWidth = 40, itemHeight = 50;
    private int score = 0, count = 0, count_pic = 0, count_death = 0;
    private int selected_rbox = 0;
    private JButton end_btn;
    private itemdrop itemDrop;
    private boolean ismove = true;
    private Player player;
    private Wordbox wbox;
    private Bomb bomb;
    private Potion potion;
    private Speed_Stopwatch speed_stopwatch;
    private Slow_Stopwatch slow_stopwatch;

    Tutorial Tframe;
    private String[] poke_list = { "custom_poke/No.png", "custom_poke/Conan.png", "custom_poke/Joy.png",
            "custom_poke/Pajamas.png", "custom_poke/Just.png" };
    private String[] custom_info = { "item_info/info1.png", "item_info/info2.png", "item_info/info3.png",
            "item_info/info4.png", "item_info/info5.png" };
    private String[] mode = { "Vocab/Beginner.txt", "Vocab/Medium.txt", "Vocab/Hard.txt", "Vocab/Nightmare.txt",
            "Vocab/Boss.txt" };
    private String[] itemdrop_list = { "item_fall/bomb.png", "item_fall/potion.png", "item_fall/slow_stopwatch.png",
            "item_fall/speed_stopwatch.png"};

    private String[] vocabFilename_list = { "Vocab/Beginner.txt", "Vocab/Medium.txt", "Vocab/Hard.txt",
            "Vocab/Nightmare.txt",
            "Vocab/Boss.txt", "Vocab/OnlyBoss.txt" };

    ArrayList<Vocab> vocabList = new ArrayList<Vocab>();

    // ------------------------------- Main Method -------------------------------
    public static void main(String[] args) {
        program = new MainApplication();
    }

    public int get_framewidth() {
        return frameWidth;
    }

    public int get_frameheight() {
        return frameHeight;
    }

    public int getCountStageEnd() {
        return countStageEnd;
    }

    public void addCountStageEnd() {
        countStageEnd++;
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

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int exit = JOptionPane.showConfirmDialog(new JFrame(), "Do you want to quit the game?",
                        "PokemonGame! - Message", JOptionPane.YES_NO_OPTION);
                if (exit == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }

        });

        // add Vocab from file to variable
        readFile(vocabFilename_list);
    }// end MainApplication Constructor;

    public void AddComponents() {
        setUpCursor(contentpane);

        bgImg = new MyImageIcon("bg/menu_bg.png").resize(frameWidth, frameHeight);
        bgImg2 = new MyImageIcon("bg/cleanMenu_bg.png").resize(frameWidth, frameHeight);
        in_gamebg1Img = new MyImageIcon("bg/beginner_bg.png").resize(frameWidth, frameHeight);
        in_gamebg2Img = new MyImageIcon("bg/medium_bg.png").resize(frameWidth, frameHeight);
        in_gamebg3Img = new MyImageIcon("bg/hard_bg.png").resize(frameWidth, frameHeight);
        in_gamebg4Img = new MyImageIcon("bg/nightmare_bg.png").resize(frameWidth, frameHeight);
        in_gamebg5Img = new MyImageIcon("bg/boss_bg.png").resize(frameWidth, frameHeight);

        emptyButton = new MyImageIcon("button_and_cursor/button.png").resize(138, 50);
        startButton = new MyImageIcon("button_and_cursor/StartButton.png").resize(138, 50);
        creditButton = new MyImageIcon("button_and_cursor/CreditButton.png").resize(138, 50);
        tutorialButton = new MyImageIcon("button_and_cursor/TutorialButton.png").resize(138, 50);
        exitButton = new MyImageIcon("button_and_cursor/ExitButton.png").resize(138, 50);
        playButton = new MyImageIcon("button_and_cursor/PlayButton.png").resize(138, 50);
        menuButton = new MyImageIcon("button_and_cursor/MenuButton.png").resize(138, 50);
        nextButton = new MyImageIcon("button_and_cursor/nextButton.png").resize(138, 50);
        backButton = new MyImageIcon("button_and_cursor/PreviousButton.png").resize(138, 50);

        drawpane = new JLabel();

        readyGoSound = new MySoundEffect("sound_effect/321GoCountdown.wav");
        readyGoImg = new MyImageIcon("sound_effect/321_Go.gif");
        readyGoLabel = new JLabel(readyGoImg);

        winGif = new MyImageIcon("gameOver/win.gif");
        gameOverGif = new MyImageIcon("gameOver/game_over.gif");
        pokeWinGif = new MyImageIcon("gameOver/pokeWin.gif");
        pokeGameOverGif = new MyImageIcon("gameOver/pokeGameOver.gif");
        winLabel = new JLabel(winGif);
        gameOverLabel = new JLabel(gameOverGif);
        pokeWinLabel = new JLabel(pokeWinGif);
        pokeGameOverLabel = new JLabel(pokeGameOverGif);

        // ------------------------------- Zombie -----------------------------------

        // ---------------------------- Sound --------------------------------------
        buttonSound = new MySoundEffect("sound_effect/button_soundeffect.wav");
        normalHitSound = new MySoundEffect("sound_effect/NormalHit_soundeffect.wav");
        softHitSound = new MySoundEffect("sound_effect/SoftHit_soundeffect.wav");
        criHitSound = new MySoundEffect("sound_effect/CriticalHit_soundeffect.wav");
        gameOverSound = new MySoundEffect("sound_effect/GameOver_soundeffect.wav");
        winSound = new MySoundEffect("sound_effect/Win_soundeffect.wav");
        usedItemSound = new MySoundEffect("sound_effect/UsedItem_soundeffect.wav");
        ding = new MySoundEffect("sound_effect/ding.wav");
        menuSong = new MySoundEffect("song/menu_song.wav");
        creditSong = new MySoundEffect("song/credit_song.wav");
        beginnerSong = new MySoundEffect("song/beginner_and_tutorial_song.wav");
        mediumSong = new MySoundEffect("song/medium_song.wav");
        hardSong = new MySoundEffect("song/hard_song.wav");
        nightmareSong = new MySoundEffect("song/nightmare_song.wav");
        bossSong = new MySoundEffect("song/boss_song.wav");

        // menuSong.playLoop();

        mainmanu();
        validate();

    }// end AddComponent

    public void mainmanu() {
        menuSong.playLoop();

        // main menu button
        drawpane.setIcon(bgImg);
        drawpane.setLayout(null);
        contentpane.add(drawpane, BorderLayout.CENTER);

        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        JButton button4 = new JButton();
        setUpButton(button1, startButton);
        setUpButton(button2, creditButton);
        setUpButton(button3, tutorialButton);
        setUpButton(button4, exitButton);
        button1.setBounds((frameWidth / 2) - 50, (frameHeight / 2) - 100, 145, 50);
        button2.setBounds((frameWidth / 2) - 50, (frameHeight / 2) - 33, 145, 50);
        button3.setBounds((frameWidth / 2) - 50, (frameHeight / 2) + 33, 145, 50);
        button4.setBounds((frameWidth / 2) - 50, (frameHeight / 2) + 100, 145, 50);
        drawpane.add(button1);
        drawpane.add(button2);
        drawpane.add(button3);
        drawpane.add(button4);

        button1.addActionListener(new ActionListener() { // Start button1
            public void actionPerformed(ActionEvent e) {
                buttonSound.playOnce();
                button1.setVisible(false);
                button2.setVisible(false);
                button3.setVisible(false);
                button4.setVisible(false);
                drawpane.setIcon(bgImg2);
                // mode_panel();
                custom();
            }
        });

        button2.addActionListener(new ActionListener() { // credit button3
            public void actionPerformed(ActionEvent e) {
                buttonSound.playOnce();
                button1.setVisible(false);
                button2.setVisible(false);
                button3.setVisible(false);
                button4.setVisible(false);
                drawpane.setIcon(bgImg2);
                credit();
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

        button4.addActionListener(new ActionListener() { // Exit button4
            public void actionPerformed(ActionEvent e) {
                buttonSound.playOnce();
                // System.exit(0);
                int exit = JOptionPane.showConfirmDialog(new JFrame(), "Do you want to quit the game?",
                        "PokemonGame! - Message", JOptionPane.YES_NO_OPTION);
                if (exit == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        validate();
    }

    public void credit() {
        MyImageIcon creditImg = new MyImageIcon("bg/Credit.gif");
        MyImageIcon idImg = new MyImageIcon("credit/id.png").resize(403, 55);
        JLabel creditLabel = new JLabel(creditImg);
        JLabel idLabel = new JLabel(idImg);
        idLabel.setBounds(frameWidth / 2 + 40, 600, 403, 55);
        creditLabel.setBounds(280, 120, 850, 480);

        JButton backbtn = new JButton();
        backbtn.setBounds(frameWidth / 4 - 80, frameHeight - 160 / 1, 200, 50);
        backbtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                buttonSound.playOnce();
                backbtn.setVisible(false);
                idLabel.setVisible(false);
                creditLabel.setVisible(false);
                repaint();
                mainmanu();

            }
        });
        setUpButton(backbtn, backButton);
        drawpane.add(backbtn);
        drawpane.add(idLabel);
        drawpane.add(creditLabel);
        validate();
        repaint();
    }

    public void custom() {
        JButton nextbtn = new JButton();
        JButton backbtn = new JButton();
        JLabel rlabel = new JLabel();

        rlabel.setLayout(new FlowLayout());
        rlabel.setBounds(frameWidth - 950, frameHeight / 2, 500, 35);
        rlabel.setOpaque(true);
        rlabel.setBackground(Color.lightGray);

        radio = new JRadioButton[5];
        ButtonGroup rgroup = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            radio[i] = new JRadioButton(accessory[i]);
            if (i == count_pic) {
                radio[i].setSelected(true);
            }
            rgroup.add(radio[i]);
            rlabel.add(radio[i]);

            radio[i].addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    JRadioButton x = (JRadioButton) e.getItem();
                    if (x.isSelected()) {
                        for (int i = 0; i < accessory.length; i++) {
                            System.out.println(poke_list[i] + " " + x.getText());
                            if (accessory[i].toLowerCase().contains(x.getText().toLowerCase())) {
                                drawpane.remove(custom_poke_AL.get(count_pic));
                                drawpane.remove(custom_info_AL.get(count_pic));
                                count_pic = i;
                                drawpane.add(custom_poke_AL.get(count_pic));
                                drawpane.add(custom_info_AL.get(count_pic));

                                repaint();
                                validate();
                            }
                        }
                    }
                }
            });

        }

        read_picture();

        // (custom_poke_AL)
        nextbtn.setBounds(frameWidth / 2, frameHeight - 300 / 1, 200, 50);
        backbtn.setBounds(frameWidth / 4, frameHeight - 300 / 1, 200, 50);
        backbtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                buttonSound.playOnce();
                nextbtn.setVisible(false);
                backbtn.setVisible(false);

                drawpane.remove(custom_poke_AL.get(count_pic));
                drawpane.remove(custom_info_AL.get(count_pic));
                rlabel.setVisible(false);
                repaint();
                mainmanu();

            }
        });
        nextbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonSound.playOnce();
                nextbtn.setVisible(false);
                backbtn.setVisible(false);
                drawpane.remove(custom_poke_AL.get(count_pic));
                drawpane.remove(custom_info_AL.get(count_pic));
                rlabel.setVisible(false);
                repaint();
                mode_panel();
            }
        });
        drawpane.add(nextbtn);
        drawpane.add(backbtn);
        drawpane.add(custom_poke_AL.get(count_pic));
        drawpane.add(custom_info_AL.get(count_pic));
        drawpane.add(rlabel);
        setUpButton(backbtn, backButton);
        setUpButton(nextbtn, nextButton);
        validate();
        repaint();
    }

    // public void radiobox_message(int i){
    // JLabel messagebox = new JLabel();
    // messagebox.setIcon(new MyImageIcon("Wbox/wbox.png").resize(120, 40));
    // messagebox.setBounds(100,200,120,40);
    // messagebox.setVisible(true);
    // drawpane.add(messagebox);
    // }

    public void showThemeMode() {
        int themeWidth = frameWidth / 3, themeHeight = frameHeight / 3;
        JLabel tempLabel = new JLabel();
        String []bg = {"bg_border/bg_beginner.png","bg_border/bg_medium.png","bg_border/bg_hard.png","bg_border/bg_nightmare.png","bg_border/bg_boss.png"};
        for(int i=0 ;i<bg.length;i++){
            tempLabel = new JLabel(new MyImageIcon(bg[i]).resize(themeWidth, themeHeight));
            tempLabel.setBounds(frameWidth-themeWidth-255, (frameHeight/2)-(themeHeight/2)-45,themeWidth, themeHeight);
           themePicLabel_AL.add(tempLabel);
           themePicLabel_AL.get(i).setVisible(false);
            drawpane.add(tempLabel);
        }
    }// end showThemeMode

    public void mode_panel() {
        // mode button
        String[] mode = { "--- Please select difficulty ---", "Beginner", "Medium", "Hard", "Nightmare", "Boss" };
        combo = new JComboBox(mode);
        combo.setBounds(frameWidth / 4, (frameHeight / 4)+115, 200, 50);

        // Play button
        JButton play = new JButton();
        JButton backbtn = new JButton();
        setUpButton(play, playButton);
        setUpButton(backbtn, backButton);
        play.setBounds((frameWidth / 2), (frameHeight / 2)+130, 500, 50);
        backbtn.setBounds((frameWidth / 2)-240, (frameHeight / 2)+130, 200, 50);

        showThemeMode();

        combo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modeSelected = (String) combo.getSelectedItem();
                if (modeSelected == "Beginner") {
                    set_example_pane(0);
                    // themePicLabel_AL.get(0).setVisible(true);
                    // themePicLabel_AL.get(1).setVisible(false);
                    // themePicLabel_AL.get(2).setVisible(false);
                    // themePicLabel_AL.get(3).setVisible(false);
                    // themePicLabel_AL.get(4).setVisible(false);
                    System.out.printf("----- %s show -----\n", modeSelected);
                }
                if (modeSelected == "Medium") {
                    set_example_pane(1);
                    // themePicLabel_AL.get(1).setVisible(true);
                    // themePicLabel_AL.get(0).setVisible(false);
                    // themePicLabel_AL.get(2).setVisible(false);
                    // themePicLabel_AL.get(3).setVisible(false);
                    // themePicLabel_AL.get(4).setVisible(false);
                    System.out.printf("----- %s show -----\n", modeSelected);
                }
                if (modeSelected == "Hard") {
                    set_example_pane(2);
                    // themePicLabel_AL.get(2).setVisible(true);
                    // themePicLabel_AL.get(0).setVisible(false);
                    // themePicLabel_AL.get(1).setVisible(false);
                    // themePicLabel_AL.get(3).setVisible(false);
                    // themePicLabel_AL.get(4).setVisible(false);
                    System.out.printf("----- %s show -----\n", modeSelected);
                }
                if (modeSelected == "Nightmare") {
                    set_example_pane(3);
                    // themePicLabel_AL.get(3).setVisible(true);
                    // themePicLabel_AL.get(0).setVisible(false);
                    // themePicLabel_AL.get(1).setVisible(false);
                    // themePicLabel_AL.get(2).setVisible(false);
                    // themePicLabel_AL.get(4).setVisible(false);
                    System.out.printf("----- %s show -----\n", modeSelected);
                }
                if (modeSelected == "Boss") {
                    set_example_pane(4);
                    // themePicLabel_AL.get(4).setVisible(true);
                    // themePicLabel_AL.get(0).setVisible(false);
                    // themePicLabel_AL.get(1).setVisible(false);
                    // themePicLabel_AL.get(2).setVisible(false);
                    // themePicLabel_AL.get(3).setVisible(false);
                    System.out.printf("----- %s show -----\n", modeSelected);
                }
                repaint();
                validate();
            }
        });
        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonSound.playOnce();
                modeSelected = (String) combo.getSelectedItem();
                if (modeSelected != "--- Please select difficulty ---") {
                    combo.setVisible(false);
                    play.setVisible(false);
                    backbtn.setVisible(false);
                    for (int i = 0; i < 5; i++) {
                        themePicLabel_AL.get(i).setVisible(false);
                        drawpane.remove(themePicLabel_AL.get(i));
                    }
                    drawpane.repaint();
                    main_game(modeSelected);
                }
            }
        });

        backbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonSound.playOnce();
                combo.setVisible(false);
                play.setVisible(false);
                backbtn.setVisible(false);
                for (int i = 0; i < 5; i++) {
                    themePicLabel_AL.get(i).setVisible(false);
                    drawpane.remove(themePicLabel_AL.get(i));
                }
                drawpane.repaint();
                custom();
            }
        });

        drawpane.add(combo);
        drawpane.add(play);
        drawpane.add(backbtn);
    }// end mode Panel

    public void set_example_pane(int i) {
        themePicLabel_AL.get(i).setVisible(true);
        for (int j = 0; j < 4; j++) {
            if (j == i) {
                continue;
            } else {
                themePicLabel_AL.get(j).setVisible(false);
            }
        }
    }

    public void showReadyGo() {

        Thread animation = new Thread() {
            public void run() {
                JLabel tempLabel;
                readyGoSound.playOnce();
                for (int i = 1; i <= 146; i++) {
                    try {
                        // System.out.println("i is =======================" + i);
                        tempLabel = new JLabel(new MyImageIcon("readyGo/readyGO (" + i + ").png").resize(380, 210));
                        tempLabel.setBounds(525, 230, 380, 210);
                        tempLabel.setOpaque(false);
                        tempLabel.setLayout(null);
                        tempLabel.setHorizontalTextPosition(JLabel.CENTER);
                        tempLabel.setVisible(true);
                        drawpane.add(tempLabel);

                        validate();
                        repaint();
                        Thread.sleep(25);
                        drawpane.remove(tempLabel);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        };
        animation.start();
    }

    public void main_game(String mode) {
        menuSong.stop();
        gameResult = "";
        countStageEnd = 0;
        showReadyGo();
        // comeIn = false;
        // if (comeIn == false) {
        // comeIn = true;
        // readyGoLabel.setBounds(525, 230, 380, 214);
        // drawpane.add(readyGoLabel);
        // drawpane.validate();
        // readyGoSound.playOnce();
        // }
        itemDrop = new itemdrop(drawpane, this, itemdrop_AL);
        itemDrop.start();

        wbox_AL.clear();

        PBar.setValue(0);
        PBar.setBounds(frameWidth - 460, frameHeight - (50 * 2), 420, 50);
        PBar.setForeground(new Color(255, 199, 56));
        PBar.setStringPainted(false);
        drawpane.add(PBar);

        keybar = new Keyboard_bar(wbox_AL, program);
        keybar.setPane(drawpane, this);
        keybar.getTypearea().grabFocus();

        end_btn = new JButton();
        setUpButton(end_btn, menuButton);
        end_btn.setBounds((frameWidth / 2) - 700, (frameHeight / 2) + 290, 200, 50);

        end_btn.addActionListener(new ActionListener() { // Back to Menu
            public void actionPerformed(ActionEvent e) {
                buttonSound.playOnce();
                // System.exit(0);
                int exit = JOptionPane.showConfirmDialog(new JFrame(), "Do you want to quit the game?",
                        "PokemonGame! - Message", JOptionPane.YES_NO_OPTION);
                if (exit == JOptionPane.YES_OPTION) {
                    gameEnd = false;
                    for (int i = 0; i < zombielist.size(); i++) {
                        zombielist.get(i).stop();
                    }
                    itemDrop.setEndgame(true);
                    beginnerSong.stop();
                    mediumSong.stop();
                    hardSong.stop();
                    nightmareSong.stop();
                    bossSong.stop();
                    comeIn = false;
                    gameEnd = true;
                    count_death = 0;

                    resetPBar();

                    program.dispose();
                    program = new MainApplication();
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
        drawpane.add(end_btn);
        bomb = new Bomb(program, drawpane, zombielist);
        slow_stopwatch = new Slow_Stopwatch(program, drawpane, zombielist);
        speed_stopwatch = new Speed_Stopwatch(program, drawpane, zombielist);

        switch (mode) {
            case "Beginner":
                drawpane.setIcon(in_gamebg1Img);
                drawpane.setLayout(null);
                contentpane.add(drawpane, BorderLayout.CENTER);
                beginnerSong.playLoop();
                player = new Player(drawpane, count_pic, 0);
                input_word(0);
                createZombieThread(mode);
                break;
            case "Medium":
                drawpane.setIcon(in_gamebg2Img);
                drawpane.setLayout(null);
                contentpane.add(drawpane, BorderLayout.CENTER);
                mediumSong.playLoop();
                player = new Player(drawpane, count_pic, 1);
                input_word(1);
                createZombieThread(mode);
                break;
            case "Hard":
                drawpane.setIcon(in_gamebg3Img);
                drawpane.setLayout(null);
                contentpane.add(drawpane, BorderLayout.CENTER);
                hardSong.playLoop();
                player = new Player(drawpane, count_pic, 2);
                input_word(2);
                createZombieThread(mode);
                break;
            case "Nightmare":
                drawpane.setIcon(in_gamebg4Img);
                drawpane.setLayout(null);
                contentpane.add(drawpane, BorderLayout.CENTER);
                nightmareSong.playLoop();
                player = new Player(drawpane, count_pic, 3);
                input_word(3);
                createZombieThread(mode);
                break;
            case "Boss":
                drawpane.setIcon(in_gamebg5Img);
                drawpane.setLayout(null);
                contentpane.add(drawpane, BorderLayout.CENTER);
                bossSong.playLoop();
                player = new Player(drawpane, count_pic, 4);
                input_word(4);
                // createZombieThread("Nightmare");
                createZombieThread(mode);

                break;
        }
        potion = new Potion(program, drawpane, player);

        // player = new Player();
        // player.draw_player(drawpane);
        // player.draw_healthbar(drawpane);

        setup_thread_list();

    }

    public void createZombieThread(String mode) {
        // ArrayList<ZombieThread> allZombThread = new ArrayList<ZombieThread>();
        for (int i = 0; i < 10; i++) {

            ZombieThread zombThread = new ZombieThread("Zombie" + i, player, drawpane, modeSelected, i, count, PBar,
                    program, wbox_AL, keybar);
            zombielist.add(zombThread);

            System.out.println("i main = " + i);
        }
        // allZombThread.add(zombThread);
        // }
        // for (int i = 0; i < 10; i++) {
        // try {
        // allZombThread.get(i).join();
        // } catch (InterruptedException e) {
        // System.out.println("Join " + i + " Interrupted: " + e);
        // }
        // }
        // System.out.println("Join Complete");

    }

    // public void pause() {
    // for (int i = 0; i < zombielist.size(); i++) {
    // if (zombielist.get(i).getPauseGame() == false) {
    // zombielist.get(i).setPauseGame(true);
    // } else if (zombielist.get(i).getPauseGame() == true) {
    // zombielist.get(i).setPauseGame(false);
    // }
    // }
    // }

    public void kill_zombie(int i) {
        // drawpane.remove(mobLabel.get(i));
        // for (int j = 0; j < zombielist.size(); j++) {
        zombielist.get(i).kill_monster(i);
        // }
        // wbox_AL.get(i).setvisible(false);
        // drawpane.remove(wbox_AL.get(i));
        // drawpane.repaint();
        setCount_death();
    }

    // class Reminder{
    // Timer timer;
    // String mode;

    // public Reminder(int seconds, String m){
    // mode = m;
    // timer = new Timer();
    // timer.schedule(new RemindTask(), seconds*1000);
    // }

    // class RemindTask extends TimerTask {
    // public void run() {
    // System.out.println("Time's up!");
    // timer.cancel(); //Terminate the timer thread
    // for(int j=0; j<zombielist.size(); j++)
    // {
    // if(Thread.currentThread().getState()!=Thread.State.TERMINATED){
    // if(mode == "slow") zombielist.get(j).speedUpToNormal();
    // else if(mode =="speed") zombielist.get(j).slowDownToNormal();
    // else System.out.println("Can't find mode in Reminder");

    // }
    // }
    // }
    // }
    // }//end Class Reminder

    public void slowSpeed(){
    for(int i=0; i<zombielist.size(); i++){
        zombielist.get(i).slowDown();
    }
    //new Reminder(5,"slow");
    }

    public void fastSpeed(){
        for(int i=0; i<zombielist.size(); i++){
        zombielist.get(i).speedUp();
    }
    //new Reminder(6,"speed");
    }

    // public void joinThread(int n) {
    // for (int i = 0; i < n; i++) {
    // try {
    // mobThread.get(i).join();
    // } catch (InterruptedException e) {
    // }
    // }
    // }

    public void setup_thread_list() {
        threadlist.add(0);
        for (int i = 9; i > 0; i--) {
            threadlist.add(i);
        }
    }

    public void print_list_thread() {
        for (int i = 0; i < threadlist.size(); i++) {
            System.out.println("->>>>>>>>" + threadlist.get(i));
        }
    }

    // wbox_AL.get(i).wbox_move(mobCurX.get(i),mobCurY.get(i)-50);

    public void setPBar() {
        if (count < 10) {
            count += 1;
            PBar.setValue(count * 10);
            System.out.println("Add Count + = 1");
        }

    }

    public int getCount() {
        return count;
    }

    public void setCount_death() {
        count_death += 1;
    }

    public int getCount_death() {
        return count_death;
    }

    public void resetPBar() {
        count = 0;
        PBar.setValue(0);
        System.out.println("-- Reset Progress Bar --");
    }

    public void setGameResult(String result) {
        gameResult = result;
    }

    public String getGameResult() {
        return gameResult;
    }

    public Player getPlayer() {
        return player;
    }

    // public void kill_monster(int i) {
    // drawpane.remove(mobLabel.get(i));
    // wbox_AL.get(i).setvisible(false);
    // // drawpane.remove(wbox_AL.get(i));
    // drawpane.repaint();
    // setCount_death();

    // }

    // ---------------------------- Game Over ------------------------
    public void stageEnd(String mode) {
        winLabel.setBounds((frameWidth / 2) - 280, 130, 620, 200);
        gameOverLabel.setBounds((frameWidth / 2) - 400, 130, 800, 200);
        pokeWinLabel.setBounds((frameWidth / 2) - 640, (frameHeight / 2) - 400, 1281, 720);
        drawpane.remove(end_btn);
        pokeGameOverLabel.setBounds((frameWidth / 2) - 640, (frameHeight / 2) - 400, 1281, 720);

        // Back To Menu
        JButton button1 = new JButton();
        setUpButton(button1, menuButton);
        button1.setBounds((frameWidth / 2) - 100, (frameHeight / 2) + 30, 200, 50);

        // Show Score
        JTextField scoreText = new JTextField("  SCORE : " + player.getScore(), 10);
        scoreText.setEditable(false);
        scoreText.setFont(new Font("Comic Sans Ms", Font.BOLD + Font.ITALIC, 25));
        scoreText.setBackground(new Color(255, 255, 255, 100));

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BorderLayout());
        scorePanel.setBounds((frameWidth / 2) - 100, 350, 200, 30);
        scorePanel.add(scoreText, BorderLayout.CENTER);

        // ----------------Stop All sound in game-----------------------------
        beginnerSong.stop();
        mediumSong.stop();
        hardSong.stop();
        nightmareSong.stop();
        bossSong.stop();
        itemDrop.setEndgame(true);
        comeIn = false;
        gameEnd = true;
        count_death = 0;
        drawpane.remove(keybar.getTypearea());
        resetPBar();

        if (player.getHP() > 0 && gameEnd == true && gameResult == "Win") { // Win
            System.out.printf("--------------- WIN [score = %2d]---------------\n\n", player.getScore());
            drawpane.add(pokeWinLabel);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            drawpane.remove(pokeWinLabel);
            validate();
            repaint();

            drawpane.add(winLabel);
            winSound.playOnce();
            gameEnd = false;
            gameResult = "";
        } else if (player.getHP() == 0 && gameEnd == true && gameResult == "GameOver") { // gameOver
            System.out.printf("--------------- GAME OVER [score = %2d]---------------\n\n", player.getScore());
            drawpane.add(pokeGameOverLabel);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            drawpane.remove(pokeGameOverLabel);
            validate();
            repaint();

            drawpane.add(gameOverLabel);
            gameOverSound.playOnce();
            gameEnd = false;
            gameResult = "";
        }

        button1.addActionListener(new ActionListener() { // Back to Menu
            public void actionPerformed(ActionEvent e) {
                gameEnd = false;
                // for(int i=0;i<zombielist.size();i++){
                // zombielist.get(i).interrupt();
                // zombielist.get(i).setkill_thread(true);
                // }
                buttonSound.playOnce();
                winSound.stop();
                gameOverSound.stop();
                drawpane.removeAll();
                program.dispose();
                program = new MainApplication();

            }
        });

        drawpane.add(scorePanel);
        drawpane.add(button1);
        validate();
        repaint();
    }// end stageEnd

    // ---------------------------- Set up Cursor & Button ------------------------
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

    // Add Vocab

    // ---------------------------- Read File -----------------------

    public void read_picture() {

        for (int i = 0; i < poke_list.length; i++) {
            JLabel label = new JLabel(new MyImageIcon(poke_list[i]));
            JLabel info = new JLabel(new MyImageIcon(custom_info[i]));
            label.setOpaque(false);
            label.setLayout(null);
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setBounds(frameWidth - 850, 100, 320, 267);
            custom_poke_AL.add(label);
            info.setOpaque(false);
            info.setLayout(null);
            info.setHorizontalTextPosition(JLabel.CENTER);
            info.setBounds(frameWidth - 350, 100, 267, 320);
            custom_info_AL.add(info);
            // poke_list_AL.add(poke_list[i]);
        }

        for (int i = 0; i < itemdrop_list.length; i++) {
            JLabel item_label = new JLabel(new MyImageIcon(itemdrop_list[i]).resize(100, 100));
            item_label.setOpaque(false);
            item_label.setLayout(null);
            item_label.setHorizontalTextPosition(JLabel.CENTER);
            item_label.setSize(200, 200);
            item_label.addMouseListener(new MouseAdapter() {
            });
            switch (i) {
                case 0:
                    item_label.addMouseListener(new MouseAdapter() {
                        public void mouseEntered(MouseEvent e) {
                            System.out.println("Hello world1");
                            item_label.setVisible(false);
                            ding.playOnce();

                        }
                    });
                    break;
                case 1:
                    item_label.addMouseListener(new MouseAdapter() {
                        public void mouseEntered(MouseEvent e) {
                            System.out.println("Hello world2");
                            item_label.setVisible(false);
                            ding.playOnce();
                        }
                    });
                    break;
                case 2:
                    item_label.addMouseListener(new MouseAdapter() {
                        public void mouseEntered(MouseEvent e) {
                            System.out.println("Hello world3");
                            item_label.setVisible(false);
                            ding.playOnce();

                        }
                    });
                    break;
                case 3:
                    item_label.addMouseListener(new MouseAdapter() {
                        public void mouseEntered(MouseEvent e) {
                            System.out.println("Hello world4");
                            item_label.setVisible(false);
                            ding.playOnce();
                        }
                    });
                    break;
                // case 4:
                //     item_label.addMouseListener(new MouseAdapter() {
                //         public void mouseEntered(MouseEvent e) {
                //             System.out.println("Hello world5");
                //             item_label.setVisible(false);
                //             ding.playOnce();
                //         }
                //     });
                //     break;
            }

            itemdrop_AL.add(item_label);
        }
    }

    public void readFile(String[] vocabFilename_list) {
        for (int i = 0; i < vocabFilename_list.length; i++) {
            enforceFile(vocabFilename_list[i]);
        }
        // System.out.printf("----------------------------------------------\n");
        // printReadFile();
    }

    public void enforceFile(String fname) {
        String fileName = fname;
        int countLine = 0;
        boolean opensuccess = false;
        ArrayList<String> vList = new ArrayList<String>(); // Keep words
        String name = "";

        while (!opensuccess) {
            // Add vocab from file.txt to variable vocabList
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
                Vocab modeVocab = new Vocab(name, vList);
                vocabList.add(modeVocab);
            }

            catch (FileNotFoundException e) {
                System.out.println(e);
            }
        } // end while
    }

    public void printReadFile() { // print read file
        for (int i = 0; i < vocabList.size(); i++) {
            vocabList.get(i).printFileWord();
        }
        System.out.println("");
    }

    public void input_word(int n) {
        for (int i = 0; i < 10; i++) {
            if (i == 1) {
                wbox = new Wordbox(drawpane, vocabList.get(n + 1).randomWord(), i, n);
            } else {
                wbox = new Wordbox(drawpane, vocabList.get(n).randomWord(), i, n);
            }
            wbox_AL.add(wbox);
        }
        for (int i = 0; i < wbox_AL.size(); i++) {
            System.out.println(i + " = " + wbox_AL.get(i).getWord());
        }

    }
}// end Class MainApplication

// class Vocab {
// private String mode;
// private int sizeList;
// private ArrayList<String> vocabList = new ArrayList<String>();
// private Random random = new Random();

// public Vocab() {
// }

// public Vocab(String m, ArrayList<String> li) {
// mode = m;
// vocabList = li;
// sizeList = vocabList.size();
// }

// public String getMode() {
// return mode;
// }

// public String randomWord() { // Random word
// int ran = random.nextInt(sizeList);
// return vocabList.get(ran);
// }

// public void printFileWord() { // check Reading File
// System.out.printf("====== Mode %-9s reading... =====\n", mode);
// for (int i = 0; i < vocabList.size(); i++) {
// System.out.printf("\t [%8s] %-15s \n", mode, vocabList.get(i));
// }
// System.out.println("");
// }
// }

// class Player {
// private int HP, Score;
// private int playerwidth = 200, playerhight = 165, healthbarwidth = 180,
// healthbarhight = 30;
// private MyImageIcon player, healthbar_pic;
// private JLabel playerLabel, HP_Label;
// private ArrayList<JLabel> HP_AL = new ArrayList<JLabel>();
// String[] HP_bar = { "health bar/H0.png", "health bar/1.png", "health
// bar/H2.png", "health bar/H3.png",
// "health bar/H4.png", "health bar/H5.png" };

// public Player(JLabel x) {
// player = new MyImageIcon("pokemon/pikachuready.png").resize(playerwidth,
// playerhight);
// playerLabel = new JLabel(player);
// playerLabel.setBounds(0, 440, playerwidth, playerhight);
// x.add(playerLabel);
// x.validate();

// for (int i = 0; i < HP_bar.length; i++) {
// healthbar_pic = new MyImageIcon(HP_bar[i]).resize(healthbarwidth,
// healthbarhight);
// HP_Label = new JLabel(healthbar_pic);

// HP_Label.setBounds(45, 50, healthbarwidth, healthbarhight);
// // HP_Label.setBounds(45,420,healthbarwidth,healthbarhight);
// HP_AL.add(HP_Label);
// }
// HP = HP_AL.size() - 1;
// x.add(HP_AL.get(5));
// }

// public void hitplayer(JLabel x) {
// x.remove(HP_AL.get(HP));
// HP -= 1;
// x.add(HP_AL.get(HP));
// }

// public int getHP() {
// return HP;
// }

// public void setscore(int x) {
// this.Score = x;
// }

// public JLabel getLabel() {
// return playerLabel;
// }
// }// end Player

// class ZombieThread extends Thread {
// JLabel zombLabel = new JLabel();
// JLabel playerLabel = new JLabel(); // For Check Intersect
// int zombCurX, zombCurY;
// int zombWidth, zombHeight;
// int zombSpeed;
// int low, high;
// JLabel tempPane;
// boolean pauseGame = false;
// MainApplication program;
// MySoundEffect hurtSound;
// static String mode;

// // -------------------------------- ZombieThread Constructor
// // --------------------------
// public ZombieThread(String n, JLabel zl, JLabel pl, int zs, JLabel pane,
// MySoundEffect hs, String m) {
// super(n);
// zombLabel = zl;
// zombCurX = zl.getX();
// zombCurY = zl.getY();
// zombWidth = zl.getWidth();
// zombHeight = zl.getHeight();
// playerLabel = pl;
// zombSpeed = zs;
// tempPane = pane;
// hurtSound = hs;
// mode = m;
// System.out.println("mode = " + mode);
// System.out.println("zombCur X = " + zombCurX + ", zombCurY = " + zombCurY + "
// zombWidth = " + zombWidth
// + " , zombHeight" + zombHeight);

// zombLabel.setBounds(zombCurX, zombCurY, zombWidth, zombHeight);
// tempPane.add(zombLabel);
// tempPane.validate();
// start();
// }

// public void setPauseGame(boolean b) {
// pauseGame = b;
// }

// // ------------------------- For randoming time Zombie Appear
// ----------------
// // Use static method to lock class * If lock only Obj. all other thread will
// // work and wait together.
// synchronized public static void waitGetIn() {
// if (Thread.currentThread().getName() != "Zombie0") {
// Random r = new Random();
// int low = 5000;
// int high = 15000;
// int timeWait = r.nextInt(high - low) + low;
// try {
// Thread.sleep(timeWait);
// } catch (InterruptedException e) {
// }
// }
// }

// synchronized public static void waitGetInHard() {
// if (Thread.currentThread().getName() != "Zombie0") {
// Random r = new Random();
// int low = 5000;
// int high = 10000;
// int timeWait = r.nextInt(high - low) + low;
// try {
// Thread.sleep(timeWait);
// } catch (InterruptedException e) {
// }
// }
// }

// synchronized public static void waitGetInNightmare() {
// if (Thread.currentThread().getName() != "Zombie0") {
// Random r = new Random();
// int low = 4000;
// int high = 7500;
// int timeWait = r.nextInt(high - low) + low;
// try {
// Thread.sleep(timeWait);
// } catch (InterruptedException e) {
// }
// }
// }

// // -------------------- If zombie not hit pikachu, it walks to left
// // --------------------
// public void move() {
// while (!(zombLabel.getBounds().intersects(playerLabel.getBounds()))) {

// zombLabel.setLocation(zombCurX, zombCurY);
// if (!pauseGame) {
// zombCurX = zombCurX - 5;
// zombLabel.repaint();
// } else {
// System.out.println("Pause ZombieThread: " +
// Thread.currentThread().getName());
// }
// zombLabel.repaint();
// try {
// Thread.sleep(zombSpeed);
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// System.out.println("thread = " + this.getName() + " move");
// } // end while

// }

// public void run() {
// System.out.println("thread = " + this.getName());
// if (mode == "Nightmare") {
// waitGetInNightmare();
// } else if (mode == "Hard") {
// waitGetInHard();
// } else {
// waitGetIn();
// }
// move();
// // --------- Remove Zombie when Hit Pikachu & decrease heart
// if (zombLabel.getBounds().intersects(playerLabel.getBounds())) {
// hurtSound.playOnce();
// tempPane.remove(zombLabel);
// tempPane.repaint();
// }

// } // end run
// }

// // ----------------- Special Class Zone ----------------
// class MyImageIcon extends ImageIcon {
// public MyImageIcon(String fname) {
// super(fname);
// }

// public MyImageIcon(Image image) {
// super(image);
// }

// public MyImageIcon resize(int width, int height) {
// Image oldimg = this.getImage();
// Image newimg = oldimg.getScaledInstance(width, height,
// java.awt.Image.SCALE_SMOOTH);
// return new MyImageIcon(newimg);
// }
// };

// class MySoundEffect {
// private Clip clip;

// public MySoundEffect(String filename) {
// try {
// java.io.File file = new java.io.File(filename);
// AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
// clip = AudioSystem.getClip();
// clip.open(audioStream);
// } catch (Exception e) {
// e.printStackTrace();
// }
// }

// public void playOnce() {
// clip.setMicrosecondPosition(0);
// clip.start();
// }

// public void playLoop() {
// clip.loop(Clip.LOOP_CONTINUOUSLY);
// }

// public void stop() {
// clip.stop();
// }
// }

// class Keyboard_bar {
// private JTextArea typearea;
// private int width, height;

// public Keyboard_bar() {
// typearea = new JTextArea();
// typearea.setBounds(50, 100, 500, 30);
// typearea.setFont(new Font("SanSerif", Font.BOLD, 25));
// // typearea.grabFocus();
// typearea.addKeyListener(new KeyListener() {
// public void keyPressed(KeyEvent e) {
// if (e.getKeyCode() == KeyEvent.VK_SPACE) {
// System.out.println("Hello world");
// }
// }

// public void keyTyped(KeyEvent e) {
// }

// public void keyReleased(KeyEvent e) {
// }
// });
// }

// public JTextArea getTypearea() {
// return typearea;
// }

// public void setPane(JLabel x) {
// x.add(typearea);
// }

// public void setposition(int x, int y) {
// typearea.setBounds(x, y, width, height);
// }
// }

// class Item {
// private int amount = 0;
// private int width = 100, length = 100;
// private ImageIcon img;

// }
