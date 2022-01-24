import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.*;
import javax.swing.*;
import java.awt.*;


public class WheelofFortune extends JFrame implements ActionListener {
    //when any consonant/vowel button is clicked
    @Override
    public void actionPerformed(ActionEvent E) {
        JButton button = (JButton) E.getSource();
        letter = button.getText();
        fill(letter);

        //update board
        board.setText(stateOfPhrase);

        //if letter was a vowel, add it to the usedVowels string, then subtract 250 from current player
        //if letter was a consonant, add it to the usedConsonants string, and add money to current player
        if (letter.equals("A") || letter.equals("E") || letter.equals("I") ||
                letter.equals("O") || letter.equals("U")) {
            usedVowels += letter;
            if (whosTurn()) {
                p1.setRoundTotal(p1.getRoundTotal() - 250);
                player1Round.setText(String.valueOf(p1.getRoundTotal()));
            } else {
                p2.setRoundTotal(p2.getRoundTotal() - 250);
                player2Round.setText(String.valueOf(p2.getRoundTotal()));
            }
        } else {
            usedConsonants += letter;
            int addMoney = 0;
            addMoney += (numCorrect * wedgeValue);
            if (isPlayer1Turn) {
                p1.setRoundTotal(p1.getRoundTotal() + addMoney);
                player1Round.setText(String.valueOf(p1.getRoundTotal()));
            } else {
                p2.setRoundTotal(p2.getRoundTotal() + addMoney);
                player2Round.setText(String.valueOf(p2.getRoundTotal()));
            }
        }

        //disable all buttons
        disableAll();

        //if the letter the player guessed isn't on the board
        if (numCorrect == 0)
            switchPlayer();

        //always reset numCorrect AND wedgeValue after a letter/vowel is guessed
        numCorrect = 0;
        wedgeValue = 0;
        guessPuzzle.setEnabled(true);

        //check which options to enable
        if (usedVowels.contains("AEIOU"))
            buyVowel.setEnabled(false);
        else
            buyVowel.setEnabled(true);

        if (usedConsonants.contains("BCDFGHJKLMNPQRSTVWXYZ"))
            spinButton.setEnabled(false);
        else
            spinButton.setEnabled(true);


        //if board becomes filled
        if (board.getText().equals(gameBoardPhrase)) {
            //update total score for each player
            p1.setTotalScore(p1.getTotalScore() + p1.getRoundTotal());
            player1Total.setText(String.valueOf(p1.getTotalScore()));
            p2.setTotalScore(p2.getTotalScore() + p2.getRoundTotal());
            player2Total.setText(String.valueOf(p2.getTotalScore()));

            //clear background
            disableAll();
            buyVowel.setEnabled(false);
            spinButton.setEnabled(false);
            guessPuzzle.setEnabled(false);
            wedgePanel.setText("");
            wedgePanel.setBackground(Color.WHITE);
            board.setText("");
            category.setText("");
            player1Round.setText("");
            player2Round.setText("");

            JOptionPane.showMessageDialog(null, new JLabel("NEXT ROUND!"), "Phrase: " + gameBoardPhrase, JOptionPane.INFORMATION_MESSAGE);


            //reset game
            reset();

            //increase the number of rounds
            numRounds++;

            //start a new game
            if (numRounds != 4)
                startRound();
            else {
                disableAll();
                buyVowel.setEnabled(false);
                spinButton.setEnabled(false);
                guessPuzzle.setEnabled(false);
                wedgePanel.setText("");
                wedgePanel.setBackground(Color.WHITE);
                board.setText("");
                category.setText("");
                player1Round.setText("");
                player2Round.setText("");
                startBonusRound();
            }

        }

    }

    //letter: letter guessed, gameBoardPhrase: phrase to be guessed, stateOfPhrase: what letters are currently
    //on the board, numCorrect: how many letters were filled on a guess
    String letter = "";
    String gameBoardPhrase = "";
    String stateOfPhrase = "";
    int numCorrect = 0;

    //controls the board
    JTextField board = new JTextField();

