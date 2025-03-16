package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class PaymentHistory extends JFrame {
    
    private conn dbConnection;
    private DefaultTableModel model;
    private JTextField searchField;
    private JButton searchButton;

    public PaymentHistory(conn dbConnection) {
        this.dbConnection = dbConnection;
        
        setTitle("Payment History");
        setSize(600, 450); // Increased height for search components
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Search panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        
        JLabel searchLabel = new JLabel("Search by Employee ID:");
        searchField = new JTextField(15);
        searchButton = new JButton("Search");
        
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        
        add(searchPanel, BorderLayout.NORTH);

        // Table setup
        String[] columnNames = {"Payment ID", "Employee ID", "Amount", "Date"};
        model = new DefaultTableModel(columnNames, 0);
        JTable historyTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(historyTable);
        add(scrollPane, BorderLayout.CENTER);

        // Load all payment history initially
        loadPaymentHistory("");

        // Search button action
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText().trim();
                loadPaymentHistory(searchTerm);
            }
        });

        // Add Enter key support for search
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText().trim();
                loadPaymentHistory(searchTerm);
            }
        });

        setVisible(true);
    }

    private void loadPaymentHistory(String empIdFilter) {
        try {
            // Clear existing rows
            model.setRowCount(0);
            
            String query;
            if (empIdFilter.isEmpty()) {
                query = "SELECT payment_id, empID, amount, payment_date FROM payments ORDER BY payment_date DESC";
            } else {
                query = "SELECT payment_id, empID, amount, payment_date FROM payments " +
                        "WHERE empID = '" + empIdFilter + "' ORDER BY payment_date DESC";
            }
            
            ResultSet rs = dbConnection.statement.executeQuery(query);
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("payment_id"),
                    rs.getString("empID"),
                    "$" + rs.getDouble("amount"),
                    rs.getTimestamp("payment_date").toString()
                };
                model.addRow(row);
            }
            rs.close();
            
            if (model.getRowCount() == 0 && !empIdFilter.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No payment records found for Employee ID: " + empIdFilter,
                    "No Results", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading payment history: " + e.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // For testing purposes only - normally called from PaymentFunction
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PaymentHistory(new conn());
            }
        });
    }
}