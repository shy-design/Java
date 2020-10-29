package com.company;

public class Regular  extends Seats {
    int seatNumber;
    String row;
    double price;
    public Regular(int seatNumber, String row ) {
        super(seatNumber,row);
        setPrice();
    }

    public double getPrice() {
        return price;
    }
    @Override
    public void setPrice() {
        price = 10;
    }
    public double calculateRegularTicketsPrice(String[][] seats, double price) {
        int count = 0;
        double totalPrice = 0;
        for(int i = 2; i < seats.length; i++) {
            for(int k = 0; k < seats[i].length; k++ ) {
                if(seats[i][k].equals("X")) {
                    count++;
                }
                totalPrice = count * price;
            }
        }
        System.out.println("You purchased " + count + " regular ticket/s");
        System.out.println("The total price for Regular tickets is: " + totalPrice);
        return totalPrice;
    }
}
