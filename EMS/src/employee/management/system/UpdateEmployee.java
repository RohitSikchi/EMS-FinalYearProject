package employee.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;

public class UpdateEmployee extends JFrame implements ActionListener {
    JTextField teducation, taddress, tphone, temail, tsalary, tdesignation;
    JLabel tempid;
    JButton update,back;

    String number;
    

    UpdateEmployee(String number){

        this.number = number;

        getContentPane().setBackground(new Color(255, 255, 213));

        JLabel heading = new JLabel("Update  Employee  Detail");
        heading.setBounds(480, 30, 500, 50);
        heading.setFont(new Font("Lucida Handwriting", Font.BOLD, 23));
        heading.setForeground(Color.MAGENTA);
        
        add(heading);

        // ---------- 1. employee name -----------
        JLabel name = new JLabel("Name");
        name.setBounds(50, 150, 150, 30);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(name);

        JLabel tname = new JLabel();
        tname.setBounds(250, 150, 250, 30);
        tname.setFont(new Font("Tahoma",Font.BOLD,14));
        add(tname);

        // -------------- 2. father name ---------------
        JLabel fname = new JLabel("Father Name");
        fname.setBounds(600, 150, 150, 30);
        fname.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(fname);

        JLabel tfname = new JLabel();
        tfname.setBounds(800, 150, 250, 30);
        tfname.setFont(new Font("Tahoma",Font.BOLD,14));
        add(tfname);

        // ----------- 3. date of birth----------------

        JLabel dob = new JLabel("Date of Birth");
        dob.setBounds(50, 200, 150, 30);
        dob.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(dob);

        JLabel tdob = new JLabel();
        tdob.setBounds(250,200,250,30);
        tdob.setFont(new Font("Tahoma",Font.BOLD,14));
        add(tdob);
        //------------- 4.salary -----------------

        JLabel salary = new JLabel("Salary");
        salary.setBounds(600, 200, 150, 30);
        salary.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(salary);

        tsalary = new JTextField();
        tsalary.setBounds(800, 200, 250, 30);
        tsalary.setBackground(new Color(240, 234, 233));
        add(tsalary);

        //------------ 5.employee Address -------------

        JLabel address = new JLabel("Address");
        address.setBounds(50, 250, 150, 30);
        address.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(address);

        taddress = new JTextField();
        taddress.setBounds(250, 250, 250, 30);
        taddress.setBackground(new Color(240, 234, 233));
        add(taddress);

        //------------- 6.Phone Number -----------------

        JLabel phone = new JLabel("Phone Number");
        phone.setBounds(600, 250, 150, 30);
        phone.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(phone);

        tphone = new JTextField();
        tphone.setBounds(800, 250, 250, 30);
        tphone.setBackground(new Color(240, 234, 233));
        add(tphone);

        //------------ 7.employee Email id -------------

        JLabel email = new JLabel("Email");
        email.setBounds(50, 300, 150, 30);
        email.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(email);

        temail = new JTextField();
        temail.setBounds(250, 300, 250, 30);
        temail.setBackground(new Color(240, 234, 233));
        add(temail);

        //------------- 8.Highest Education -----------------

        JLabel education = new JLabel("Highest Education");
        education.setBounds(600, 300, 250, 30);
        education.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(education);

        teducation = new JTextField();
        teducation.setBounds(800,300,250,30);
        teducation.setBackground(new Color(240, 234, 233));
        add(teducation);

        //------------ 9.employee designation-------------

        JLabel designation = new JLabel("Designation");
        designation.setBounds(50, 350, 150, 30);
        designation.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(designation);

        tdesignation = new JTextField();
        tdesignation.setBounds(250, 350, 250, 30);
        tdesignation.setBackground(new Color(240, 234, 233));
        add(tdesignation);


        
        

        //------------ 10.employee Addhar -------------

        JLabel addhar = new JLabel("Addhar Number");
        addhar.setBounds(600, 350, 150, 30);
        addhar.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(addhar);

        JLabel taadhar = new JLabel();
        taadhar.setBounds(800, 350, 250, 30);
        taadhar.setFont(new Font("Tahoma",Font.BOLD,14));
        add(taadhar);

        

        //------------- 11.Employee id -----------------

        JLabel empid = new JLabel("Employee ID");
        empid.setBounds(50, 400, 150, 30);
        empid.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(empid);

        tempid = new JLabel("");
        tempid.setBounds(250, 400, 250, 30);
        tempid.setFont(new Font("SAN_SERIF",Font.BOLD,14));
        tempid.setForeground(Color.RED);
        

        add(tempid);

// * --------------------------------get Original data from database---------------------------------------------------
        try {
            conn c = new conn();
            String query = " select * from employee where empID = '"+number+"' ";
            ResultSet rs = c.statement.executeQuery(query);
            while (rs.next()) {
                tname.setText(rs.getString("name"));
                tfname.setText(rs.getString("fname"));
                tdob.setText(rs.getString("dob"));
                tsalary.setText(rs.getString("salary"));
                taddress.setText(rs.getString("address"));
                tphone.setText(rs.getString("phone"));
                temail.setText(rs.getString("email"));
                teducation.setText(rs.getString("education"));
                tdesignation.setText(rs.getString("designation"));
                taadhar.setText(rs.getString("aadhar"));
                tempid.setText(rs.getString("empID"));


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
// * -------------------------------------------------------------------------------------------------

//*  ---------------------------------- add and back button -------------------------------------------

        update = new JButton("UPDATE");
        update.setBounds(450,550,150,35);
        update.setBackground(Color.DARK_GRAY);
        update.setForeground(Color.MAGENTA);
        update.setFont(new Font("Arial", Font.BOLD, 14));
        update.setFocusPainted(false);
        update.setCursor(new Cursor(Cursor.HAND_CURSOR));
        update.addActionListener(this);
        add(update);

        back = new JButton("BACK");
        back.setBounds(650,550,150,35);
        back.setBackground(Color.DARK_GRAY);
        back.setForeground(Color.MAGENTA);
        back.setFont(new Font("Arial", Font.BOLD, 14));
        back.setFocusPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(this);
        add(back);

        setSize(1280,700);
        setLayout(null);
        setLocation(0, 7);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == update) {
            String salary = tsalary.getText();
            String address = taddress.getText();
            String phone = tphone.getText();
            String email = temail.getText();
            String education = teducation.getText();
            String designation = tdesignation.getText();
    
            // Check for empty fields
            if (salary.isEmpty() || address.isEmpty() || phone.isEmpty() || 
                email.isEmpty() || designation.isEmpty() || education.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields before submitting", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // 1. Email validation
            if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(null, 
                    "Please enter a valid email (e.g., example@domain.com)", 
                    "Invalid Email", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // 2. Phone number validation
            if (!isValidPhone(phone)) {
                JOptionPane.showMessageDialog(null, 
                    "Phone number must be exactly 10 digits", 
                    "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // 3. Salary validation
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
                    "Salary must be a valid number", 
                    "Invalid Salary", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Check phone number uniqueness
            try {
                conn c = new conn();
                String checkPhoneQuery = "SELECT * FROM employee WHERE phone = '" + phone + "' AND empID != '" + number + "'";
                ResultSet rs = c.statement.executeQuery(checkPhoneQuery);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, 
                        "This phone number is already registered", 
                        "Duplicate Phone Number", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
    
            // Update database if all validations pass
            try {
                conn c = new conn();
                String query = "UPDATE employee SET salary = '" + salary + "', address = '" + address + 
                              "', phone = '" + phone + "', email = '" + email + "', education = '" + education + 
                              "', designation = '" + designation + "' WHERE empID = '" + number + "'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details Updated Successfully...");
                setVisible(false);
                new Main_class();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
            new ViewEmployee();
        }
    }
    
    // Email validation method
    private boolean isValidEmail(String email) {
        // Email validation rules:
        // 1. Must contain @ symbol
        // 2. At least 2 characters before @
        // 3. Domain must be at least 4 characters after @ (e.g., gmail.com)
        // 4. Must contain a dot in the domain part
        // 5. Top-level domain (after last dot) must be 2-4 characters
        // 6. Must contain only valid characters
        
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";
        if (!email.matches(emailRegex)) return false;
    
        int atIndex = email.indexOf('@');
        if (atIndex < 2) return false; // At least 2 characters before @
    
        String domain = email.substring(atIndex + 1);
        if (domain.length() < 4) return false; // Minimum domain length after @
    
        int lastDotIndex = domain.lastIndexOf('.');
        if (lastDotIndex < 1 || lastDotIndex == domain.length() - 1) return false; // Dot position check
    
        String tld = domain.substring(lastDotIndex + 1); // Top-level domain
        if (tld.length() < 2 || tld.length() > 4) return false; // TLD must be 2-4 characters
    
        // Additional check to prevent short domains like "gma.co"
        String domainName = domain.substring(0, lastDotIndex);
        if (domainName.length() < 3) return false; // Domain name before TLD must be at least 3 characters
    
        return true;
    }
    
    // Phone number validation method
    private boolean isValidPhone(String phone) {
        // Must be exactly 10 digits
        return phone.matches("\\d{10}");
    }



    public static void main(String[] args) {
        new UpdateEmployee("");
    }
}
