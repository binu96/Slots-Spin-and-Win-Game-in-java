import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Binu Senevirathne on 12/11/2016.
 */
public class Symbol implements Isymbol {

    //declaring all the global variables
    public int valueImage;
    public int index;
    public int wins = 0;
    public int loses = 0;
    public int winCredits;
    public double avg = 0.0;
    public String imgValue;
    public List<Symbol> list = new ArrayList<Symbol>();
    public String name;
    public String image;
    public String imgSet01;
    public String imgSet02;
    public String imgSet03;
    public boolean stop = false;
    public JLabel image1;
    public JLabel image2;
    public JLabel image3;
    public JTextField txtCredit;
    public JTextField txtBetArea;
    public JTextField txtWin;
    public JTextField txtLose;
    public JTextField txtAverage;
    public JButton jbtnSpin;
    public JFrame mainFrame;

    //default Constructor
    public Symbol() {

    }

    //declared Constructor
    public Symbol(String name, int value, String image) {
        this.name = name;
        this.valueImage = value;
        this.imgValue = image;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getValueImage() {
        return valueImage;
    }

    public void setValueImage(int valueImage) {

        this.valueImage = valueImage;
    }

    public String getImgValue() {

        return imgValue;
    }

    public void setImgValue(String imgValue) {

        this.imgValue = imgValue;
    }

    @Override
    public void setImage() {

        //setting the images to the respective labels
        imgSet01 = getImage();
        ImageIcon setImg01 = new ImageIcon(imgSet01);
        image1.setIcon(setImg01);

        imgSet02 = getImage();
        ImageIcon setImg02 = new ImageIcon(imgSet02);
        image2.setIcon(setImg02);

        imgSet03 = getImage();
        ImageIcon setImg03 = new ImageIcon(imgSet03);
        image3.setIcon(setImg03);


    }

    @Override
    public String getImage() {

        //creating the object of class Reel and getting the image objects
        Reel reel = new Reel();
        list = reel.Spin();

        int range = (5 - 0) + 1;
        index = (int) (Math.random() * range) + 0;

        image = list.get(index).imgValue;

        return image;
    }

    @Override
    public void setValue(int valueIndex) {

        int currentVal = Integer.parseInt(txtCredit.getText());
        int currentBet = Integer.parseInt(txtBetArea.getText());

        int newVal = currentVal + (valueIndex * currentBet);
        txtCredit.setText(Integer.toString(newVal));
        txtBetArea.setText("0");

        winCredits = winCredits + (valueIndex * currentBet);

        if ((winCredits != 0) || (newVal != 0)) {
            avg = (double) winCredits / (double) newVal;
            avg = round(avg, 2);
        } else {
            avg = 0.0;
        }

    }

    @Override
    public int getValue() {

        int value = 0;

        if ((imgSet01.equals(imgSet02)) && (imgSet01.equals(imgSet03))) {

            wins = wins + 1;
            value = list.get(index).valueImage;

            int curBet = Integer.parseInt(txtBetArea.getText());
            int winCredVal = value * curBet;
            JOptionPane.showMessageDialog(mainFrame, "YOU WON!" + " You have gained $ " + winCredVal + " Credits.");

        } else {

            value = 0;
            loses = loses + 1;

            JOptionPane.showMessageDialog(mainFrame, "YOU LOSE!");
        }
        return value;
    }

    @Override
    public void displayGUI() {

        try {
            BufferedImage img = ImageIO.read(new File("C:/Users/Binu Senevirathne/IdeaProjects/Spin and Win/images/firewatch_game-HD.jpg"));

            mainFrame = new JFrame("Spin and Win Game");
            mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel gameControlArea = new JPanel(new BorderLayout(5, 5));
            gameControlArea.setBorder(new EmptyBorder(10, 10, 10, 10));

            // Panel that holds everything
            JPanel contAll = new JPanel(new GridLayout(3, 1, 5, 5));

            contAll.setBackground(Color.WHITE);
            gameControlArea.add(contAll, BorderLayout.CENTER);

            //Panel that holds all the Headings and instructions
            JPanel heading = new JPanel(new GridLayout(6, 1, 5, 5));
            heading.setBorder(BorderFactory.createLineBorder(Color.black));
            contAll.add(heading, BorderLayout.NORTH);// adding to the contAll Panel
            heading.setBackground(Color.WHITE);


            JLabel headText = new JLabel("Welcome to the Spin and Win Game", JLabel.CENTER);
            headText.setForeground(Color.blue);
            headText.setFont(new Font("Jokerman", Font.PLAIN, 35));
            JLabel instructions = new JLabel("  1. You can enter the bettings by Add Coin and Bet Max buttons.");
            JLabel instructions1 = new JLabel("  2. The Maximum bet at a time is $ 3.00.");
            JLabel instructions2 = new JLabel("  3. To stop the spin you can click one of the images.");
            JLabel instructions3 = new JLabel("  4. To check the statistics of your winnings and losings click statistics.");
            JLabel instructions4 = new JLabel("  5. To start the game click spin. Let's Go...");

            heading.setBackground(new Color(175, 238, 238));
            heading.add(headText);
            heading.add(instructions);
            heading.add(instructions1);
            heading.add(instructions2);
            heading.add(instructions3);
            heading.add(instructions4);


            //Middle Panel which holds the images and the buttons
            JPanel container = new JPanel(new GridLayout(1, 2, 5, 5));
            contAll.add(container, BorderLayout.CENTER);

            //To hold the images
            JPanel gameControlArea1 = new JPanel(new GridLayout(1, 3, 5, 5));
            container.add(gameControlArea1, BorderLayout.WEST);
            gameControlArea1.setBackground(Color.WHITE);

            //image 01
            image1 = new JLabel("");
            Dimension d1 = image1.getPreferredSize();
            ImageIcon setImg01 = new ImageIcon("C:/Users/Binu Senevirathne/IdeaProjects/Spin and Win/images/slots.png");
            image1.setIcon(setImg01);


            Dimension big1 = new Dimension(
                    (int) d1.getWidth(),
                    (int) d1.getHeight() * 2);
            image1.setPreferredSize(big1);
            gameControlArea1.add(image1);
            image1.setBorder(BorderFactory.createLineBorder(Color.black));

            //Stop the iteration
            image1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    stopIteration();
                }
            });

