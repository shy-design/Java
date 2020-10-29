/*
 * Created by JFormDesigner on Tue Aug 11 13:34:27 PDT 2020
 */

package com.company;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author Kateryna Timonina
 */
// my GitHub submission is : https://github.com/shy-design/Final/releases/tag/v1.0
public class Form1 extends JFrame {
    public static String clientnoEdited;
    public Form1() {
        initComponents();
    }
    public void prepare() {
        String[] cols  = {"Number", "Name", "Amount","Years","Type of loan"};
        String [] [] data = {{"b1", "b2","b3","b4","b5"}};
        DefaultTableModel model = new DefaultTableModel(data,cols);
        table1.setModel(model);
        Vector vector = model.getDataVector();
        comboBox1.addItem("Business");
        comboBox1.addItem("Personal");
        textField10.setEnabled(false);


    }

    public static void main(String[] args)  {
        Form1 object = new Form1();
        object.prepare();
        object.setVisible(true);
    }

    public void updateTable() {
        try{
            //double monthlyPayment;
            PreparedStatement query;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/loan","root","");
            query = con1.prepareStatement("select * from loantable");
            ResultSet rs = query.executeQuery();
            int c;
            ResultSetMetaData res = rs.getMetaData();
            c = res.getColumnCount();
            DefaultTableModel bf = (DefaultTableModel)table1.getModel();
            bf.setRowCount(0);
            while(rs.next()) {
                Vector v = new Vector();
                for(int i = 1; i <= c; i++) {
                    v.add(rs.getString("clientno"));
                    v.add(rs.getString("clientname"));
                    v.add(rs.getString("loanamount"));
                    v.add(rs.getString("years"));
                    v.add(rs.getString("loantype"));

                }
                bf.addRow(v);
            }

        }
        catch(ClassNotFoundException|SQLException error) {
            error.getMessage();
        }


    }

    private void buttonAddActionPerformed(ActionEvent e) {
        // TODO add your code here

        try {
            updateTable();
            PreparedStatement query;
            String clientno, clientname,loantype;
            double loanamount = 0.0;
            int years;
            clientno = textField6.getText();
            clientname = textField7.getText();
            loanamount = Double.parseDouble(textField8.getText());
            textField8.setInputVerifier(fieldVerifier2);
            years = Integer.parseInt((textField9.getText()));
            textField9.setInputVerifier(fieldVerifier);
            loantype = (String) comboBox1.getSelectedItem();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/loan","root","");
            if(e.getSource() == buttonAdd) {
                query = con1.prepareStatement("Select * from loantable where clientno = ?");
                query.setString(1, clientno);
                ResultSet rs = query.executeQuery();
                if (rs.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(null, "The client number you are trying to enter already exists ");
                    textField6.setText(" ");
                    textField7.setText(" ");
                    textField8.setText(" ");
                    textField9.setText(" ");
                    textField6.requestFocus();
                    return;
                }

                query = con1.prepareStatement("insert into loantable values (?,?,?,?,?)");
                query.setString(1,clientno);
                query.setString(2,clientname);
                query.setString(3, String.valueOf(loanamount));
                query.setString(4, String.valueOf(years));
                query.setString(5,loantype);
                query.executeUpdate();
                JOptionPane.showMessageDialog(null,"One record was added to the database");
                textField6.setText(" ");
                textField7.setText(" ");
                textField8.setText(" ");
                textField9.setText(" ");
                textField6.requestFocus();
                updateTable();
            }
        }
        catch(ClassNotFoundException | SQLException | NumberFormatException error) {
            error.getMessage();
        }
       
    }

    private void buttonEditActionPerformed(ActionEvent e) {
        // TODO add your code here
        try {
            PreparedStatement query;
            String clientno, clientname,loantype;
            Double loanamount;
            int years;
            clientno = textField6.getText();
            clientname = textField7.getText();
            loanamount = Double.parseDouble(textField8.getText());
            textField8.setInputVerifier(fieldVerifier2);
            years = Integer.parseInt((textField9.getText()));
            textField9.setInputVerifier(fieldVerifier);
            loantype = (String) comboBox1.getSelectedItem();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/loan","root","");
            query = con1.prepareStatement("Update loantable set clientno = ?,clientname = ? ," +
                    "loanamount = ?, years = ?, loantype = ? where clientno = ? ");
            query.setString(1,clientno);
            query.setString(2,clientname);
            query.setString(3, String.valueOf(loanamount));
            query.setString(4, String.valueOf(years));
            query.setString(5,loantype);
            query.setString(6,clientnoEdited);
            query.executeUpdate();
            JOptionPane.showMessageDialog(null,"One record was changed in the database");
            textField6.setText(" ");
            textField7.setText(" ");
            textField8.setText(" ");
            textField9.setText(" ");
            textField6.requestFocus();
            updateTable();
        }
        catch (ClassNotFoundException | SQLException error) {
            error.getMessage();
        }
    }