    //keep track of used vowels
    String usedVowels = "";

    //keep track of used consonants
    String usedConsonants = "";

    //stores wheel wedges
    String[] wedges = {"200", "250", "300", "350", "400", "450", "500", "550", "600", "650", "700", "750", "800", "850", "900", "950",
            "1000", "Bankrupt", "Lose A Turn"};

    //set wedge value
    int wedgeValue = 0;

    //keeps track of the number of rounds played
    int numRounds = 1;

    JFrame frame = new JFrame();
    JLabel labelCategory = new JLabel("Category");
    JLabel category = new JLabel();
    Random rand = new Random();

    JButton buyVowel = new JButton("Buy a Vowel");
    JButton spinButton = new JButton("Spin");
    JButton guessPuzzle = new JButton("Guess the Puzzle");

    //alphabet
    JButton a = new JButton("A");
    JButton b = new JButton("B");
    JButton c = new JButton("C");
    JButton d = new JButton("D");
    JButton e = new JButton("E");
    JButton f = new JButton("F");
    JButton g = new JButton("G");
    JButton h = new JButton("H");
    JButton i = new JButton("I");
    JButton j = new JButton("J");
    JButton k = new JButton("K");
    JButton l = new JButton("L");
    JButton m = new JButton("M");
    JButton n = new JButton("N");
    JButton o = new JButton("O");
    JButton p = new JButton("P");
    JButton q = new JButton("Q");
    JButton r = new JButton("R");
    JButton s = new JButton("S");
    JButton t = new JButton("T");
    JButton u = new JButton("U");
    JButton v = new JButton("V");
    JButton w = new JButton("W");
    JButton x = new JButton("X");
    JButton y = new JButton("Y");
    JButton z = new JButton("Z");

    final JFrame enterSolve = new JFrame();

    //creating 2 player game
    Player p1 = new Player();
    Player p2 = new Player();

    //creating labels for players
    JLabel player1 = new JLabel("Player 1");
    JLabel player2 = new JLabel("Player 2");
    JLabel round = new JLabel("Round");
    JLabel total = new JLabel("Total");
    JLabel player1Round = new JLabel();
    JLabel player1Total = new JLabel();
    JLabel player2Round = new JLabel();
    JLabel player2Total = new JLabel();

    //keeps track of the wedge spun
    JLabel wedgePanel = new JLabel();
    JLabel header = new JLabel();

    //keep track of whose turn it is
    boolean isPlayer1Turn = false;

    WheelofFortune() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buyVowel.setBounds(250, 430, 200, 40);
        buyVowel.setFocusable(false);

        spinButton.setBounds(480, 430, 200, 40);
        spinButton.setFocusable(false);

        guessPuzzle.setBounds(710, 430, 200, 40);
        guessPuzzle.setFocusable(false);

        a.setBounds(50, 500, 100, 20);
        b.setBounds(290, 500, 100, 20);
        c.setBounds(400, 500, 100, 20);
        d.setBounds(510, 500, 100, 20);
        e.setBounds(160, 500, 100, 20);
        f.setBounds(620, 500, 100, 20);
        g.setBounds(730, 500, 100, 20);
        h.setBounds(840, 500, 100, 20);
        i.setBounds(50, 530, 100, 20);
        j.setBounds(950, 500, 100, 20);
        k.setBounds(290, 530, 100, 20);
        l.setBounds(400, 530, 100, 20);
        m.setBounds(510, 530, 100, 20);
        n.setBounds(620, 530, 100, 20);
        o.setBounds(160, 530, 100, 20);
        p.setBounds(730, 530, 100, 20);
        q.setBounds(840, 530, 100, 20);
        r.setBounds(950, 530, 100, 20);
        s.setBounds(290, 560, 100, 20);
        t.setBounds(400, 560, 100, 20);
        u.setBounds(50, 560, 100, 20);
        v.setBounds(510, 560, 100, 20);
        w.setBounds(620, 560, 100, 20);
        x.setBounds(730, 560, 100, 20);
        y.setBounds(840, 560, 100, 20);
        z.setBounds(950, 560, 100, 20);

