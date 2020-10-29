package com.company;

import javax.swing.*;

public class MyException extends Throwable {
    public MyException() {
        JOptionPane.showMessageDialog(null,"Please, enter the number greater than 0");
    }
}
