package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.util.Vector;

public class Business  extends LoanP implements Generate{

    public Business(String clientno, String clientname, double loanamount, int years, String loantype) {
        super(clientno, clientname, loanamount, years, loantype);

    }

    @Override
    public void generateTable(JTable table) {
        String[] cols2  = {"Payment", "Principal", "Interest","Monthly Payment","Balance"};
        String [] [] data2 = {{"b1","b2","b3","b4","b5"}};
        DefaultTableModel model = new DefaultTableModel(data2,cols2);
        table.setModel(model);
        Vector vv = model.getDataVector();
        int period = getYears() * 12;
        double balance = getLoanamount();

        double interest;
        double principal;
        double monthlyPayment = 0;
        double monthlyInterest = 0;

        model.setRowCount(0);
        for(int i = 0; i <= period;i++) {
            for (int k = 0; k <= period; k++) {
                vv = new Vector();
                interest = balance * monthlyInterest;
                principal = monthlyPayment - interest;
                monthlyInterest = 0.09 / 12;
                balance -= principal;

                data2[i][0] = String.valueOf(k);
                data2[i][1] = getTwoDecimals(principal);
                data2[i][2] = getTwoDecimals(interest);
                data2[i][3] = getTwoDecimals(monthlyPayment);
                data2[i][4] = getTwoDecimals(balance);
                vv.add(data2[i][0]);
                vv.add(data2[i][1]);
                vv.add(data2[i][2]);
                vv.add(data2[i][3]);
                vv.add(data2[i][4]);
                model.addRow(vv);

                monthlyPayment = computeAmortization();
            }
        }

    };

    public static String getTwoDecimals(Double value){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(value);
    }


    public double computeAmortization() {
        double monthlyPayment =  (getLoanamount() * (0.09/12))/(1- Math.pow(1 + 0.09/12,-(getYears()*12)));
        return monthlyPayment;
    }
}
