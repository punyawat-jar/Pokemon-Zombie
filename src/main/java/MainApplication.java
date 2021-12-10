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
    private JLabel Label;
    private ButtonGroup bgroup;
    private String modeSelected;
    private MyImageIcon readyGoImg;
    private JLabel readyGoLabel;
    private MySoundEffect readyGoSound;
    private boolean comeIn;
    private boolean gameEnd = false;
    private String gameResult;
    private int countStageEnd = 0;
    private MyImageIcon bgImg, bgImg2, in_gamebg1Img, in_gamebg2Img, in_gamebg3Img, in_gamebg4Img, in_gamebg5Img;
    private MySoundEffect menuSong, creditSong, beginnerSong, mediumSong, hardSong, nightmareSong, bossSong;

    private JRadioButton[] radio;
    private String[] accessory = { "poke1", "poke2", "poke3", "poke4", "poke5" };

    private MyImageIcon emptyButton, startButton, creditButton, tutorialButton, exitButton, playButton, restartButton,
            menuButton,
            nextButton, backButton;
    private MySoundEffect buttonSound, normalHitSound, softHitSound, criHitSound, gameOverSound, winSound,
            usedItemSound;

    // ArrayList<Thread> mobThread = new ArrayList<Thread>();
    ArrayList<Wordbox> wbox_AL = new ArrayList<Wordbox>();
    ArrayList<JLabel> custom_poke_AL = new ArrayList<JLabel>();
    ArrayList<Integer> threadlist = new ArrayList<Integer>();
    ArrayList<ZombieThread> zombielist = new ArrayList<ZombieThread>();
    private MyImageIcon winGif, gameOverGif;

    private JProgressBar PBar = new JProgressBar();
    private Keyboard_bar keybar;
    private int frameWidth = 1366, frameHeight = 768;
    private int itemWidth = 40, itemHeight = 50;
    private int score = 0, count = 0, count_pic = 0, count_death = 0;
    private long timebegin;

    private boolean ismove = true;
    private Player player;
    private Wordbox wbox;
    // private Bomb bomb;
    // private Potion potion;

    Tutorial Tframe;
    private String[] poke_list = { "custom_poke/poke1.png", "custom_poke/poke2.png", "custom_poke/poke3.png",
            "custom_poke/poke4.png", "custom_poke/poke5.png" };
    private String[] vocabFilename_list = { "Vocab/Beginner.txt", "Vocab/Medium.txt", "Vocab/Hard.txt",
            "Vocab/Nightmare.txt",
            "Vocab/Boss.txt", "Vocab/OnlyBoss.txt" };
    // private String[] mode = { "Vocab/Beginner.txt", "Vocab/Medium.txt",
    // "Vocab/Hard.txt" };
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
        restartButton = new MyImageIcon("button_and_cursor/RestartButton.png").resize(138, 50);
        menuButton = new MyImageIcon("button_and_cursor/NextButton.png").resize(138, 50);
        nextButton = new MyImageIcon("button_and_cursor/nextButton.png").resize(138, 50);
        backButton = new MyImageIcon("button_and_cursor/PreviousButton.png").resize(138, 50);
        drawpane = new JLabel();

        readyGoSound = new MySoundEffect("sound_effect/321GoCountdown.wav");
        readyGoImg = new MyImageIcon("sound_effect/321_Go.gif");
        readyGoLabel = new JLabel(readyGoImg);
        // ------------------------------- Zombie -----------------------------------

        // ---------------------------- Sound --------------------------------------
        buttonSound = new MySoundEffect("sound_effect/button_soundeffect.wav");
        normalHitSound = new MySoundEffect("sound_effect/NormalHit_soundeffect.wav");
        softHitSound = new MySoundEffect("sound_effect/SoftHit_soundeffect.wav");
        criHitSound = new MySoundEffect("sound_effect/CriticalHit_soundeffect.wav");
        gameOverSound = new MySoundEffect("sound_effect/GameOver_soundeffect.wav");
        winSound = new MySoundEffect("sound_effect/Win_soundeffect.wav");
        usedItemSound = new MySoundEffect("sound_effect/UsedItem_soundeffect.wav");

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
                System.exit(0);
            }
        });

        validate();
    }

    public void custom() {
        JButton nextbtn = new JButton();
        JButton backbtn = new JButton();
        JLabel rlabel = new JLabel();

        rlabel.setLayout(new FlowLayout());
        rlabel.setBounds(frameWidth - 900, frameHeight / 2, 400, 35);
        rlabel.setOpaque(true);
        rlabel.setBackground(Color.lightGray);

        radio = new JRadioButton[5];
        ButtonGroup rgroup = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            radio[i] = new JRadioButton(accessory[i]);
            if (i == 0) {
                radio[i].setSelected(true);
            }
            rgroup.add(radio[i]);
            rlabel.add(radio[i]);

            radio[i].addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    JRadioButton x = (JRadioButton) e.getItem();
                    if (x.isSelected()) {
                        // IntStream.range(0,poke_list_AL.size())
                        // .filter(arg->poke_list_AL.get(arg).contains(x.getText()))
                        // .forEach(arg->{
                        // drawpane.remove(custom_poke_AL.get(count));
                        // count = arg;
                        // drawpane.add(custom_poke_AL.get(arg));

                        // repaint();
                        // });

                        for (int i = 0; i < poke_list.length; i++) {
                            if (poke_list[i].contains(x.getText())) {
                                drawpane.remove(custom_poke_AL.get(count_pic));
                                count_pic = i;
                                drawpane.add(custom_poke_AL.get(count_pic));
                                repaint();
                            }
                        }
                    }
                }
            });
        }

        read_poke_custom();

        // (custom_poke_AL)
        nextbtn.setBounds(frameWidth / 2, frameHeight - 300 / 1, 200, 50);
        backbtn.setBounds(frameWidth / 4, frameHeight - 300 / 1, 200, 50);
        backbtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                buttonSound.playOnce();
                nextbtn.setVisible(false);
                backbtn.setVisible(false);

                drawpane.remove(custom_poke_AL.get(count_pic));
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
                rlabel.setVisible(false);
                repaint();
                mode_panel();
            }
        });
        drawpane.add(nextbtn);
        drawpane.add(backbtn);
        drawpane.add(custom_poke_AL.get(count_pic));
        drawpane.add(rlabel);
        setUpButton(backbtn, backButton);
        setUpButton(nextbtn, nextButton);
        validate();
        repaint();
    }

    public void mode_panel() {
        // mode button
        String[] mode = { "--- Please select difficulty ---", "Beginner", "Medium", "Hard", "Nightmare", "Boss" };
        combo = new JComboBox(mode);
        combo.setBounds(frameWidth / 4, frameHeight / 4, 200, 50);

        // Play button
        JButton play = new JButton();
        JButton backbtn = new JButton();
        setUpButton(play, playButton);
        setUpButton(backbtn, backButton);
        play.setBounds(frameWidth / 4, frameHeight / 2, 200, 50);
        backbtn.setBounds(frameWidth - 1400, frameHeight / 2, 500, 50);

        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonSound.playOnce();
                modeSelected = (String) combo.getSelectedItem();
                if (modeSelected != "--- Please select difficulty ---") {
                    combo.setVisible(false);
                    play.setVisible(false);
                    backbtn.setVisible(false);

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
                custom();
            }
        });

        drawpane.add(combo);
        drawpane.add(play);
        drawpane.add(backbtn);
    }// end mode Panel

    public void main_game(String mode) {
        menuSong.stop();
        PBar.setValue(0);
        PBar.setBounds(frameWidth - 460, frameHeight - (50 * 2), 420, 50);
        PBar.setForeground(new Color(255, 199, 56));
        PBar.setStringPainted(false);
        drawpane.add(PBar);
        gameResult = "";
        countStageEnd = 0;
        // comeIn = false;
        // if (comeIn == false) {
        // // comeIn = true;
        // readyGoLabel.setBounds(525, 230, 380, 214);
        // drawpane.add(readyGoLabel);
        // drawpane.validate();
        // readyGoSound.playOnce();
        // }
        wbox_AL.clear();

        keybar = new Keyboard_bar(wbox_AL);
        keybar.setPane(drawpane, this);
        keybar.getTypearea().grabFocus();

        switch (mode) {
            case "Beginner":
                drawpane.setIcon(in_gamebg1Img);
                drawpane.setLayout(null);
                contentpane.add(drawpane, BorderLayout.CENTER);
                beginnerSong.playLoop();
                player = new Player(drawpane);
                input_word(0);
                createZombieThread(mode);

                break;
            case "Medium":
                drawpane.setIcon(in_gamebg2Img);
                drawpane.setLayout(null);
                contentpane.add(drawpane, BorderLayout.CENTER);
                mediumSong.playLoop();
                player = new Player(drawpane);
                input_word(1);
                createZombieThread(mode);
                break;
            case "Hard":
                drawpane.setIcon(in_gamebg3Img);
                drawpane.setLayout(null);
                contentpane.add(drawpane, BorderLayout.CENTER);
                hardSong.playLoop();
                player = new Player(drawpane);
                input_word(2);
                createZombieThread(mode);
                break;
            case "Nightmare":
                drawpane.setIcon(in_gamebg4Img);
                drawpane.setLayout(null);
                contentpane.add(drawpane, BorderLayout.CENTER);
                nightmareSong.playLoop();
                player = new Player(drawpane);
                input_word(3);
                createZombieThread(mode);
                break;
            case "Boss":
                drawpane.setIcon(in_gamebg5Img);
                drawpane.setLayout(null);
                contentpane.add(drawpane, BorderLayout.CENTER);
                bossSong.playLoop();
                player = new Player(drawpane);
                input_word(4);
                // createZombieThread("Nightmare");
                createZombieThread(mode);

                break;
        }
        // player = new Player();
        // player.draw_player(drawpane);
        // player.draw_healthbar(drawpane);

        setup_thread_list();

    }

    public void createZombieThread(String mode) {
        // ArrayList<ZombieThread> allZombThread = new ArrayList<ZombieThread>();
        for (int i = 0; i < 10; i++) {

            ZombieThread zombThread = new ZombieThread("Zombie" + i, player, drawpane, modeSelected, i, count, PBar,
                    program, wbox_AL);
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
        for (int j = 0; j < zombielist.size(); j++) {
            zombielist.get(i).kill_monster(i);
        }
        // wbox_AL.get(i).setvisible(false);
        // drawpane.remove(wbox_AL.get(i));
        // drawpane.repaint();
        setCount_death();

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
        count += 1;
        PBar.setValue(count * 10);
        System.out.println("Add Count + = 1");
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

    // public void kill_monster(int i) {
    // drawpane.remove(mobLabel.get(i));
    // wbox_AL.get(i).setvisible(false);
    // // drawpane.remove(wbox_AL.get(i));
    // drawpane.repaint();
    // setCount_death();

    // }

    // ---------------------------- Game Over ------------------------
    public void stageEnd(String mode) {
        winGif = new MyImageIcon("gameOver/win.gif");
        gameOverGif = new MyImageIcon("gameOver/game_over.gif");
        JLabel winLabel = new JLabel(winGif);
        JLabel gameOverLabel = new JLabel(gameOverGif);
        winLabel.setBounds((frameWidth / 2) - 280, 130, 620, 200);
        gameOverLabel.setBounds((frameWidth / 2) - 400, 130, 800, 200);

        JButton button1 = new JButton();
        JButton button2 = new JButton();
        setUpButton(button1, restartButton);
        setUpButton(button2, menuButton);
        button1.setBounds((frameWidth / 2) - 225, (frameHeight / 2) + 50, 200, 50);
        button2.setBounds((frameWidth / 2) + 25, (frameHeight / 2) + 50, 200, 50);

        /*
         * // * Waiting For my Brain --Show Score
         * JPanel scorepane = new JPanel();
         * JTextField showScore = new JTextField("SCORE : 34",10);
         * showScore.setFont(new Font("Comic Sans Ms",Font.BOLD+Font.ITALIC,20));
         * scorepane.add(showScore);
         * //contentpane.add(scorepane,BorderLayout.CENTER);
         */

        // ----------------Stop All sound and delete All component in main
        // game-----------------------
        button1.setBounds((frameWidth / 2) - 225, (frameHeight / 2) + 40, 200, 50);
        button2.setBounds((frameWidth / 2) + 25, (frameHeight / 2) + 40, 200, 50);

        JTextField scoreText = new JTextField("  SCORE : " + score, 10);
        scoreText.setEditable(false);
        scoreText.setFont(new Font("Comic Sans Ms", Font.BOLD + Font.ITALIC, 25));
        scoreText.setBackground(new Color(255, 255, 255, 100));

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BorderLayout());
        scorePanel.setBounds((frameWidth / 2) - 100, 350, 200, 30);
        scorePanel.add(scoreText, BorderLayout.CENTER);

        // ----------------Stop All sound and delete All component in main
        // game-----------------------
        // normalHitSound.stop();
        // softHitSound.stop();
        // criHitSound.stop();
        // usedItemSound.stop();
        // readyGoSound.stop();
        beginnerSong.stop();
        mediumSong.stop();
        hardSong.stop();
        nightmareSong.stop();
        bossSong.stop();
        comeIn = false;
        gameEnd = true;
        count_death = 0;
        resetPBar();
        // drawpane.removeAll();

        if (player.getHP() > 0 && gameEnd == true && gameResult == "Win") { // Win
            drawpane.repaint();
            drawpane.add(winLabel);
            winSound.playOnce();
            gameEnd = false;
            gameResult = "";
        } else if (player.getHP() == 0 && gameEnd == true &&
                gameResult == "GameOver") { // gameOver
            drawpane.add(gameOverLabel);
            gameOverSound.playOnce();
            gameEnd = false;
            gameResult = "";
        }
        button1.addActionListener(new ActionListener() { // Restart Game
            public void actionPerformed(ActionEvent e) {
                buttonSound.playOnce();
                winSound.stop();
                gameOverSound.stop();
                gameEnd = false;
                drawpane.removeAll();
                repaint();
                validate();
                main_game(mode);
            }
        });

        button2.addActionListener(new ActionListener() { // Back to Menu
            public void actionPerformed(ActionEvent e) {
                gameEnd = false;
                buttonSound.playOnce();
                winSound.stop();
                gameOverSound.stop();
                drawpane.removeAll();
                repaint();
                validate();
                mainmanu();
            }
        });
        drawpane.add(scorePanel);
        drawpane.add(button1);
        drawpane.add(button2);
        validate();
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

    public void read_poke_custom() {
        for (int i = 0; i < poke_list.length; i++) {
            JLabel label = new JLabel(new ImageIcon(poke_list[i]));
            label.setBounds(450, 40, 500, 250);
            custom_poke_AL.add(label);
            // poke_list_AL.add(poke_list[i]);
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