    private void table1MouseClicked(MouseEvent e) {
        // TODO add your code here

        DefaultTableModel df = (DefaultTableModel)table1.getModel();
        int index = table1.getSelectedRow();
        textField6.setText(df.getValueAt(index,0).toString());
        textField7.setText(df.getValueAt(index,1).toString());
        textField8.setText(df.getValueAt(index,2).toString());
        textField9.setText(df.getValueAt(index,3).toString());
        clientnoEdited = textField6.getText();

        String clientno, clientname,loantype;
        double loanamount;
        int years;
        clientno = textField6.getText();
        clientname = textField7.getText();
        loanamount = Double.parseDouble(textField8.getText());
        years = Integer.parseInt((textField9.getText()));
        loantype = (String) table1.getValueAt(index,4);
        double payment;

        if(loantype.equals("Business")) {
            // this is a method without Generate interface and generateTable()
           // String[] cols2  = {"Payment", "Principal", "Interest","Monthly Payment","Balance"};
            //String [] [] data2 = {{"b1","b2","b3","b4","b5"}};
            //DefaultTableModel model2 = new DefaultTableModel(data2,cols2);
           // table2.setModel(model2);
            Business object1 = new Business(clientno,clientname,loanamount,years,loantype);

            payment = object1.computeAmortization();
            //textField10.setText(String.valueOf(payment));
            textField10.setText(getTwoDecimals(payment));

            /*int period = years * 12;
            double balance = object.getLoanamount();
            double interest = 0;
            double principal = 0;
            double monthlyPayment = 0;
            double monthlyInterest = 0;

            model2.setRowCount(0);
            for(int i = 0; i < 5;i++) {

                for(int k = 0; k <= period; k++) {
                    Vector vv = new Vector();
                    interest = balance * monthlyInterest;
                    principal = monthlyPayment - interest;
                     monthlyInterest = 0.09/12;
                    balance -= principal;

                    data2[i][0] = String.valueOf(k);
                    data2[i][1] = String.valueOf(principal);
                    data2[i][2] = String.valueOf(interest);
                    data2[i][3] = String.valueOf(monthlyPayment);
                    data2[i][4] = String.valueOf(balance);
                    vv.add(data2[i][0]);
                    vv.add(data2[i][1]);
                    vv.add(data2[i][2]);
                    vv.add(data2[i][3]);
                    vv.add(data2[i][4]);
                    model2.addRow(vv);

                    monthlyPayment = object.computeAmortization();
                }*/

               object1.generateTable(table2);

            }
            else if(loantype.equals("Personal")) {
                Personal object2 = new Personal(clientno,clientname,loanamount,years,loantype);
                payment = object2.computeAmortization();
                textField10.setText(String.valueOf(getTwoDecimals(payment)));

                object2.generateTable(table2);

            }

        }