        //adding action listeners
        a.addActionListener(this);
        b.addActionListener(this);
        c.addActionListener(this);
        d.addActionListener(this);
        e.addActionListener(this);
        f.addActionListener(this);
        g.addActionListener(this);
        h.addActionListener(this);
        i.addActionListener(this);
        j.addActionListener(this);
        k.addActionListener(this);
        l.addActionListener(this);
        m.addActionListener(this);
        n.addActionListener(this);
        o.addActionListener(this);
        p.addActionListener(this);
        q.addActionListener(this);
        r.addActionListener(this);
        s.addActionListener(this);
        t.addActionListener(this);
        u.addActionListener(this);
        v.addActionListener(this);
        w.addActionListener(this);
        x.addActionListener(this);
        y.addActionListener(this);
        z.addActionListener(this);

        frame.setSize(1920, 1080);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.add(buyVowel);
        frame.add(spinButton);
        frame.add(guessPuzzle);
        frame.add(labelCategory);
        frame.add(category);

        frame.add(a);
        frame.add(e);

        frame.add(i);
        frame.add(o);
        frame.add(u);

        frame.add(b);
        frame.add(c);
        frame.add(d);
        frame.add(f);
        frame.add(g);
        frame.add(h);
        frame.add(j);
        frame.add(k);
        frame.add(l);
        frame.add(m);
        frame.add(n);
        frame.add(p);
        frame.add(q);
        frame.add(r);
        frame.add(s);
        frame.add(t);
        frame.add(v);
        frame.add(w);
        frame.add(x);
        frame.add(y);
        frame.add(z);

        labelCategory.setVisible(true);
        labelCategory.setBounds(1125, 450, 350, 40);
        labelCategory.setFont(new Font(null, Font.BOLD, 30));
        labelCategory.setHorizontalAlignment(SwingConstants.CENTER);
        labelCategory.setVerticalAlignment(SwingConstants.CENTER);

        category.setVisible(true);
        category.setBounds(1125, 500, 350, 80);
        category.setFont(new Font(null, Font.BOLD, 20));
        category.setText("Category");
        category.setHorizontalAlignment(SwingConstants.CENTER);
        category.setVerticalAlignment(SwingConstants.CENTER);
        category.setOpaque(true);
        category.setBackground(Color.BLUE);
        category.setForeground(Color.WHITE);

        player1.setVisible(true);
        player2.setVisible(true);
        round.setVisible(true);
        total.setVisible(true);
        player1Round.setVisible(true);
        player1Total.setVisible(true);
        player2Round.setVisible(true);
        player2Total.setVisible(true);

        board.setVisible(true);
        board.setEditable(false);
        board.setBounds(50, 600, 1000, 160);
        board.setFont(new Font(null, Font.BOLD, 40));

        frame.add(player1);
        frame.add(player2);
        frame.add(round);
        frame.add(total);
        frame.add(player1Round);
        frame.add(player1Total);
        frame.add(player2Round);
        frame.add(player2Total);
        frame.add(board);

        player1.setBounds(1125, 150, 100, 40);
        player1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        player1.setHorizontalAlignment(SwingConstants.CENTER);
        player1.setVerticalAlignment(SwingConstants.CENTER);

        player2.setBounds(1125, 210, 100, 40);
        player2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        player2.setHorizontalAlignment(SwingConstants.CENTER);
        player2.setVerticalAlignment(SwingConstants.CENTER);

        round.setBounds(1250, 100, 100, 40);
        round.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        round.setHorizontalAlignment(SwingConstants.CENTER);
        round.setVerticalAlignment(SwingConstants.CENTER);

        total.setBounds(1375, 100, 100, 40);
        total.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        total.setHorizontalAlignment(SwingConstants.CENTER);
        total.setVerticalAlignment(SwingConstants.CENTER);

        player1Round.setBounds(1250, 150, 100, 40);
        player1Round.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        player1Round.setHorizontalAlignment(SwingConstants.CENTER);
        player1Round.setVerticalAlignment(SwingConstants.CENTER);

