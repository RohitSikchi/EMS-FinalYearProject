package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DecimalFormat;

public class PaymentFunction extends JFrame {
    
    private JComboBox<String> employeeComboBox;
    private JTextField amountField, daysField;
    private JButton payButton, historyButton;
    private JLabel statusLabel;
    private conn dbConnection;
    private JLabel phoneLabel, aadharLabel, salaryLabel, perDaySalaryLabel;

    public PaymentFunction() {
        dbConnection = new conn();
        
        setTitle("Employee Payment System");
        setSize(1280, 650);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        getContentPane().setBackground(new Color(51, 1, 94));

        JLabel titleLabel = new JLabel("E m p l o y e e     P a y m e n t");
        titleLabel.setBounds(440, 20, 400, 30);
        titleLabel.setFont(new Font("Lucida Handwriting", Font.BOLD, 23));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icons/payment.png"));
        Image i22 = i11.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
        ImageIcon i33 = new ImageIcon(i22);
        JLabel imgg = new JLabel(i33);
        imgg.setBounds(800, 80, 450, 450);
        add(imgg);

        JLabel empLabel = new JLabel("Select Employee:");
        empLabel.setBounds(50, 80, 150, 30);
        empLabel.setForeground(Color.WHITE);
        empLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(empLabel);

        employeeComboBox = new JComboBox<>();
        employeeComboBox.setBounds(200, 80, 400, 30);
        employeeComboBox.setBackground(new Color(138, 5, 255));
        employeeComboBox.setForeground(Color.WHITE);
        employeeComboBox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        add(employeeComboBox);

        phoneLabel = new JLabel("Phone Number : ");
        phoneLabel.setBounds(50, 130, 600, 30);
        phoneLabel.setForeground(Color.WHITE);
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(phoneLabel);

        aadharLabel = new JLabel("Aadhar Number : ");
        aadharLabel.setBounds(50, 180, 600, 30);
        aadharLabel.setForeground(Color.WHITE);
        aadharLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(aadharLabel);

        salaryLabel = new JLabel("Monthly Salary: ");
        salaryLabel.setBounds(50, 230, 600, 30);
        salaryLabel.setForeground(Color.WHITE);
        salaryLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(salaryLabel);

        perDaySalaryLabel = new JLabel("Per Day Salary: ");
        perDaySalaryLabel.setBounds(50, 280, 600, 30);
        perDaySalaryLabel.setForeground(Color.WHITE);
        perDaySalaryLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(perDaySalaryLabel);

        JLabel daysLabel = new JLabel("Working Days:");
        daysLabel.setBounds(50, 330, 150, 30);
        daysLabel.setForeground(Color.WHITE);
        daysLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(daysLabel);

        daysField = new JTextField();
        daysField.setBounds(200, 330, 400, 30);
        daysField.setBackground(new Color(138, 5, 255));
        daysField.setForeground(Color.WHITE);
        daysField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        add(daysField);

        JLabel amountLabel = new JLabel("Payment Amount:");
        amountLabel.setBounds(50, 380, 150, 30);
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(200, 380, 400, 30);
        amountField.setEditable(false);
        amountField.setBackground(new Color(138, 5, 255));
        amountField.setForeground(Color.WHITE);
        amountField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        add(amountField);

        payButton = new JButton("Process Payment");
        payButton.setBounds(200, 440, 200, 40);
        payButton.setBackground(new Color(138, 5, 255));
        payButton.setForeground(Color.WHITE);
        payButton.setFont(new Font("Arial", Font.BOLD, 16));
        payButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        add(payButton);

        historyButton = new JButton("Payment History");
        historyButton.setBounds(450, 440, 200, 40);
        historyButton.setBackground(new Color(138, 5, 255));
        historyButton.setForeground(Color.WHITE);
        historyButton.setFont(new Font("Arial", Font.BOLD, 16));
        historyButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        add(historyButton);

        statusLabel = new JLabel("");
        statusLabel.setBounds(50, 500, 1180, 30);
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(statusLabel);

        loadEmployees();

        employeeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEmployeeDetails();
            }
        });

        daysField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                calculatePayment();
            }
        });

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processPayment();
            }
        });

        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PaymentHistory(dbConnection);
            }
        });

        setVisible(true);
    }

    private void loadEmployees() {
        try {
            String query = "SELECT empID, name FROM employee";
            ResultSet rs = dbConnection.statement.executeQuery(query);
            while (rs.next()) {
                String empDetails = rs.getString("empID") + " - " + rs.getString("name");
                employeeComboBox.addItem(empDetails);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            statusLabel.setText("Error loading employees: " + e.getMessage());
            statusLabel.setForeground(Color.RED);
        }
    }

    private void showEmployeeDetails() {
        String selectedEmp = (String) employeeComboBox.getSelectedItem();
        if (selectedEmp != null) {
            String empId = selectedEmp.split(" - ")[0];
            try {
                String query = "SELECT phone, aadhar, salary FROM employee WHERE empID = '" + empId + "'";
                ResultSet rs = dbConnection.statement.executeQuery(query);
                if (rs.next()) {
                    // Add spaces (e.g., 10 spaces) between label and value
                    phoneLabel.setText("Phone Number :          " + rs.getString("phone"));
                    aadharLabel.setText("Aadhar Number :        " + rs.getString("aadhar"));
                    double monthlySalary = rs.getDouble("salary");
                    salaryLabel.setText("Monthly Salary :          " + "$" + monthlySalary);
                    DecimalFormat df = new DecimalFormat("#.##");
                    double perDaySalary = monthlySalary / 30;
                    perDaySalaryLabel.setText("Per Day Salary :          " + "$" + df.format(perDaySalary));
                    calculatePayment();
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                statusLabel.setText("Error loading employee details: " + e.getMessage());
                statusLabel.setForeground(Color.RED);
            }
        }
    }

    private void calculatePayment() {
        try {
            String daysText = daysField.getText().trim();
            if (!daysText.isEmpty()) {
                int days = Integer.parseInt(daysText);
                if (days < 0 || days > 31) {
                    statusLabel.setText("Please enter valid working days (0-31)");
                    statusLabel.setForeground(Color.RED);
                    amountField.setText("");
                    return;
                }
                String selectedEmp = (String) employeeComboBox.getSelectedItem();
                if (selectedEmp != null) {
                    String empId = selectedEmp.split(" - ")[0];
                    String query = "SELECT salary FROM employee WHERE empID = '" + empId + "'";
                    ResultSet rs = dbConnection.statement.executeQuery(query);
                    if (rs.next()) {
                        double monthlySalary = rs.getDouble("salary");
                        double perDaySalary = monthlySalary / 30;
                        double totalPayment = perDaySalary * days;
                        DecimalFormat df = new DecimalFormat("#.##");
                        amountField.setText(df.format(totalPayment));
                    }
                    rs.close();
                }
            } else {
                amountField.setText("");
            }
            statusLabel.setText("");
            statusLabel.setForeground(Color.WHITE);
        } catch (NumberFormatException e) {
            statusLabel.setText("Please enter a valid number of days");
            statusLabel.setForeground(Color.RED);
            amountField.setText("");
        } catch (SQLException e) {
            statusLabel.setText("Database error: " + e.getMessage());
            statusLabel.setForeground(Color.RED);
            e.printStackTrace();
        }
    }

    private void processPayment() {
        try {
            String selectedEmp = (String) employeeComboBox.getSelectedItem();
            if (selectedEmp == null || daysField.getText().trim().isEmpty()) {
                statusLabel.setText("Please select an employee and enter working days");
                statusLabel.setForeground(Color.RED);
                return;
            }

            String empId = selectedEmp.split(" - ")[0];
            int days = Integer.parseInt(daysField.getText().trim());
            double amount = Double.parseDouble(amountField.getText().trim());
            
            if (amount <= 0 || days <= 0) {
                statusLabel.setText("Please enter valid days and amount");
                statusLabel.setForeground(Color.RED);
                return;
            }

            String salaryQuery = "SELECT salary FROM employee WHERE empID = '" + empId + "'";
            ResultSet rs = dbConnection.statement.executeQuery(salaryQuery);
            if (rs.next()) {
                double employeeSalary = rs.getDouble("salary");
                if (amount > employeeSalary) {
                    int confirm = JOptionPane.showConfirmDialog(this, 
                        "Payment amount exceeds monthly salary. Continue?",
                        "Warning", JOptionPane.YES_NO_OPTION);
                    if (confirm != JOptionPane.YES_OPTION) {
                        rs.close();
                        return;
                    }
                }
            }
            rs.close();

            String insertQuery = "INSERT INTO payments (empID, amount) VALUES ('" + empId + "', " + amount + ")";
            dbConnection.statement.executeUpdate(insertQuery);

            statusLabel.setText("Payment of $" + amount + " processed successfully for employee " + empId + " for " + days + " days");
            statusLabel.setForeground(Color.GREEN);
            daysField.setText("");
            amountField.setText("");

        } catch (NumberFormatException e) {
            statusLabel.setText("Please enter valid numbers");
            statusLabel.setForeground(Color.RED);
        } catch (SQLException e) {
            statusLabel.setText("Database error: " + e.getMessage());
            statusLabel.setForeground(Color.RED);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PaymentFunction();
            }
        });
    }
}