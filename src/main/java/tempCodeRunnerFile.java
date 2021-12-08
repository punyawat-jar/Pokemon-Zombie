
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
         * Waiting For my Brain --Show Score
         * JTextField showScore = new JTextField("SCORE : 34",10);
         * showScore.setFont(new Font("Comic Sans Ms",Font.BOLD+Font.ITALIC,20));
         * //drawpane.add(showScore,frameWidth/2,200);
         */

        // ----------------Stop All sound and delete All component in main
        // game-----------------------
        normalHitSound.stop();
        softHitSound.stop();
        criHitSound.stop();
        usedItemSound.stop();
        readyGoSound.stop();
        beginnerSong.stop();
        mediumSong.stop();
        hardSong.stop();
        nightmareSong.stop();
        bossSong.stop();
        comeIn = false;
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

                // drawpane.removeAll();
                repaint();
                validate();
                mainmanu();
            }
        });

        if (player.getHP() > 0) { // Win
            drawpane.add(winLabel);
            winSound.playOnce();
        } else { // gameOver
            drawpane.add(gameOverLabel);
            gameOverSound.playOnce();
        }

        drawpane.add(button1);
        drawpane.add(button2);
    }