            //image 02
            image2 = new JLabel("");
            Dimension d2 = image2.getPreferredSize();

            ImageIcon setImg02 = new ImageIcon("C:/Users/Binu Senevirathne/IdeaProjects/Spin and Win/images/slots.png");
            image2.setIcon(setImg01);

            Dimension big2 = new Dimension(
                    (int) d2.getWidth(),
                    (int) d2.getHeight() * 2);
            image2.setPreferredSize(big2);
            gameControlArea1.add(image2);
            image2.setBorder(BorderFactory.createLineBorder(Color.black));

            //Stop the iteration
            image2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    stopIteration();
                }
            });

            //image 03
            image3 = new JLabel("");
            Dimension d3 = image3.getPreferredSize();
            ImageIcon setImg03 = new ImageIcon("C:/Users/Binu Senevirathne/IdeaProjects/Spin and Win/images/slots.png");
            image3.setIcon(setImg01);

            Dimension big3 = new Dimension(
                    (int) d3.getWidth(),
                    (int) d3.getHeight() * 2);
            image3.setPreferredSize(big3);
            gameControlArea1.add(image3);
            image3.setBorder(BorderFactory.createLineBorder(Color.black));

            //Stop the iteration
            image3.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    stopIteration();
                }
            });

            //Buttons
            JPanel gameControlArea2 = new JPanel(new GridLayout(5, 1, 5, 5));
            gameControlArea2.setBackground(Color.WHITE);

            container.add(gameControlArea2, BorderLayout.EAST);

            //Add Coin Button
            JButton jbtnAddCoin = new JButton("ADD COIN");
            jbtnAddCoin.setBackground(new Color(59, 89, 182));
            jbtnAddCoin.setForeground(Color.WHITE);
            jbtnAddCoin.setFocusPainted(false);
            jbtnAddCoin.setFont(new Font("Jokerman", Font.BOLD, 25));

            jbtnAddCoin.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    int credit = Integer.parseInt(txtCredit.getText()) + 1;
                    txtCredit.setText(Integer.toString(credit));
                }
            });

            //Bet One Buttonn
            JButton jbtnBetOne = new JButton("BET ONE");
            jbtnBetOne.setBackground(new Color(59, 89, 182));
            jbtnBetOne.setForeground(Color.WHITE);
            jbtnBetOne.setFocusPainted(false);
            jbtnBetOne.setFont(new Font("Jokerman", Font.BOLD, 25));

            jbtnBetOne.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jbtnSpin.setEnabled(true);
                    int credit = Integer.parseInt(txtCredit.getText()) - 1;

                    if (credit >= 0) {
                        int bet = Integer.parseInt(txtBetArea.getText()) + 1;
                        txtBetArea.setText(Integer.toString(bet));
                        txtCredit.setText(Integer.toString(credit));

                    } else {
                        JOptionPane.showMessageDialog(mainFrame, "You don't have sufficient credits.", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            //Bet Max Button
            JButton jbtnBetMax = new JButton("BET MAX");
            jbtnBetMax.setBackground(new Color(59, 89, 182));
            jbtnBetMax.setForeground(Color.WHITE);
            jbtnBetMax.setFocusPainted(false);
            jbtnBetMax.setFont(new Font("Jokerman", Font.BOLD, 25));

            jbtnBetMax.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    jbtnSpin.setEnabled(true);

                    int credit = Integer.parseInt(txtCredit.getText()) - 3;

                    if (credit >= 0) {
                        int bet = Integer.parseInt(txtBetArea.getText()) + 3;
                        txtBetArea.setText(Integer.toString(bet));
                        txtCredit.setText(Integer.toString(credit));
                    } else {
                        JOptionPane.showMessageDialog(mainFrame, "You don't have sufficient credits.", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            //Reset Button
            JButton jbtnReset = new JButton("RESET");
            jbtnReset.setBackground(new Color(59, 89, 182));
            jbtnReset.setForeground(Color.WHITE);
            jbtnReset.setFocusPainted(false);
            jbtnReset.setFont(new Font("Jokerman", Font.BOLD, 25));

            jbtnReset.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jbtnSpin.setEnabled(false);

                    int bet = Integer.parseInt(txtBetArea.getText());
                    txtBetArea.setText("0");

                    int credit = Integer.parseInt(txtCredit.getText()) + bet;
                    txtCredit.setText(Integer.toString(credit));
                }
            });

            //Statistics Button
            JButton jbtnStatistics = new JButton("STATISTICS");
            jbtnStatistics.setBackground(new Color(59, 89, 182));
            jbtnStatistics.setForeground(Color.WHITE);
            jbtnStatistics.setFocusPainted(false);
            jbtnStatistics.setFont(new Font("Jokerman", Font.BOLD, 25));

            jbtnStatistics.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    Statistics st = new Statistics();
                    st.Stats(wins, loses, avg);
                }
            });

            //Adding the buttons to the Panel
            gameControlArea2.add(jbtnAddCoin);
            gameControlArea2.add(jbtnBetOne);
            gameControlArea2.add(jbtnBetMax);
            gameControlArea2.add(jbtnReset);
            gameControlArea2.add(jbtnStatistics);

            //having a footer panel
            JPanel footer = new JPanel(new GridLayout(2, 1, 5, 5));
            contAll.add(footer, BorderLayout.SOUTH);

            //gameControlArea3 Panel to hold the text fields
            JPanel gameControlArea3 = new JPanel(new GridLayout(1, 2, 5, 5));
            gameControlArea3.setBackground(Color.LIGHT_GRAY);

            //adding the gameControlArea3 panel to the footer panel
            footer.add(gameControlArea3, BorderLayout.NORTH);
            gameControlArea3.setBorder(BorderFactory.createLineBorder(Color.black));

            txtCredit = new JTextField("10");
            txtCredit.setFont(new Font("Tahoma", Font.BOLD, 55));
            txtCredit.setEnabled(false);//disabling the text field
            JLabel credit = new JLabel("Credit Area", JLabel.CENTER);
            credit.setFont(new Font("Tahoma", Font.BOLD, 25));

            txtBetArea = new JTextField("0");
            txtBetArea.setFont(new Font("Tahoma", Font.BOLD, 55));
            txtBetArea.setEnabled(false);// disabling the text field
            JLabel bet = new JLabel("Bet Area", JLabel.CENTER);
            bet.setFont(new Font("Tahoma", Font.BOLD, 25));

            //adding the content to the panel
            gameControlArea3.add(credit);
            gameControlArea3.add(txtCredit);
            gameControlArea3.add(bet);
            gameControlArea3.add(txtBetArea);

            //new panel to hold the spin button
            JPanel gameControlArea4 = new JPanel(new GridLayout(2, 1, 1, 1));
            footer.add(gameControlArea4, BorderLayout.SOUTH);

            //Spin Button
            jbtnSpin = new JButton("Spin");
            jbtnSpin.setEnabled(false);
            jbtnSpin.setBackground(new Color(59, 89, 182));
            jbtnSpin.setForeground(Color.WHITE);
            jbtnSpin.setFocusPainted(false);
            jbtnSpin.setFont(new Font("Jokerman", Font.BOLD, 30));

            jbtnSpin.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    stop = false;//to stop the iterations

                    //setting a timer to iterate the images
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {

                                @Override
                                public void run() {
                                    if (stop == false) {
                                        setImage(); //calling the setImage method
                                    }
                                }
                            },
                            0, 100 // setting the time intervals
                    );

                }
            });

            JLabel jlb = new JLabel("****************************************************************" +
                    "************************************************************************************" +
                    "************************", JLabel.CENTER);


            gameControlArea4.add(jbtnSpin);
            gameControlArea4.add(jlb);

            //Adding the content to the main frame and adjusting the size of the main frame
            mainFrame.add(gameControlArea);
            mainFrame.pack();
            mainFrame.setVisible(true);

        } catch (IOException e) {

            System.out.println("There is a Input and Output Exception");
        }
    }


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public void stopIteration() {
        jbtnSpin.setEnabled(false);
        stop = true;
        int valueIndex = getValue();
        setValue(valueIndex);

    }
}
