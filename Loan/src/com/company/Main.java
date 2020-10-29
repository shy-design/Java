package com.company;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
        // my GitHub submission is : https://github.com/shy-design/Final/releases/tag/v1.0
        Business obj = new Business("123","mmm",150000,10,"Personal");
        //obj.getLoanamount();
        //obj.getYears();
        //System.out.println(obj.getLoanamount());
       // System.out.println(obj.getYears());
       // System.out.println(obj.computeAmortization());
        double balance = obj.getLoanamount();
        int period = obj.getYears() * 12;
        double interest = 0;
        double principal = 0;
        double monthlyPayment = 0;
        double monthlyInterest = 0;
        int rows = 13;
        int columns = 5;

              String [][] results = new String [rows][columns];
            /*for(int i = 0; i <= results.length; i++) {
                    String [] row = new String[results[i].length];
                    for(int k = 0; k < results[i].length;k++) {
                        row[k] = results[i][k];
                    }*/
        for(int i = 0; i <= 12; i++) {

                    principal = monthlyPayment - interest;
                    interest = balance * monthlyInterest;
                    balance -= principal;
                    monthlyInterest = 0.09/12;
                    monthlyPayment = obj.computeAmortization();
                    results[i][0] = String.valueOf(i);
                    results[i][1] = String.valueOf(principal);
                    results[i][2] = String.valueOf(interest);
                    results[i][3] = String.valueOf(monthlyPayment);
                    results[i][4] = String.valueOf(balance);
            System.out.print(i);
            System.out.printf(" %.2f",principal);
            System.out.printf(" %.2f",interest);
            System.out.printf(" %.2f",monthlyPayment);
            System.out.printf(" %.2f",balance);
            System.out.println();
            //System.out.println(results[3][4]);
                //System.out.println();
                //System.out.print(i);
                //System.out.printf(" %.2f",principal);
                //System.out.printf(" %.2f",interest);
                //System.out.printf(" %.2f",monthlyPayment);
               // System.out.printf(" %.2f",balance);
               // System.out.println();
            }
        System.out.println(Arrays.deepToString(results));
        }



    }