        player1Total.setBounds(1375, 150, 100, 40);
        player1Total.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        player1Total.setHorizontalAlignment(SwingConstants.CENTER);
        player1Total.setVerticalAlignment(SwingConstants.CENTER);

        player2Round.setBounds(1250, 210, 100, 40);
        player2Round.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        player2Round.setHorizontalAlignment(SwingConstants.CENTER);
        player2Round.setVerticalAlignment(SwingConstants.CENTER);

        player2Total.setBounds(1375, 210, 100, 40);
        player2Total.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        player2Total.setHorizontalAlignment(SwingConstants.CENTER);
        player2Total.setVerticalAlignment(SwingConstants.CENTER);

        buyVowelHandler vowelHandler = new buyVowelHandler();
        buyVowel.addActionListener(vowelHandler);

        spinButtonHandler spinHandler = new spinButtonHandler();
        spinButton.addActionListener(spinHandler);

        guessButtonHandler guessHandler = new guessButtonHandler();
        guessPuzzle.addActionListener(guessHandler);

        buyVowel.setEnabled(true);
        spinButton.setEnabled(true);
        guessPuzzle.setEnabled(true);

        //header above the wedgepanel
        frame.add(header);
        header.setVisible(true);
        header.setBounds(475, 10, 200, 100);
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setVerticalAlignment(SwingConstants.CENTER);
        header.setFont(new Font(null, Font.BOLD, 25));
        header.setText("");

        //to display wedge
        frame.add(wedgePanel);
        wedgePanel.setVisible(true);
        wedgePanel.setOpaque(true);
        wedgePanel.setBounds(320, 100, 500, 200);
        wedgePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        wedgePanel.setHorizontalAlignment(SwingConstants.CENTER);
        wedgePanel.setVerticalAlignment(SwingConstants.CENTER);
        wedgePanel.setFont(new Font(null, Font.BOLD, 50));

