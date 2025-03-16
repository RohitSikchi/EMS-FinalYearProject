package employee.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;



class RoundBorder extends AbstractBorder {
    private int r;
    RoundBorder(int r) { this.r = r; }

    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        ((Graphics2D) g).drawRoundRect(x, y, w - 1, h - 1, r, r);
    }
}

public class Main_class extends JFrame {

    
    Main_class() {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/emshome.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1280, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0, 0, 1280, 700);
        add(img);

        JLabel heading = new JLabel("Employee Management System");
        heading.setBounds(410, 100, 500, 70);
        heading.setFont(new Font("Lucida Handwriting", Font.BOLD, 25));
        heading.setForeground(Color.CYAN);
        heading.setOpaque(true);
        heading.setBackground(Color.DARK_GRAY);
        heading.setBorder(new EmptyBorder(30, 20, 30, 20));
        img.add(heading);

        JButton add = new JButton("Add Employee");
        add.setBounds(150, 320, 150, 40);
        add.setForeground(Color.RED);
        add.setBackground(Color.BLACK);
        add.setFont(new Font("Arial", Font.ITALIC, 14));
        add.setFocusPainted(false);
        add.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add.setBorder(new RoundBorder(20));

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //add employee code after button is clicked
                new AddEmployee();
                setVisible(false);
    
            }
        });

        img.add(add);

        JButton view = new JButton("View Employee");
        view.setBounds(550, 320, 150, 40);
        view.setForeground(Color.RED);
        view.setBackground(Color.BLACK);
        view.setFont(new Font("Arial", Font.ITALIC, 14));
        view.setFocusPainted(false);
        view.setCursor(new Cursor(Cursor.HAND_CURSOR));
        view.setBorder(new RoundBorder(20));

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //view employee code after button is clicked
                new ViewEmployee();
                setVisible(false);
            }
        });

        img.add(view);

        JButton rem = new JButton("Remove Employee");
        rem.setBounds(950, 320, 160, 40);
        rem.setForeground(Color.RED);
        rem.setBackground(Color.BLACK);
        rem.setFont(new Font("Arial", Font.ITALIC, 14));
        rem.setFocusPainted(false);
        rem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rem.setBorder(new RoundBorder(20));

        rem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //remove employee code after button is clicked
                new RemoveEmployee();
                setVisible(false);

                
            }
        });
        
        img.add(rem);

        JButton pay = new JButton(" Employee Payroll");
        pay.setBounds(550, 520, 150, 40);
        pay.setForeground(Color.RED);
        pay.setBackground(Color.BLACK);
        pay.setFont(new Font("Arial", Font.ITALIC, 14));
        pay.setFocusPainted(false);
        pay.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pay.setBorder(new RoundBorder(20));

        pay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                // employee paymnent  code after button is clicked
                new PaymentFunction();
                setVisible(false);
            }
        });

        img.add(pay);

        setSize(1280, 700);
        setLocation(0, 7);
        setLayout(null);
        setVisible(true);

    }

    

    public static void main(String[] args) {
        new Main_class();

    }

}
