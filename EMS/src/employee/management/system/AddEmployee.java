package employee.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import javax.swing.text.*;

public class AddEmployee extends JFrame implements ActionListener {

    JTextField tname, tfname, taddress, tphone, taadhar, temail, tsalary, tdesignation;
    JLabel tempid;
    JDateChooser tdob;
    JComboBox<String> Boxeducation;
    JButton add, back;

    AddEmployee() {
        // Set background color with valid RGB values
        getContentPane().setBackground(new Color(240, 219, 213));

        JLabel heading = new JLabel("ADD  Employee  Detail");
        heading.setBounds(480, 30, 500, 50);
        heading.setFont(new Font("Lucida Handwriting", Font.BOLD, 23));
        heading.setForeground(Color.RED);

        add(heading);

        // ---------- 1. employee name -----------
        JLabel name = new JLabel("Name");
        name.setBounds(50, 150, 150, 30);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(name);

        tname = new JTextField();
        tname.setBounds(250, 150, 250, 30);
        tname.setBackground(new Color(240, 234, 233));
        add(tname);

        // -------------- 2. father name ---------------
        JLabel fname = new JLabel("Father Name");
        fname.setBounds(600, 150, 150, 30);
        fname.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(fname);

        tfname = new JTextField();
        tfname.setBounds(800, 150, 250, 30);
        tfname.setBackground(new Color(240, 234, 233));
        add(tfname);

        // ----------- 3. date of birth----------------

        JLabel dob = new JLabel("Date of Birth");
        dob.setBounds(50, 200, 150, 30);
        dob.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(dob);

        tdob = new JDateChooser();
        tdob.setBounds(250, 200, 250, 30);
        tdob.setBackground(new Color(240, 234, 233));

        // Set date range restrictions
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        // Set maximum date (7 years before current year)
        calendar.set(currentYear - 7, Calendar.JANUARY, 1);
        Date maxDate = calendar.getTime();
        tdob.setMaxSelectableDate(maxDate);

        // Set minimum date (January 1, 1950)
        calendar.set(1950, Calendar.JANUARY, 1);
        Date minDate = calendar.getTime();
        tdob.setMinSelectableDate(minDate);

        // Optional: Set a default date (e.g., January 1 of current year - 7)
        calendar.set(currentYear - 7, Calendar.JANUARY, 1);
        tdob.setDate(calendar.getTime());

        add(tdob);
        // ------------- 4.salary -----------------

        JLabel salary = new JLabel("Salary");
        salary.setBounds(600, 200, 150, 30);
        salary.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(salary);
        
        tsalary = new JTextField();
        tsalary.setBounds(800, 200, 250, 30);
        tsalary.setBackground(new Color(240, 234, 233));
        // Add document filter to accept only numbers
        ((AbstractDocument) tsalary.getDocument()).setDocumentFilter(new NumericFilter());
        add(tsalary);
        // ------------ 5.employee Address -------------

        JLabel address = new JLabel("Address");
        address.setBounds(50, 250, 150, 30);
        address.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(address);

        taddress = new JTextField();
        taddress.setBounds(250, 250, 250, 30);
        taddress.setBackground(new Color(240, 234, 233));
        add(taddress);

        // ------------- 6.Phone Number -----------------

        JLabel phone = new JLabel("Phone Number");
        phone.setBounds(600, 250, 150, 30);
        phone.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(phone);

        tphone = new JTextField();
        tphone.setBounds(800, 250, 250, 30);
        tphone.setBackground(new Color(240, 234, 233));
        add(tphone);

        // ------------ 7.employee Email id -------------

        JLabel email = new JLabel("Email");
        email.setBounds(50, 300, 150, 30);
        email.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(email);

        temail = new JTextField();
        temail.setBounds(250, 300, 250, 30);
        temail.setBackground(new Color(240, 234, 233));
        add(temail);

        // ------------- 8.Highest Education -----------------

        JLabel education = new JLabel("Highest Education");
        education.setBounds(600, 300, 250, 30);
        education.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(education);

        String items[] = { "BBA", "B,Tech", "M.Sc", "MCA", "MCS", "M.Tech", "BA", "B.Sc", "B.Com", "MBA", "M.Com", "MA",
                "PHD" };
        Boxeducation = new JComboBox<>(items);
        Boxeducation.setBackground(new Color(240, 234, 233));
        Boxeducation.setBounds(800, 300, 250, 30);
        add(Boxeducation);

        // ------------ 9.employee designation-------------

        JLabel designation = new JLabel("Designation");
        designation.setBounds(50, 350, 150, 30);
        designation.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(designation);

        tdesignation = new JTextField();
        tdesignation.setBounds(250, 350, 250, 30);
        tdesignation.setBackground(new Color(240, 234, 233));
        add(tdesignation);

        // ------------ 10.employee Addhar -------------

        JLabel addhar = new JLabel("Addhar Number");
        addhar.setBounds(600, 350, 150, 30);
        addhar.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(addhar);

        taadhar = new JTextField();
        taadhar.setBounds(800, 350, 250, 30);
        taadhar.setBackground(new Color(240, 234, 233));
        add(taadhar);

        // ------------- 11.Employee id -----------------

        JLabel empid = new JLabel("Employee ID");
        empid.setBounds(50, 400, 150, 30);
        empid.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(empid);

        // Generate unique employee ID
        String uniqueEmpId = generateUniqueEmployeeId();
        tempid = new JLabel(uniqueEmpId);
        tempid.setBounds(250, 400, 250, 30);
        tempid.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        tempid.setForeground(Color.RED);
        add(tempid);

        // -------------- add and back button -------------

        add = new JButton("Add");
        add.setBounds(450, 550, 150, 40);
        add.setBackground(Color.black);
        add.setForeground(Color.red);
        add.setFont(new Font("Arial", Font.BOLD, 16));
        add.setFocusPainted(false);
        add.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add.addActionListener(this);
        add(add);

        back = new JButton("Back");
        back.setBounds(650, 550, 150, 40);
        back.setBackground(Color.black);
        back.setForeground(Color.red);
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.setFocusPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(this);
        add(back);

        // Set layout and window properties
        setSize(1280, 700);
        setLayout(null);
        setLocation(0, 7);
        setVisible(true);
    }


