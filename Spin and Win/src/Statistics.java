import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Binu Senevirathne on 12/19/2016.
 */
public class Statistics {

    public int wins;
    public int loses;
    public double average;
    public JTextField txtWin;
    public JTextField txtLose;
    public JTextField txtAverage;

    public void Stats(int wins, int loses, double average) {

        this.wins = wins;
        this.loses = loses;
        this.average = average;

        JFrame stat = new JFrame("Statistics");
        stat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel statPanel = new JPanel(new BorderLayout(5, 5));
        statPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel headArea = new JPanel(new GridLayout(1, 1, 5, 5));
        statPanel.add(headArea, BorderLayout.NORTH);

        JLabel statHeadText = new JLabel("Statistics", JLabel.CENTER);
        statHeadText.setFont(new Font("Jokerman", Font.BOLD, 55));
        headArea.add(statHeadText);

        JPanel statArea1 = new JPanel(new GridLayout(4, 2, 5, 5));
        statPanel.add(statArea1, BorderLayout.CENTER);

        txtWin = new JTextField(Integer.toString(this.wins));
        txtWin.setFont(new Font("Tahoma", Font.BOLD, 55));
        txtWin.setEnabled(false);// disabling the text field
        JLabel lblWin = new JLabel("Number of Wins             :", JLabel.CENTER);
        lblWin.setFont(new Font("Tahoma", Font.BOLD, 25));

        txtLose = new JTextField(Integer.toString(this.loses));
        txtLose.setFont(new Font("Tahoma", Font.BOLD, 55));
        txtLose.setEnabled(false);// disabling the text field
        JLabel lblLose = new JLabel("Number of Loses            :", JLabel.CENTER);
        lblLose.setFont(new Font("Tahoma", Font.BOLD, 25));

        txtAverage = new JTextField(String.valueOf(this.average));
        txtAverage.setFont(new Font("Tahoma", Font.BOLD, 55));
        txtAverage.setEnabled(false);// disabling the text field
        JLabel lblAverage = new JLabel("Average of Win Credits :", JLabel.CENTER);
        lblAverage.setFont(new Font("Tahoma", Font.BOLD, 25));

        JButton jbtnsaveStat = new JButton("Save Statistics");
        jbtnsaveStat.setBackground(new Color(59, 89, 182));
        jbtnsaveStat.setForeground(Color.WHITE);
        jbtnsaveStat.setFocusPainted(false);
        jbtnsaveStat.setFont(new Font("Jokerman", Font.BOLD, 25));

        jbtnsaveStat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveStat(wins, loses, average);
                JOptionPane.showMessageDialog(statArea1, "Save Successful.");
            }
        });

        JButton jbtnCloseStat = new JButton("Close");
        jbtnCloseStat.setBackground(new Color(59, 89, 182));
        jbtnCloseStat.setForeground(Color.WHITE);
        jbtnCloseStat.setFocusPainted(false);
        jbtnCloseStat.setFont(new Font("Jokerman", Font.BOLD, 25));

        jbtnCloseStat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stat.dispose();
            }
        });


        statArea1.add(lblWin);
        statArea1.add(txtWin);
        statArea1.add(lblLose);
        statArea1.add(txtLose);
        statArea1.add(lblAverage);
        statArea1.add(txtAverage);
        statArea1.add(jbtnCloseStat);
        statArea1.add(jbtnsaveStat);

        stat.add(statPanel);

        stat.pack();
        stat.setVisible(true);

    }

    public void saveStat(int wins, int loses, double average) {

        try {

            this.wins = wins;
            this.loses = loses;
            this.average = average;

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
            Date date = new Date();

            String fileName = new SimpleDateFormat("yyyyMMddhhmm'.txt'").format(new Date());

            fileName = "src/Statistics/" + fileName;

            File file = new File(fileName);
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);

            PrintWriter wr = new PrintWriter(file, "UTF-8");
            wr.println("Wins :" + this.wins);
            wr.println("Loses :" + this.loses);
            wr.println("Average :" + this.average);
            wr.close();


        } catch (IOException e) {
            System.out.print("Input Output Exception");
        }

    }

}