    private void buttonDeleteActionPerformed(ActionEvent e) {
        // TODO add your code here
        try{
            PreparedStatement query;
            String clientno, clientname,loantype;
            double loanamount;
            int years;
            clientno = textField6.getText();
            clientname = textField7.getText();
            loanamount = Double.parseDouble(textField8.getText());
            years = Integer.parseInt((textField9.getText()));
            loantype = (String) comboBox1.getSelectedItem();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/loan","root","");
            query = con1.prepareStatement("delete from loantable where clientno =?");
            int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete?", "Delete",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){

                query.setString(1, clientno);

            }
            query.execute();
            JOptionPane.showMessageDialog(null, "Record deleted");
            textField6.setText(" ");
            textField7.setText(" ");
            textField8.setText(" ");
            textField9.setText(" ");
            textField6.requestFocus();
            updateTable();

        }
        catch(ClassNotFoundException | SQLException error) {
            error.getMessage();
        }

    }

    public static String getTwoDecimals(Double value){
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(value);
    }


    InputVerifier fieldVerifier = new InputVerifier() {
        @Override
        public boolean verify(JComponent input) {
            JTextField temp = (JTextField)input;
            try {
                int number = Integer.parseInt(temp.getText());
                if(number > 0) {
                    return true;
                }
                else  {
                    JOptionPane.showMessageDialog(null, "Please, enter the number greater than 0!");
                    return false;
                }
            }
            catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please, enter integer number!");
            }

            return false;
        }
    };

    InputVerifier fieldVerifier2 = new InputVerifier() {
        @Override
        public boolean verify(JComponent input) {
            JTextField temp = (JTextField)input;
            try {
                double number = Double.parseDouble(temp.getText());
                if(number > 0) {
                    return true;
                }
                else  {
                    JOptionPane.showMessageDialog(null, "Please, enter the number greater than 0!");
                    return false;
                }
            }
            catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please, enter numeric value!");
            }

            return false;
        }
    };

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Kateryna Timonina
        textField6 = new JTextField();
        textField7 = new JTextField();
        textField8 = new JTextField();
        textField9 = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        comboBox1 = new JComboBox();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        scrollPane2 = new JScrollPane();
        table2 = new JTable();
        buttonAdd = new JButton();
        buttonEdit = new JButton();
        buttonDelete = new JButton();
        label6 = new JLabel();
        textField10 = new JTextField();

        //======== this ========
        setTitle("Loan Projection");
        var contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(textField6);
        textField6.setBounds(385, 30, 420, 30);
        contentPane.add(textField7);
        textField7.setBounds(385, 60, 420, 30);
        contentPane.add(textField8);
        textField8.setBounds(385, 90, 420, 30);
        contentPane.add(textField9);
        textField9.setBounds(385, 120, 420, 30);

        //---- label1 ----
        label1.setText("Enter the client number:");
        contentPane.add(label1);
        label1.setBounds(20, 30, 165, 30);

        //---- label2 ----
        label2.setText("Enter the client name:");
        contentPane.add(label2);
        label2.setBounds(20, 60, 175, 26);

        //---- label3 ----
        label3.setText("Enter the customer loan amount");
        contentPane.add(label3);
        label3.setBounds(20, 90, 210, 26);

        //---- label4 ----
        label4.setText("Enter the number of years to pay:");
        contentPane.add(label4);
        label4.setBounds(20, 120, 210, 26);

        //---- label5 ----
        label5.setText("Enter the loan type:");
        contentPane.add(label5);
        label5.setBounds(20, 150, 255, 26);
        contentPane.add(comboBox1);
        comboBox1.setBounds(385, 150, 420, 30);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    table1MouseClicked(e);
                }
            });
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(20, 210, 330, 220);

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(table2);
        }
        contentPane.add(scrollPane2);
        scrollPane2.setBounds(385, 210, 420, 220);

        //---- buttonAdd ----
        buttonAdd.setText("Add");
        buttonAdd.addActionListener(e -> buttonAddActionPerformed(e));
        contentPane.add(buttonAdd);
        buttonAdd.setBounds(30, 450, buttonAdd.getPreferredSize().width, 30);

        //---- buttonEdit ----
        buttonEdit.setText("Edit");
        buttonEdit.addActionListener(e -> buttonEditActionPerformed(e));
        contentPane.add(buttonEdit);
        buttonEdit.setBounds(145, 450, buttonEdit.getPreferredSize().width, 30);

        //---- buttonDelete ----
        buttonDelete.setText("Delete");
        buttonDelete.addActionListener(e -> buttonDeleteActionPerformed(e));
        contentPane.add(buttonDelete);
        buttonDelete.setBounds(260, 450, buttonDelete.getPreferredSize().width, 30);

        //---- label6 ----
        label6.setText("Monthly payment:");
        contentPane.add(label6);
        label6.setBounds(385, 450, 100, 30);
        contentPane.add(textField10);
        textField10.setBounds(515, 450, 170, textField10.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(820, 535));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Kateryna Timonina
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JComboBox comboBox1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JButton buttonAdd;
    private JButton buttonEdit;
    private JButton buttonDelete;
    private JLabel label6;
    private JTextField textField10;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
