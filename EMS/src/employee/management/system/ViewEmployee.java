package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class ViewEmployee extends JFrame implements ActionListener {
    JTable table;
    JTextField empIdField;
    JButton find, print, update, back , view;

    ViewEmployee() {
        getContentPane().setBackground(new Color(218, 171, 255));

        JLabel search = new JLabel("Search by Employee id");
        search.setBounds(20, 20, 150, 20);
        add(search);

        empIdField = new JTextField();
        empIdField.setBounds(180, 20, 150, 20);
        
        empIdField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume();
                }
            }
        });
        
        add(empIdField);

        table = new JTable();
        try {
            conn c = new conn();
            ResultSet allrs = c.statement.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(allrs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JScrollPane jp = new JScrollPane(table);
        jp.setBounds(3, 100, 1260, 560);
        add(jp);

        find = new JButton("S e a r c h");
        find.setBounds(20, 60, 100, 27);
        find.setForeground(Color.magenta);
        find.setBackground(Color.BLACK);
        find.setFont(new Font("Arial", Font.ITALIC, 12));
        find.setCursor(new Cursor(Cursor.HAND_CURSOR));
        find.setFocusPainted(false);
        find.addActionListener(this);
        add(find);

        print = new JButton("P r i n t");
        print.setBounds(150, 60, 100, 27);
        print.setForeground(Color.magenta);
        print.setBackground(Color.BLACK);
        print.setFont(new Font("Arial", Font.ITALIC, 12));
        print.setCursor(new Cursor(Cursor.HAND_CURSOR));
        print.setFocusPainted(false);
        print.addActionListener(this);
        add(print);

        update = new JButton("U p d a t e");
        update.setBounds(280, 60, 100, 27);
        update.setForeground(Color.magenta);
        update.setBackground(Color.BLACK);
        update.setFont(new Font("Arial", Font.ITALIC, 12));
        update.setCursor(new Cursor(Cursor.HAND_CURSOR));
        update.setFocusPainted(false);
        update.addActionListener(this);
        add(update);

        back = new JButton("B a c k");
        back.setBounds(410, 60, 100, 27);
        back.setForeground(Color.magenta);
        back.setBackground(Color.BLACK);
        back.setFont(new Font("Arial", Font.ITALIC, 12));
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.setFocusPainted(false);
        back.addActionListener(this);
        add(back);

        view = new JButton("V i e w");
        view.setBounds(540, 60, 100, 27);
        view.setForeground(Color.magenta);
        view.setBackground(Color.BLACK);
        view.setFont(new Font("Arial", Font.ITALIC, 12));
        view.setCursor(new Cursor(Cursor.HAND_CURSOR));
        view.setFocusPainted(false);
        view.addActionListener(this);
        add(view);




        setSize(1280, 700);
        setLayout(null);
        setLocation(0, 7);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == find){
            String empId = empIdField.getText().trim();
            
            if(empId.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter an Employee ID", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            conn c = null;
            PreparedStatement pstmtCheck = null;
            PreparedStatement pstmtDisplay = null;
            ResultSet rsCheck = null;
            ResultSet rsDisplay = null;
            try {
                c = new conn();
                // First check if the empID exists
                String checkQuery = "SELECT * FROM employee WHERE empID = ?";
                pstmtCheck = c.connection.prepareStatement(checkQuery);
                pstmtCheck.setString(1, empId);
                rsCheck = pstmtCheck.executeQuery();
                
                if(rsCheck.next()) {
                    // If empID exists, execute a separate query for display
                    String displayQuery = "SELECT * FROM employee WHERE empID = ?";
                    pstmtDisplay = c.connection.prepareStatement(displayQuery);
                    pstmtDisplay.setString(1, empId);
                    rsDisplay = pstmtDisplay.executeQuery();
                    table.setModel(DbUtils.resultSetToTableModel(rsDisplay));
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "You have entered a wrong employee id, please enter correct employee id", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    ResultSet allrs = c.statement.executeQuery("select * from employee");
                    table.setModel(DbUtils.resultSetToTableModel(allrs));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Database Error: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    if(rsCheck != null) rsCheck.close();
                    if(rsDisplay != null) rsDisplay.close();
                    if(pstmtCheck != null) pstmtCheck.close();
                    if(pstmtDisplay != null) pstmtDisplay.close();
                    if(c != null && c.connection != null) c.connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }



        else if(e.getSource() == print){
            try {
                table.print();
            } catch (Exception E) {
                E.printStackTrace();
            }
        }


        else if(e.getSource() == update){
            String empId = empIdField.getText().trim();
            
            if(empId.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter an Employee ID", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            conn c = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                c = new conn();
                String query = "SELECT * FROM employee WHERE empID = ?";
                pstmt = c.connection.prepareStatement(query);
                pstmt.setString(1, empId);
                rs = pstmt.executeQuery();
                
                if(rs.next()) {
                    setVisible(false);
                    new UpdateEmployee(empId);
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "You have entered a wrong employee id, please enter correct employee id", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    if(rs != null) rs.close();
                    if(pstmt != null) pstmt.close();
                    if(c != null && c.connection != null) c.connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        else if(e.getSource() == view){
            try {
                conn c = new conn();
                ResultSet allrs = c.statement.executeQuery("select * from employee");
                table.setModel(DbUtils.resultSetToTableModel(allrs));
            } catch (Exception E) {
                E.printStackTrace();
            }
        }
        else{
            new Main_class();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new ViewEmployee();
    }
}