        startRound();

    }

    class spinButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String wedge = getWedge();
            if (wedge.equals("Lose A Turn")) {
                switchPlayer();
                return;
            }
            if (wedge.equals("Bankrupt")) {
                //check whos turn it is and set their round score to 0
                if (isPlayer1Turn) {
                    p1.setRoundTotal(0);
                    player1Round.setText("0");
                } else {
                    p2.setRoundTotal(0);
                    player2Round.setText("0");
                }
                switchPlayer();
                return;
            }
            wedgeValue = Integer.valueOf(wedge);

            //must disable Buy a Vowel and Guess the Puzzle
            buyVowel.setEnabled(false);
            guessPuzzle.setEnabled(false);

            if (!usedConsonants.contains("B"))
                b.setEnabled(true);
            if (!usedConsonants.contains("C"))
                c.setEnabled(true);
            if (!usedConsonants.contains("D"))
                d.setEnabled(true);
            if (!usedConsonants.contains("F"))
                f.setEnabled(true);
            if (!usedConsonants.contains("G"))
                g.setEnabled(true);
            if (!usedConsonants.contains("H"))
                h.setEnabled(true);
            if (!usedConsonants.contains("J"))
                j.setEnabled(true);
            if (!usedConsonants.contains("K"))
                k.setEnabled(true);
            if (!usedConsonants.contains("L"))
                l.setEnabled(true);
            if (!usedConsonants.contains("M"))
                m.setEnabled(true);
            if (!usedConsonants.contains("N"))
                n.setEnabled(true);
            if (!usedConsonants.contains("P"))
                p.setEnabled(true);
            if (!usedConsonants.contains("Q"))
                q.setEnabled(true);
            if (!usedConsonants.contains("R"))
                r.setEnabled(true);
            if (!usedConsonants.contains("S"))
                s.setEnabled(true);
            if (!usedConsonants.contains("T"))
                t.setEnabled(true);
            if (!usedConsonants.contains("V"))
                v.setEnabled(true);
            if (!usedConsonants.contains("W"))
                w.setEnabled(true);
            if (!usedConsonants.contains("X"))
                x.setEnabled(true);
            if (!usedConsonants.contains("Y"))
                y.setEnabled(true);
            if (!usedConsonants.contains("Z"))
                z.setEnabled(true);
        }
    }

    class buyVowelHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            //must disable spin and guess puzzle
            spinButton.setEnabled(false);
            guessPuzzle.setEnabled(false);

            //check if current player has enough money
            if (isPlayer1Turn) {
                if (p1.getRoundTotal() < 250) {
                    JOptionPane.showMessageDialog(null, "Sorry you don't have sufficent funds!", "", JOptionPane.INFORMATION_MESSAGE);
                    if (usedVowels.contains("AEIOU"))
                        buyVowel.setEnabled(false);
                    else
                        buyVowel.setEnabled(true);

                    if (usedConsonants.contains("BCDFGHJKLMNPQRSTVWXYZ"))
                        spinButton.setEnabled(false);
                    else
                        spinButton.setEnabled(true);

                    guessPuzzle.setEnabled(true);
                    return;
                }
            } else {
                if (p2.getRoundTotal() < 250) {
                    JOptionPane.showMessageDialog(null, "Sorry you don't have sufficent funds!", "", JOptionPane.INFORMATION_MESSAGE);
                    if (usedVowels.contains("AEIOU"))
                        buyVowel.setEnabled(false);
                    else
                        buyVowel.setEnabled(true);

                    if (usedConsonants.contains("BCDFGHJKLMNPQRSTVWXYZ"))
                        spinButton.setEnabled(false);
                    else
                        spinButton.setEnabled(true);

                    guessPuzzle.setEnabled(true);
                    return;
                }
            }

            if (!usedVowels.contains("A"))
                a.setEnabled(true);
            if (!usedVowels.contains("E"))
                e.setEnabled(true);
            if (!usedVowels.contains("I"))
                i.setEnabled(true);
            if (!usedVowels.contains("O"))
                o.setEnabled(true);
            if (!usedVowels.contains("U"))
                u.setEnabled(true);
        }
    }

    class guessButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {

            String solve = JOptionPane.showInputDialog(enterSolve, "Solve the Puzzle", null);
            if (solve.equalsIgnoreCase(gameBoardPhrase)) {
                //update totals
                p1.setTotalScore(p1.getTotalScore() + p1.getRoundTotal());
                player1Total.setText(String.valueOf(p1.getTotalScore()));
                p2.setTotalScore(p2.getTotalScore() + p2.getRoundTotal());
                player2Total.setText(String.valueOf(p2.getTotalScore()));

                //clear background
                disableAll();
                buyVowel.setEnabled(false);
                spinButton.setEnabled(false);
                guessPuzzle.setEnabled(false);
                wedgePanel.setText("");
                wedgePanel.setBackground(Color.WHITE);
                board.setText("");
                category.setText("");
                player1Round.setText("");
                player2Round.setText("");

                JOptionPane.showMessageDialog(null, "Congratulations you solved the puzzle!" + "\n"
                        + "Phrase: " + gameBoardPhrase + "\nNEXT ROUND!", "", JOptionPane.INFORMATION_MESSAGE);

                reset();

                //increase the number of rounds
                numRounds++;

                //start a new game
                if (numRounds != 4)
                    startRound();
                else {
                    disableAll();
                    buyVowel.setEnabled(false);
                    spinButton.setEnabled(false);
                    guessPuzzle.setEnabled(false);
                    wedgePanel.setText("");
                    wedgePanel.setBackground(Color.WHITE);
                    board.setText("");
                    category.setText("");
                    player1Round.setText("");
                    player2Round.setText("");
                    startBonusRound();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Sorry you are incorrect!", "", JOptionPane.INFORMATION_MESSAGE);
                switchPlayer();

                //check to enable or disable
                if (usedVowels.contains("AEIOU"))
                    buyVowel.setEnabled(false);
                else
                    buyVowel.setEnabled(true);

                if (usedConsonants.contains("BCDFGHJKLMNPQRSTVWXYZ"))
                    spinButton.setEnabled(false);
                else
                    spinButton.setEnabled(true);
            }

        }

    }

    //disable all letters
    public void disableAll() {
        a.setEnabled(false);
        e.setEnabled(false);
        i.setEnabled(false);
        o.setEnabled(false);
        u.setEnabled(false);
        b.setEnabled(false);
        c.setEnabled(false);
        d.setEnabled(false);
        f.setEnabled(false);
        g.setEnabled(false);
        h.setEnabled(false);
        j.setEnabled(false);
        k.setEnabled(false);
        l.setEnabled(false);
        m.setEnabled(false);
        n.setEnabled(false);
        p.setEnabled(false);
        q.setEnabled(false);
        r.setEnabled(false);
        s.setEnabled(false);
        t.setEnabled(false);
        v.setEnabled(false);
        w.setEnabled(false);
        x.setEnabled(false);
        y.setEnabled(false);
        z.setEnabled(false);
    }

    //reset to prepare for the next round
    public void reset() {
        stateOfPhrase = "";
        gameBoardPhrase = "";
        letter = "";
        usedVowels = "";
        usedConsonants = "";
    }

    //returns true if it is player 1's turn
    //returns false if it is player 2's turn
    public boolean whosTurn() {
        player1.setOpaque(true);
        player2.setOpaque(true);

        if (isPlayer1Turn) {
            player1.setBackground(Color.RED);
            player2.setBackground(Color.WHITE);
            return true;
        } else {
            player1.setBackground(Color.WHITE);
            player2.setBackground(Color.RED);
            return false;
        }
    }

    //player 1's turn ends, and player 2's turn begins OR
    //player 2's turn ends, and player 1's turn begins
    public void switchPlayer() {
        player1.setOpaque(true);
        player2.setOpaque(true);

        if (isPlayer1Turn) {
            isPlayer1Turn = false;
            player1.setBackground(Color.WHITE);
            player2.setBackground(Color.RED);
        } else {
            isPlayer1Turn = true;
            player1.setBackground(Color.RED);
            player2.setBackground(Color.WHITE);
        }
    }

    public void startRound() {
        //reset each player's round score
        p1.setRoundTotal(0);
        player1Round.setText(String.valueOf(p1.getRoundTotal()));
        p2.setRoundTotal(0);
        player2Round.setText(String.valueOf(p2.getRoundTotal()));

        //player is randomly chosen
        int r = rand.nextInt(2);
        if(r == 1)
            isPlayer1Turn = true;
        else
            isPlayer1Turn = false;

        whosTurn();

        //disable all letters
        disableAll();

        //enable all options
        buyVowel.setEnabled(true);
        spinButton.setEnabled(true);
        guessPuzzle.setEnabled(true);

        //drawing phrase board
        drawBoard();
    }


    //will update the state of the phrase(i.e. board)
    //will update the number of times the guessed letter or vowel was
    //found in the phrase to be guessed
    public void fill(String letter) {
        StringBuilder s = new StringBuilder(stateOfPhrase);
        for (int i = 0; i < stateOfPhrase.length(); i++) {
            if (s.charAt(i) == '-') {
                if (String.valueOf(gameBoardPhrase.charAt(i)).equals(letter)) {
                    numCorrect++;
                    s.setCharAt(i, letter.charAt(0));
                }
            }
        }
        stateOfPhrase = s.toString();
    }

    //returns a random game board wedge
    public String getWedge() {
        int randomNumber = rand.nextInt(wedges.length);
        String wedge = wedges[randomNumber];
        header.setText("You spun:");
        wedgePanel.setText(wedge);

        //color panel random color for effect
        wedgePanel.setBackground(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
        return wedges[randomNumber];
    }

    //draws board for a game round
    public void drawBoard() {
        String gameCategory = Categories.getCategory();
        category.setText(gameCategory);

        String gamePhrase = Categories.getPhrase(gameCategory);
        gameBoardPhrase = gamePhrase;
        board.setColumns(gameBoardPhrase.length());

        String fill = "";
        for (int i = 0; i < gameBoardPhrase.length(); i++) {
            if (gamePhrase.charAt(i) == ' ') {
                fill = fill + " ";
            } else {
                fill = fill + "-";
            }
        }

        board.setText(fill);
        board.setHorizontalAlignment(SwingConstants.CENTER);
        board.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //board is currently empty
        stateOfPhrase = fill;
    }

    //checks for valid input for the bonus round
    public boolean checkBonusInput(String input) {
        if (input.length() != 4)
            return false;
        if (input.contains("R") || input.contains("S") || input.contains("T") ||
                input.contains("L") || input.contains("N") || input.contains("E"))
            return false;

        //check unique characters
        Set testSet = new HashSet();
        for (int i = 0; i < input.length(); i++) {
            testSet.add(input.charAt(i));
        }
        if (testSet.size() != 4)
            return false;

        int numConsonants = 0;
        int numVowels = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isLetter(c)) {
                if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
                    numVowels++;
                else
                    numConsonants++;
            }
        }

        if (numConsonants == 3 && numVowels == 1)
            return true;
        else
            return false;
    }


    public void startBonusRound() {
        //check who won
        boolean isPlayer1 = false;
        int total = 0;

        //set reward
        int reward = rand.nextInt(50000) + 10000;

        if (p1.getTotalScore() > p2.getTotalScore()) {
            isPlayer1 = true;
            total = p1.getTotalScore();
            JOptionPane.showMessageDialog(null, "Player 1 Score: " + p1.getTotalScore() +
                            "\nPlayer 2 Score: " + p2.getTotalScore() + "\nPlayer 1 WINS!\n\n" +
                            "Player 1 moves on to the bonus round...",
                    "End Of The Main Game", JOptionPane.INFORMATION_MESSAGE);
        }
        if (p2.getTotalScore() > p1.getTotalScore()) {
            total = p2.getTotalScore();
            JOptionPane.showMessageDialog(null, "Player 1 Score: " + p1.getTotalScore() +
                            "\nPlayer 2 Score: " + p2.getTotalScore() + "\nPlayer 2 WINS!\n\n" +
                            "Player 2 moves on to the bonus round...",
                    "End Of The Main Game", JOptionPane.INFORMATION_MESSAGE);
        }
        if (p1.getTotalScore() == p2.getTotalScore()) {
            //random breaking of the tie, if the number is 1 player 1 moves on to the bonus round, else
            //if the number is 0 player moves on to the bonus round
            int number = rand.nextInt(2);
            if (number == 1)
                isPlayer1 = true;
            else
                isPlayer1 = false;

            if (isPlayer1) {
                total = p1.getTotalScore();
                JOptionPane.showMessageDialog(null, "Player 1 Score: " + p1.getTotalScore() +
                                "\nPlayer 2 Score: " + p2.getTotalScore() + "\nTossing a coin...\n\n" +
                                "Player 1 moves on to the bonus round...",
                        "End Of The Main Game", JOptionPane.INFORMATION_MESSAGE);
            } else {
                total = p2.getTotalScore();
                JOptionPane.showMessageDialog(null, "Player 1 Score: " + p1.getTotalScore() +
                                "\nPlayer 2 Score: " + p2.getTotalScore() + "\nTossing a coin...\n\n" +
                                "Player 2 moves on to the bonus round...",
                        "End Of The Main Game", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        //getting bonus phrase
        Set testSet = new HashSet();
        String category1 = "";
        String category2 = "";
        String category3 = "";
        do {
            testSet.clear();
            category1 = Categories.getCategory();
            category2 = Categories.getCategory();
            category3 = Categories.getCategory();
            testSet.add(category1);
            testSet.add(category2);
            testSet.add(category3);
        }while(testSet.size() != 3);

        //get chosen category from the player
        String choice;
        do {
            choice = JOptionPane.showInputDialog(null, "Choose A Category:"
                            + "\n\n" + "1 - " + category1 + "\n2 - " + category2 + "\n3 - " + category3
                            + "\n\nEnter 1 or 2 or 3",
                    "Bonus Guess", JOptionPane.INFORMATION_MESSAGE);
        }while(!choice.equals("1") && !choice.equals("2") && !choice.equals("3"));

        String category = choice;
        if(category.equals("1"))
            category = category1;
        if(category.equals("2"))
            category = category2;
        if(category.equals("3"))
            category = category3;

        String phrase = Categories.getPhrase(category);
        String stateOfPhrase = "";
        String input = "";

        //filling in letters R, S, T, L, N, E
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == 'R' || phrase.charAt(i) == 'S' || phrase.charAt(i) == 'T' ||
                    phrase.charAt(i) == 'L' || phrase.charAt(i) == 'N' || phrase.charAt(i) == 'E')
                stateOfPhrase += String.valueOf(phrase.charAt(i));
            else if (phrase.charAt(i) == ' ')
                stateOfPhrase += " ";
            else
                stateOfPhrase += "-";
        }

        do {
            //get guessed letters from player
            input = JOptionPane.showInputDialog(null, "Category: " + category + "\n\n"
                            + stateOfPhrase + "\n\nLetters R, S, " + "T, L, N, and E are already filled in "
                            + "the phrase. Enter 3 consonants and 1 vowel to be " +
                            "filled in the phrase." + "\nDo not include letters R, S, T, L, N, E in the guess."
                            + "\nDo not include spaces in the guess."
                            + "\nDo not repeat letters(letters must be distinct).",
                    "Bonus Guess", JOptionPane.INFORMATION_MESSAGE);

            input = input.toUpperCase();

        } while (!checkBonusInput(input));

        //fill in letters to stateOfPhrase
        for (int i = 0; i < phrase.length(); i++) {
            String s = String.valueOf(phrase.charAt(i));
            if (input.contains(s)) {
                stateOfPhrase = stateOfPhrase.substring(0, i) + s
                        + stateOfPhrase.substring(i + 1);
            }
        }

        //check if the phrase was completely filled
        Object[] options = {"Yes", "No"};
        if (stateOfPhrase.equals(phrase)) {
            int totalEarnings = total + reward;
            JOptionPane.showMessageDialog(null, "YOU GUESSED THE PHRASE: "
                            + phrase + "\nBonus Reward: " + reward + "\nTotal Earnings: " +
                            totalEarnings,
                    "End Game", JOptionPane.INFORMATION_MESSAGE);
            int n = JOptionPane.showOptionDialog(new JFrame(),
                    "Would you like to start a new game?",
                    "Option",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (n == JOptionPane.YES_OPTION) {
                new WheelofFortune();
            } else {
                frame.dispose();
                System.exit(0);
            }
        } else {
            input = JOptionPane.showInputDialog(null, stateOfPhrase + "\n\n"
                            + "Guess the phrase",
                    "Bonus Guess", JOptionPane.INFORMATION_MESSAGE);
            if (input.equalsIgnoreCase(phrase)) {
                int totalEarnings = total + reward;
                JOptionPane.showMessageDialog(null, "YOU GUESSED THE PHRASE: "
                                + phrase + "\nBonus Reward: " + reward + "\nTotal Earnings: " +
                                totalEarnings,
                        "End Game", JOptionPane.INFORMATION_MESSAGE);
                int n = JOptionPane.showOptionDialog(new JFrame(),
                        "Would you like to start a new game?",
                        "Option",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (n == JOptionPane.YES_OPTION) {
                    new WheelofFortune();
                } else {
                    frame.dispose();
                    System.exit(0);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Sorry, the phrase was: "
                        + phrase + "\nBonus Reward: 0" + "\nTotal Earnings: " +
                        total, "End Game", JOptionPane.INFORMATION_MESSAGE);
                int n = JOptionPane.showOptionDialog(new JFrame(),
                        "Would you like to start a new game?",
                        "Option",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (n == JOptionPane.YES_OPTION) {
                    new WheelofFortune();
                } else {
                    frame.dispose();
                    System.exit(0);
                }
            }
        }
    }
}
