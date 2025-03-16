package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RemoveEmployee extends JFrame implements ActionListener {

    JTextField searchField;
    JButton searchButton, remove, back;
    JLabel textName, textPhone, textEmail, textdes;

    RemoveEmployee() {
        // Existing image code remains same
        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icons/removeicon.jpg"));
        Image i22 = i11.getImage().getScaledInstance(300, 302, Image.SCALE_DEFAULT);
        ImageIcon i33 = new ImageIcon(i22);
        JLabel imgg = new JLabel(i33);
        imgg.setBounds(950, 180, 300, 300);
        add(imgg);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/removeimg.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1280, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0, 0, 1280, 700);
        add(img);

        JLabel heading = new JLabel("Remove   Employee   Detail");
        heading.setBounds(500, 30, 450, 70);
        heading.setFont(new Font("Lucida Handwriting", Font.BOLD, 25));
        img.add(heading);

        // Search Field and Button
        JLabel label = new JLabel("Employee ID");
        label.setBounds(500, 150, 160, 40);
        label.setFont(new Font("Tahoma", Font.BOLD, 16));
        img.add(label);

        searchField = new JTextField();
        searchField.setBounds(670, 160, 150, 30);
        searchField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        // Allow only digits
        searchField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });
        img.add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(830, 160, 100, 30);
        searchButton.setForeground(new Color(218,165,32));
        searchButton.setBackground(Color.BLACK);
        searchButton.setFont(new Font("Arial", Font.ITALIC, 14));
        searchButton.addActionListener(this);
        img.add(searchButton);

        // Employee Details Labels
        JLabel labelName = new JLabel("Employee Name");
        labelName.setBounds(500, 200, 160, 40);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 16));
        img.add(labelName);

        textName = new JLabel();
        textName.setBounds(670, 205, 200, 30);
        textName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        img.add(textName);

        JLabel labelPhone = new JLabel("Phone Number");
        labelPhone.setBounds(500, 250, 180, 40);
        labelPhone.setFont(new Font("Tahoma", Font.BOLD, 16));
        img.add(labelPhone);

        textPhone = new JLabel();
        textPhone.setBounds(670, 255, 200, 30);
        textPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        img.add(textPhone);

        JLabel labelEmail = new JLabel("Email id");
        labelEmail.setBounds(500, 300, 180, 40);
        labelEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
        img.add(labelEmail);

        textEmail = new JLabel();
        textEmail.setBounds(670, 305, 200, 30);
        textEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        img.add(textEmail);

        JLabel labeldes = new JLabel("Designation");
        labeldes.setBounds(500, 350, 180, 40);
        labeldes.setFont(new Font("Tahoma", Font.BOLD, 16));
        img.add(labeldes);

        textdes = new JLabel();
        textdes.setBounds(670, 355, 200, 30);
        textdes.setFont(new Font("Tahoma", Font.PLAIN, 16));
        img.add(textdes);

        // Buttons
        remove = new JButton("Remove");
        remove.setBounds(500,460,150,40);
        remove.setForeground(new Color(218,165,32));
        remove.setBackground(Color.BLACK);
        remove.setFont(new Font("Arial", Font.ITALIC, 16));
        remove.setFocusPainted(false);
        remove.setCursor(new Cursor(Cursor.HAND_CURSOR));
        remove.addActionListener(this);
        remove.setEnabled(false); // Initially disabled until valid search
        img.add(remove);

        back = new JButton("Back");
        back.setBounds(700,460,150,40);
        back.setForeground(new Color(218,165,32));
        back.setBackground(Color.BLACK);
        back.setFont(new Font("Arial", Font.ITALIC, 16));
        back.setFocusPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(this);
        img.add(back);

        setSize(1280, 700);
        setLocation(0, 7);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String empId = searchField.getText().trim();
            
            if (empId.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter Employee ID");
                clearFields();
                return;
            }

            try {
                conn c = new conn();
                ResultSet rs = c.statement.executeQuery(
                    "SELECT * FROM employee WHERE empID = '" + empId + "'"
                );
                
                if (rs.next()) {
                    textName.setText(rs.getString("name"));
                    textPhone.setText(rs.getString("phone"));
                    textEmail.setText(rs.getString("email"));
                    textdes.setText(rs.getString("designation"));
                    remove.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "Invalid Employee ID, please enter correct Employee ID");
                    clearFields();
                    remove.setEnabled(false);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        if (e.getSource() == remove) {
            try {
                conn c = new conn();
                String query = "DELETE FROM employee WHERE empID = '" + searchField.getText() + "'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Employee Removed Successfully");
                setVisible(false);
                new Main_class();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        if (e.getSource() == back) {
            setVisible(false);
            new Main_class();
        }
    }

    private void clearFields() {
        textName.setText("");
        textPhone.setText("");
        textEmail.setText("");
        textdes.setText("");
    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }
}