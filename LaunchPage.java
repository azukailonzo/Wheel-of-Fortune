import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchPage extends JFrame implements ActionListener {

    JFrame frame = new JFrame();
    JButton myButton = new JButton("Start");
    JButton myButton2 = new JButton("Rules");

    LaunchPage() {
        myButton.setBounds(100, 160, 200, 40);
        myButton.setFocusable(false);
        myButton.addActionListener(this);

        myButton2.setBounds(100, 200, 200, 40);
        myButton2.setFocusable(false);
        myButton2.addActionListener(this);

        frame.setTitle("Wheel OF Fortune");
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new JLabel(new ImageIcon("WOF.jpg")));
        frame.add(myButton);
        frame.add(myButton2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(new BorderLayout());
        frame.setLayout(new FlowLayout());
        frame.setLayout(null);
        frame.setVisible(true);
    }

    //action performed opens the window to whatever button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myButton) {
            frame.dispose();
            WheelofFortune wheel = new WheelofFortune();
        }

        if (e.getSource() == myButton2) {
            rules rulez = new rules();
        }
    }
}


