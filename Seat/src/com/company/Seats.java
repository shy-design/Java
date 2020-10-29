package com.company;

public  abstract class Seats {
    private int seatNumber;
    private String row;
    private double price;
    private String [][] seats = new String [6][6];

    public Seats(int seatNumber, String row) {
        this.seatNumber = seatNumber;
        this.row = row;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public abstract void setPrice();

    public static void setSeats(String[][] seats) {
        //this.seats = seats;
        int i, k;
        System.out.print("\t");
        for(k = 0; k < 1; k++) {
            System.out.print("A" + "\t" + "B" + "\t" + "C" + "\t" + "D" + "\t" + "E" + "\t" + "F" + "\t");
        }
        System.out.println();

        for(i = 1; i < seats.length; i++) {
            System.out.print(i + "\t");
            for(k = 0; k < seats[i].length; k++) {
                seats[i][k] = "E";
                System.out.print( seats[i][k] + "\t");
            }
            System.out.println();
        }
    }
    public static void markSeats(String[][] seats,int i, int k) {
        if(seats[i][k].equals("E")) {
            seats[i][k] = "X";
        }
        else{
            System.out.println("This seat is taken");
        }
    }

    public static void displaySeats(String [][] seats) {
        int i, k;
        System.out.print("\t");
        for(k = 0; k < 1; k++) {
            System.out.print("A" + "\t" + "B" + "\t" + "C" + "\t" + "D" + "\t" + "E" + "\t" + "F" + "\t");
        }
        System.out.println();

        for(i = 1; i < seats.length; i++) {
            System.out.print(i + "\t");
            for(k = 0; k < seats[i].length; k++) {
                System.out.print( seats[i][k] + "\t");
            }
            System.out.println();
        }
    }
}
