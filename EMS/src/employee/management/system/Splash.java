package employee.management.system;

import java.awt.*;
import javax.swing.*;

public class Splash extends JFrame {
    Splash() {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/emsfront.gif"));
        Image i2 = i1.getImage().getScaledInstance(1280, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 =new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,1280,700);
        add(image);


        setSize(1280, 700);
        setLayout(null);
        setLocation(0, 7);
        setVisible(true);

        try {
            Thread.sleep(9000);
            setVisible(false);
            new Login();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Splash();

    }
}