    private String generateUniqueEmployeeId() {
        String empId;
        boolean isUnique = false;
        Random ran = new Random();
        
        try {
            conn c = new conn();
            do {
                // Generate a 6-digit number with leading zeros
                int number = ran.nextInt(1000000); // 0 to 999999
                empId = String.format("%06d", number); // Ensures 6 digits with leading zeros
                
                // Check if this ID already exists
                String checkQuery = "SELECT COUNT(*) FROM employee WHERE empId = '" + empId + "'";
                ResultSet rs = c.statement.executeQuery(checkQuery);
                rs.next();
                int count = rs.getInt(1);
                
                isUnique = (count == 0);
            } while (!isUnique);
            
            return empId;
        } catch (Exception e) {
            e.printStackTrace();
            // Fallback in case of database error
            return String.format("%06d", ran.nextInt(1000000));
        }
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String name = tname.getText().trim();
            String fname = tfname.getText().trim();
            String dobText = ((JTextField) tdob.getDateEditor().getUiComponent()).getText().trim();
            String salary = tsalary.getText().trim();
            String address = taddress.getText().trim();
            String phone = tphone.getText().trim();
            String email = temail.getText().trim();
            String education = (String) Boxeducation.getSelectedItem();
            String designation = tdesignation.getText().trim();
            String aadhar = taadhar.getText().trim();
            String empID = tempid.getText().trim();
    
            // Validation check for empty fields
            if (name.isEmpty() || fname.isEmpty() || dobText.isEmpty() || salary.isEmpty() || 
                address.isEmpty() || phone.isEmpty() || email.isEmpty() || 
                designation.isEmpty() || aadhar.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields before submitting", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Validate date of birth
            Date selectedDate = tdob.getDate();
            Calendar cal = Calendar.getInstance();
            int currentYear = cal.get(Calendar.YEAR);
            cal.set(1950, Calendar.JANUARY, 1);
            Date minDate = cal.getTime();
            cal.set(currentYear - 7, Calendar.JANUARY, 1);
            Date maxDate = cal.getTime();
    
            if (selectedDate == null || selectedDate.before(minDate) || selectedDate.after(maxDate)) {
                JOptionPane.showMessageDialog(null, 
                    "Please select a valid date of birth between 1950 and " + (currentYear - 7), 
                    "Invalid Date", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Salary validation
            try {
                double salaryValue = Double.parseDouble(salary);
                if (salaryValue < 10000 || salaryValue > 1000000) {
                    JOptionPane.showMessageDialog(null, 
                        "Salary must be between 10,000 and 1,000,000", 
                        "Invalid Salary", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, 
                    "Please enter a valid numeric salary", 
                    "Invalid Salary Format", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Aadhar number validation (12 digits, numbers only)
            if (!aadhar.matches("\\d{12}")) {
                JOptionPane.showMessageDialog(null, 
                    "Please enter a valid 12-digit Aadhar number (numbers only)", 
                    "Invalid Aadhar", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Phone number validation (10 digits, numbers only)
            if (!phone.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(null, 
                    "Please enter a valid 10-digit phone number (numbers only)", 
                    "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Email validation
            if (!email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$")) {
                JOptionPane.showMessageDialog(null, 
                    "Please enter a valid email (must contain @, digits and characters before and after @)", 
                    "Invalid Email", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            try {
                conn c = new conn();
                
                // Check uniqueness for Aadhar, phone, and email
                String checkAadharQuery = "SELECT COUNT(*) FROM employee WHERE aadhar = '" + aadhar + "'";
                ResultSet rsAadhar = c.statement.executeQuery(checkAadharQuery);
                rsAadhar.next();
                if (rsAadhar.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(null, 
                        "This Aadhar number already exists. Please enter a unique Aadhar number", 
                        "Duplicate Aadhar", JOptionPane.ERROR_MESSAGE);
                    return;
                }
    
                String checkPhoneQuery = "SELECT COUNT(*) FROM employee WHERE phone = '" + phone + "'";
                ResultSet rsPhone = c.statement.executeQuery(checkPhoneQuery);
                rsPhone.next();
                if (rsPhone.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(null, 
                        "This phone number already exists. Please enter a unique phone number", 
                        "Duplicate Phone Number", JOptionPane.ERROR_MESSAGE);
                    return;
                }
    
                String checkEmailQuery = "SELECT COUNT(*) FROM employee WHERE email = '" + email + "'";
                ResultSet rsEmail = c.statement.executeQuery(checkEmailQuery);
                rsEmail.next();
                if (rsEmail.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(null, 
                        "This email already exists. Please enter a unique email", 
                        "Duplicate Email", JOptionPane.ERROR_MESSAGE);
                    return;
                }
    
                // Insert new employee record
                String query = "INSERT INTO employee VALUES('" + name + "', '" + fname + "','" + 
                              dobText + "','" + salary + "', '" + address + "','" + phone + "', '" + 
                              email + "', '" + education + "', '" + designation + "', '" + 
                              aadhar + "', '" + empID + "')";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details Added Successfully");
                setVisible(false);
                new Main_class();
    
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Error occurred while adding employee: " + ex.getMessage(), 
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == back) {
            new Main_class();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddEmployee();
    }

}


class NumericFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) 
            throws BadLocationException {
        if (string != null && string.matches("\\d*")) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text != null && text.matches("\\d*")) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}