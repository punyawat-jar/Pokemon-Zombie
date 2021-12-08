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
    private MainApplication program; // Use to call this class' function in other class
    /* For Pausing Game, use setPauseGame(true) zombieThread will pause. */
    // ------------------------------- Component -------------------------------
    private JPanel contentpane;
    private JLabel drawpane;
    private JComboBox combo;
    private JToggleButton[] tb;
    private JTextField scoreText;
    private JLabel Label;
    private ButtonGroup bgroup;
    private boolean pauseGame = false;
    private boolean joinComplete = false;
    private String modeSelected;

    private MyImageIcon bgImg, bgImg2, in_gamebg1Img, in_gamebg2Img, in_gamebg3Img, in_gamebg4Img, in_gamebg5Img;
    private MySoundEffect menuSong, creditSong, beginnerSong, mediumSong, hardSong, nightmareSong, bossSong;

    private JRadioButton[] radio;
    private String[] accessory = { "poke1", "poke2", "poke3", "poke4", "poke5" };

    private MyImageIcon emptyButton,startButton, creditButton, tutorialButton, exitButton, playButton, restartButton, menuButton,
            nextButton, backButton;
    private MySoundEffect buttonSound, normalHitSound, softHitSound, criHitSound, hurtSound, gameOverSound, winSound,
            usedItemSound;

    private MyImageIcon zomb1Img, zomb2Img, zomb3Img, zomb4Img, zombBossImg; // Beginner & Boss
    private MyImageIcon nzomb1Img, nzomb2Img, nzomb3Img, nzomb4Img; // Medium (Night)
    private MyImageIcon ezomb1Img, ezomb2Img, ezomb3Img, ezomb4Img; // Hard (Earth)
    private MyImageIcon szomb1Img, szomb2Img, szomb3Img; // Nightmare (Stone)
    private JLabel zomb1Label, zomb2Label, zomb3Label, zomb4Label, zombBossLabel;
    private JLabel nzomb1Label, nzomb2Label, nzomb3Label, nzomb4Label;
    private JLabel ezomb1Label, ezomb2Label, ezomb3Label, ezomb4Label;
    private JLabel szomb1Label, szomb2Label, szomb3Label, szomb4Label;
    private MyImageIcon readyGoImg;
    private JLabel readyGoLabel;
    private MySoundEffect readyGoSound;
    private int zombSpeed = 300;
    ArrayList<JLabel> mobLabel = new ArrayList<JLabel>();
    ArrayList<Integer> mobCurX = new ArrayList<Integer>();
    ArrayList<Integer> mobCurY = new ArrayList<Integer>();
    ArrayList<Integer> mobWidth = new ArrayList<Integer>();
    ArrayList<Integer> mobHeight = new ArrayList<Integer>();
    // ArrayList<Thread> mobThread = new ArrayList<Thread>();
    ArrayList<Wordbox> wbox_AL = new ArrayList<Wordbox>();
    ArrayList<JLabel> custom_poke_AL = new ArrayList<JLabel>();
    private MyImageIcon winGif, gameOverGif;

    private JProgressBar PBar = new JProgressBar();
    private Keyboard_bar keybar;
    private int frameWidth = 1366, frameHeight = 768;
    private int itemWidth = 40, itemHeight = 50;
    private int score = 0, count = 0, count_pic = 0;

    private Player player;
    //private Bomb bomb;
    // private Potion potion;

    Tutorial Tframe;
    private String[] poke_list = { "custom_poke/poke1.png", "custom_poke/poke2.png", "custom_poke/poke3.png",
            "custom_poke/poke4.png", "custom_poke/poke5.png" };

    private String[]mode = {"Vocab/Beginner.txt", "Vocab/Medium.txt", "Vocab/Hard.txt","Vocab/Nightmare.txt","Vocab/Boss.txt"};
    ArrayList<Vocab> modeList = new ArrayList<Vocab>();

    // ------------------------------- Main Method -------------------------------
    public static void main(String[] args) {
        new MainApplication();
    }

    public int get_framewidth() {
        return frameWidth;
    }

    public int get_frameheight() {
        return frameHeight;
    }

    public boolean getPauseGame() {
        return pauseGame;
    }

    public void setPauseGame(boolean x) {
        pauseGame = x;
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

        // ------------------------------- Zombie -----------------------------------
        readyGoImg = new MyImageIcon("sound_effect/321_Go.gif");
        readyGoLabel = new JLabel(readyGoImg);

        for (int i = 0; i < 16; i++) {
            switch (i) {
                case 0: // z1
                    mobWidth.add(98);
                    mobHeight.add(157);
                    break;
                case 1:// z2
                    mobWidth.add(140);
                    mobHeight.add(139);
                    break;
                case 2:// z3
                    mobWidth.add(138);
                    mobHeight.add(153);
                    break;
                case 3:// z4
                    mobWidth.add(140);
                    mobHeight.add(140);
                    break;
                case 4:// nz1
                    mobWidth.add(98);
                    mobHeight.add(157);
                    break;
                case 5:// nz2
                    mobWidth.add(140);
                    mobHeight.add(139);
                    break;
                case 6:// nz3
                    mobWidth.add(138);
                    mobHeight.add(153);
                    break;
                case 7:// nz4
                    mobWidth.add(140);
                    mobHeight.add(140);
                    break;
                case 8:// ez1
                    mobWidth.add(143);
                    mobHeight.add(140);
                    break;
                case 9:// ez2
                    mobWidth.add(140);
                    mobHeight.add(117);
                    break;
                case 10:// ez3
                    mobWidth.add(140);
                    mobHeight.add(153);
                    break;
                case 11:// ez4
                    mobWidth.add(170);
                    mobHeight.add(170);
                    break;
                case 12:// sz1
                    mobWidth.add(140);
                    mobHeight.add(140);
                    break;
                case 13:// sz2
                    mobWidth.add(187);
                    mobHeight.add(140);
                    break;
                case 14:// sz3
                    mobWidth.add(140);
                    mobHeight.add(140);

                case 15:// boss
                    mobWidth.add(182);
                    mobHeight.add(223);
                    break;
            }
            mobCurX.add(i, frameWidth);
            if (0 <= i && i <= 7) {
                mobCurY.add(frameHeight - 185 - mobHeight.get(i));
            } else if (8 <= i && i <= 11) {
                if (i == 9)
                    mobCurY.add(frameHeight - 160 - mobHeight.get(i));
                else
                    mobCurY.add(frameHeight - 180 - mobHeight.get(i));
            } else if (i == 13 || i == 14)
                mobCurY.add(frameHeight - 200 - mobHeight.get(i));
            else
                mobCurY.add(frameHeight - 170 - mobHeight.get(i));
        }
        // 0-3 Beginner
        zomb1Img = new MyImageIcon("zombie/z01.png").resize(mobWidth.get(0), mobHeight.get(0));
        zomb2Img = new MyImageIcon("zombie/z02.png").resize(mobWidth.get(1), mobHeight.get(1));
        zomb3Img = new MyImageIcon("zombie/z03.png").resize(mobWidth.get(2), mobHeight.get(2));
        zomb4Img = new MyImageIcon("zombie/z04.png").resize(mobWidth.get(3), mobHeight.get(3));
        // 4-7 Medium
        nzomb1Img = new MyImageIcon("zombie/nz01.png").resize(mobWidth.get(4), mobHeight.get(4));
        nzomb2Img = new MyImageIcon("zombie/nz02.png").resize(mobWidth.get(5), mobHeight.get(5));
        nzomb3Img = new MyImageIcon("zombie/nz03.png").resize(mobWidth.get(6), mobHeight.get(6));
        nzomb4Img = new MyImageIcon("zombie/nz04.png").resize(mobWidth.get(7), mobHeight.get(7));
        // 8-11 Hard
        ezomb1Img = new MyImageIcon("zombie/ez01.png").resize(mobWidth.get(8), mobHeight.get(8));
        ezomb2Img = new MyImageIcon("zombie/ez02.png").resize(mobWidth.get(9), mobHeight.get(9));
        ezomb3Img = new MyImageIcon("zombie/ez03.png").resize(mobWidth.get(10), mobHeight.get(10));
        ezomb4Img = new MyImageIcon("zombie/ez04.png").resize(mobWidth.get(11), mobHeight.get(11));
        // 12-14 Nightmare (also Use zombie from other mode)
        szomb1Img = new MyImageIcon("zombie/sz01.png").resize(mobWidth.get(12), mobHeight.get(12));
        szomb2Img = new MyImageIcon("zombie/sz02.png").resize(mobWidth.get(13), mobHeight.get(13));
        szomb3Img = new MyImageIcon("zombie/sz03.png").resize(mobWidth.get(14), mobHeight.get(14));
        // 15 Boss
        zombBossImg = new MyImageIcon("zombie/zboss.png").resize(mobWidth.get(15), mobHeight.get(15));

        // ---------------------------- Sound --------------------------------------
        buttonSound = new MySoundEffect("sound_effect/button_soundeffect.wav");
        normalHitSound = new MySoundEffect("sound_effect/NormalHit_soundeffect.wav");
        softHitSound = new MySoundEffect("sound_effect/SoftHit_soundeffect.wav");
        criHitSound = new MySoundEffect("sound_effect/CriticalHit_soundeffect.wav");
        hurtSound = new MySoundEffect("sound_effect/Hurt_soundeffect.wav");
        gameOverSound = new MySoundEffect("sound_effect/GameOver_soundeffect.wav");
        winSound = new MySoundEffect("sound_effect/Win_soundeffect.wav");
        usedItemSound = new MySoundEffect("sound_effect/UsedItem_soundeffect.wav");
        readyGoSound = new MySoundEffect("sound_effect/321GoCountdown.wav");

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
        wbox_AL.clear();
        switch (mode) {
            case "Beginner":
                drawpane.setIcon(in_gamebg1Img);
                drawpane.setLayout(null);
                contentpane.add(drawpane, BorderLayout.CENTER);
                beginnerSong.playLoop();
                player = new Player(drawpane);
                addZombieBeginner(mode);
                //input_word(0);
                break;
            case "Medium":
                drawpane.setIcon(in_gamebg2Img);
                drawpane.setLayout(null);
                contentpane.add(drawpane, BorderLayout.CENTER);
                mediumSong.playLoop();
                player = new Player(drawpane);
                addZombieMedium(mode);
                //input_word(1);
                break;
            case "Hard":
                drawpane.setIcon(in_gamebg3Img);
                drawpane.setLayout(null);
                contentpane.add(drawpane, BorderLayout.CENTER);
                hardSong.playLoop();
                player = new Player(drawpane);
                addZombieHard(mode);
                //input_word(2);
                break;
            case "Nightmare":
                drawpane.setIcon(in_gamebg4Img);
                drawpane.setLayout(null);
                contentpane.add(drawpane, BorderLayout.CENTER);
                nightmareSong.playLoop();
                player = new Player(drawpane);
                addZombieNightmare(mode);
                //input_word(3);
                break;
            case "Boss":
                drawpane.setIcon(in_gamebg5Img);
                drawpane.setLayout(null);
                contentpane.add(drawpane, BorderLayout.CENTER);
                bossSong.playLoop();
                player = new Player(drawpane);
                //input_word(4);
                // addZombieHard();

                break;
        }
        // player = new Player();
        // player.draw_player(drawpane);
        // player.draw_healthbar(drawpane);

        PBar.setValue(0);
        PBar.setBounds(frameWidth - 460, frameHeight - (50 * 2), 420, 50);
        PBar.setForeground(new Color(255, 199, 56));
        PBar.setStringPainted(false);
        drawpane.add(PBar);

        keybar = new Keyboard_bar();
        keybar.setPane(drawpane, this);
        keybar.getTypearea().grabFocus();


        // gameover(mode);
        // // validate();
        // joinThread(10);
        // while (!joinComplete) {
        // System.out.println("Before if, mobThread size = " + mobThread.size());
        // if (mobThread.size() == 10) {
        // System.out.println("Join all Threads");
        // // validate();
        // joinThread(10);
        // System.out.println("End game, Stage: " + mode);
        // System.out.println("mobThread size = " + mobThread.size());
        // // }
        // joinComplete = true;
        // }
        // }
    }

    public void setPbar(JProgressBar bar) {
        count += 1;
        bar.setValue(count * 10);
        System.out.println("Add Count + = 1");
    }

    // public void joinThread(int n) {
    // for (int i = 0; i < n; i++) {
    // try {
    // mobThread.get(i).join();
    // } catch (InterruptedException e) {
    // }
    // }
    // }

    // ----------------------------- Add 10 Zombies to screen--------------------
    public void addZombieBeginner(String mode) {
        for (int i = 0; i < 10; i++) {
            int zombie = randomNum(4);
            switch (zombie) {
                case 0:
                    mobLabel.add(new JLabel(zomb1Img));
                    mobLabel.get(i).setBounds(mobCurX.get(0), mobCurY.get(0), mobWidth.get(0),
                            mobHeight.get(0));
                    break;
                case 1:
                    mobLabel.add(new JLabel(zomb2Img));
                    mobLabel.get(i).setBounds(mobCurX.get(1), mobCurY.get(1), mobWidth.get(1),
                            mobHeight.get(1));
                    break;
                case 2:
                    mobLabel.add(new JLabel(zomb3Img));
                    mobLabel.get(i).setBounds(mobCurX.get(2), mobCurY.get(2), mobWidth.get(2),
                            mobHeight.get(2));
                    break;
                case 3:
                    mobLabel.add(new JLabel(zomb4Img));
                    mobLabel.get(i).setBounds(mobCurX.get(3), mobCurY.get(3), mobWidth.get(3),
                            mobHeight.get(3));
                    break;
            }
            System.out.println("Size Of zombLabel = " + mobLabel.size());
        } // end for getZombie
        setZombSpeed(250);
        createZombieThread(mode);
    }// end addZombieBeginner

    public void addZombieMedium(String mode) {
        for (int i = 0; i < 10; i++) {
            int zombie = randomNum(4);
            switch (zombie) {
                case 0:
                    mobLabel.add(new JLabel(nzomb1Img));
                    mobLabel.get(i).setBounds(mobCurX.get(4), mobCurY.get(4), mobWidth.get(4),
                            mobHeight.get(4));
                    break;
                case 1:
                    mobLabel.add(new JLabel(nzomb2Img));
                    mobLabel.get(i).setBounds(mobCurX.get(5), mobCurY.get(5), mobWidth.get(5),
                            mobHeight.get(5));
                    break;
                case 2:
                    mobLabel.add(new JLabel(nzomb3Img));
                    mobLabel.get(i).setBounds(mobCurX.get(6), mobCurY.get(6), mobWidth.get(6),
                            mobHeight.get(6));
                    break;
                case 3:
                    mobLabel.add(new JLabel(nzomb4Img));
                    mobLabel.get(i).setBounds(mobCurX.get(7), mobCurY.get(7), mobWidth.get(7),
                            mobHeight.get(7));
                    break;
            }
            System.out.println("Size Of zombLabel = " + mobLabel.size());
        } // end for
        setZombSpeed(200);
        createZombieThread(mode);
    } // end addZombieMedium

    // ----------------------------- Add 10 Zombies to screen--------------------
    public void addZombieHard(String mode) {
        for (int i = 0; i < 10; i++) {

            int zombie = randomNum(4);
            switch (zombie) {
                case 0:
                    mobLabel.add(new JLabel(ezomb1Img));
                    mobLabel.get(i).setBounds(mobCurX.get(8), mobCurY.get(8), mobWidth.get(8),
                            mobHeight.get(8));
                    break;
                case 1:
                    mobLabel.add(new JLabel(ezomb2Img));
                    mobLabel.get(i).setBounds(mobCurX.get(9), mobCurY.get(9), mobWidth.get(9),
                            mobHeight.get(9));
                    break;
                case 2:
                    mobLabel.add(new JLabel(ezomb3Img));
                    mobLabel.get(i).setBounds(mobCurX.get(10), mobCurY.get(10), mobWidth.get(10),
                            mobHeight.get(10));
                    break;
                case 3:
                    mobLabel.add(new JLabel(ezomb4Img));
                    mobLabel.get(i).setBounds(mobCurX.get(11), mobCurY.get(11), mobWidth.get(11),
                            mobHeight.get(11));
                    break;
            }
            System.out.println("Size Of zombLabel = " + mobLabel.size());
        } // end for getZombie
        setZombSpeed(150);
        createZombieThread(mode);
    }// end addZombieHard

    // ----------------------------- Add 10 Zombies to screen--------------------
    public void addZombieNightmare(String mode) {
        for (int i = 0; i < 10; i++) {
            int zombie = randomNum(5);
            switch (zombie) {
                case 0:
                    mobLabel.add(new JLabel(szomb1Img));
                    mobLabel.get(i).setBounds(mobCurX.get(12), mobCurY.get(12), mobWidth.get(12),
                            mobHeight.get(12));
                    break;
                case 1:
                    mobLabel.add(new JLabel(szomb2Img));
                    mobLabel.get(i).setBounds(mobCurX.get(13), mobCurY.get(13), mobWidth.get(13),
                            mobHeight.get(13));
                    break;
                case 2:
                    mobLabel.add(new JLabel(szomb3Img));
                    mobLabel.get(i).setBounds(mobCurX.get(14), mobCurY.get(14), mobWidth.get(14),
                            mobHeight.get(14));
                    break;
                case 3: // Use Senigame Zombie from Beginner mode
                    mobLabel.add(new JLabel(zomb4Img));
                    mobLabel.get(i).setBounds(mobCurX.get(3), mobCurY.get(3) + 15, mobWidth.get(3),
                            mobHeight.get(3));
                    break;
                case 4: // Use Sunflower Zombie from hard mode
                    mobLabel.add(new JLabel(ezomb4Img));
                    mobLabel.get(i).setBounds(mobCurX.get(11), mobCurY.get(11), mobWidth.get(11),
                            mobHeight.get(11));
                    break;
            }
            System.out.println("Size Of zombLabel = " + mobLabel.size());
        } // end for getZombie
        setZombSpeed(100);
        createZombieThread(mode);
    }// end addZombieNightmare

    public void createZombieThread(String mode) {
        for (int i = 0; i < 10; i++) {

            mobCurX.set(i, mobLabel.get(i).getX());
            mobCurY.set(i, mobLabel.get(i).getY());

            setZombieThread(i);

            if (i == 0) {
                readyGoSound.playOnce();
                readyGoLabel.setBounds(525, 230, 380, 214);
                drawpane.add(readyGoLabel);
                drawpane.validate();
            }
            System.out.println("thread = " + Thread.currentThread().getName() + " Get in");
        }
    }// end CreateZombieThread

    // We Create Thread in setZombieThread in order to easily edit Variable from
    // MainApplication
    public void setZombieThread(int i) {
        System.out.println("mode = " + modeSelected);
        System.out.println("zombCurX = " + mobCurX.get(i) + ", zombCurY = " + mobCurY.get(i) + " zombWidth = "
                + mobLabel.get(i).getWidth()
                + " , zombHeight" + mobLabel.get(i).getHeight());

        mobLabel.get(i).setBounds(mobCurX.get(i), mobCurY.get(i), mobLabel.get(i).getWidth(),
                mobLabel.get(i).getHeight());
        drawpane.add(mobLabel.get(i));
        drawpane.validate();
        // mobThread.add(new Thread("Zombie" + i) {

        Thread zombieThread = new Thread("Zombie" + i) {
            public void run() {
                //wbox = new Wordbox(drawpane);
                System.out.println("thread = " + this.getName());
                if (modeSelected == "Nightmare") {
                    waitGetInNightmare(i);
                } else if (modeSelected == "Hard") {
                    waitGetInHard(i);
                } else {
                    waitGetIn(i);
                }
                setPbar(PBar);
                move(i);
                

                if (player.getHP() == 0) {
                    gameover(modeSelected);
                    return;
                }
                // --------- Remove Zombie when Hit Pikachu & decrease heart
                if (mobLabel.get(i).getBounds().intersects(player.getLabel().getBounds())) {
                    hurtSound.playOnce();
                    player.hitplayer(drawpane);
                    drawpane.remove(mobLabel.get(i));
                    drawpane.repaint();
                }
            } // end run
        };// end thread creation

        zombieThread.start();
    }

    // ------------------------- For randoming time Zombie Appear ----------------
    // Use static method to lock class * If lock only Obj. all other thread will
    // work and wait together.
    synchronized public static void waitGetIn(int i) {
        int timeWait = 3000;
        if (i != 0) {
            Random r = new Random();
            int low = 5000;
            int high = 15000;
            timeWait = r.nextInt(high - low) + low;
            try {
                Thread.sleep(timeWait);
            } catch (InterruptedException e) {
            }
        } else {
            try {
                Thread.sleep(timeWait);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Thread: " + Thread.currentThread().getName() + " Waiting" + timeWait / 1000 + "sec");
    }

    synchronized public static void waitGetInHard(int i) {
        int timeWait = 3000;
        if (i != 0) {
            Random r = new Random();
            int low = 5000;
            int high = 10000;
            timeWait = r.nextInt(high - low) + low;
            try {
                Thread.sleep(timeWait);
            } catch (InterruptedException e) {
            }
        } else {
            try {
                Thread.sleep(timeWait);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Thread: " + Thread.currentThread().getName() + " Waiting" + timeWait / 1000 + "sec");
    }

    synchronized public static void waitGetInNightmare(int i) {
        int timeWait = 3000;
        if (i != 0) {
            Random r = new Random();
            int low = 4000;
            int high = 7500;
            timeWait = r.nextInt(high - low) + low;
            try {
                Thread.sleep(timeWait);
            } catch (InterruptedException e) {
            }
        } else {
            try {
                Thread.sleep(timeWait);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Thread: " + Thread.currentThread().getName() + " Waiting" + timeWait / 1000 + "sec");
    }

    // ----------------- If zombie not hit pikachu, it walks to left----------------
    public void move(int i) {
        System.out.println("Thread: Zombie" + i + " -> move");
        // While not Hit player & player not die. walk left
        while (!(mobLabel.get(i).getBounds().intersects(player.getLabel().getBounds())) && player.getHP() != 0) {
            mobLabel.get(i).setLocation(mobCurX.get(i), mobCurY.get(i));
            if (!pauseGame) {
                mobCurX.set(i, mobCurX.get(i) - 5);
                mobLabel.get(i).repaint();
                //wbox_AL.get(i).wbox_move(mobCurX.get(i) - 5, mobCurY.get(i)-20);
                //wbox.wbox_move(mobCurX.get(i) - 5,mobCurY.get(i)-20);
            } 
            else{
                System.out.println("Pause ZombieThread: " + Thread.currentThread().getName());
            }
            mobLabel.get(i).repaint();
            try {
                Thread.sleep(zombSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } // end while
    }

    public int randomNum(int amount) {
        Random random = new Random();
        int randomNum = random.nextInt(amount);
        mobCurX.add(frameWidth);
        System.out.println(randomNum);
        return randomNum;
    }

    public void setZombSpeed(int spd) {
        zombSpeed = spd;
    }

    public void addCount(int c) {
        count += c;
        System.out.println("Add Count + = 1");
    }

    // ---------------------------- Game Over ------------------------
    public void gameover(String mode) {
        winGif = new MyImageIcon("gameOver/win.gif");
        gameOverGif = new MyImageIcon("gameOver/game_over.gif");
        JLabel winLabel = new JLabel(winGif);
        JLabel gameOverLabel = new JLabel(gameOverGif);
        winLabel.setBounds((frameWidth / 2) - 280, 120, 620, 200);
        gameOverLabel.setBounds((frameWidth / 2) - 400, 120, 800, 200);

        JButton button1 = new JButton();
        JButton button2 = new JButton();
        setUpButton(button1, restartButton);
        setUpButton(button2, menuButton);
        button1.setBounds((frameWidth / 2) - 225, (frameHeight / 2) + 50, 200, 50);
        button2.setBounds((frameWidth / 2) + 25, (frameHeight / 2) + 50, 200, 50);

        /*
         // * Waiting For my Brain --Show Score
           JPanel scorepane = new JPanel();
           JTextField showScore = new JTextField("SCORE : 34",10);
           showScore.setFont(new Font("Comic Sans Ms",Font.BOLD+Font.ITALIC,20));
           scorepane.add(showScore);
           //contentpane.add(scorepane,BorderLayout.CENTER);
        */

        // ----------------Stop All sound and delete All component in main
        // game-----------------------
        normalHitSound.stop();
        softHitSound.stop();
        criHitSound.stop();
        hurtSound.stop();
        usedItemSound.stop();
        readyGoSound.stop();
        beginnerSong.stop();
        mediumSong.stop();
        hardSong.stop();
        nightmareSong.stop();
        bossSong.stop();
        // drawpane.removeAll();

        button1.addActionListener(new ActionListener() { // Restart Game
            public void actionPerformed(ActionEvent e) {
                buttonSound.playOnce();
                winSound.stop();
                gameOverSound.stop();
                // winLabel.setVisible(false);
                // gameOverLabel.setVisible(false);
                // button1.setVisible(false);
                // button2.setVisible(false);

                drawpane.removeAll();
                repaint();
                validate();
                main_game(mode);
            }
        });

        button2.addActionListener(new ActionListener() { // Back to Menu
            public void actionPerformed(ActionEvent e) {
                buttonSound.playOnce();
                winSound.stop();
                gameOverSound.stop();
                // winLabel.setVisible(false);
                // gameOverLabel.setVisible(false);
                // button1.setVisible(false);
                // button2.setVisible(false);
                // PBar.setVisible(false);

                drawpane.removeAll();
                repaint();
                validate();
                mainmanu();
            }
        });

        if (player.getHP() == 0) { // Game Over
            drawpane.add(gameOverLabel);
            gameOverSound.playOnce();
        }
        /*
         * if(score==10) { // win
         * drawpane.add(winLabel);
         * winSound.playOnce();
         * }
         */

        drawpane.add(button1);
        drawpane.add(button2);
    }
    // ---------------------------- Set up Cursor & Button ------------------------

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

    public void readFile(String[] mode) {
        for (int i = 0; i < mode.length; i++) {
             enforceFile(mode[i]);
        }
        //System.out.printf("----------------------------------------------\n");
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
        for (int i = 0; i < modeList.size(); i++) {
            modeList.get(i).printFileWord();
         }
        System.out.println("");
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
