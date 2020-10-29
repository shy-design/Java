package com.company;

public class Matinee extends Seats {
    int seatNumber;
    String row;
    double price;
    String[][] seats;
    public Matinee(int seatNumber, String row ) {
        super(seatNumber,row);
        setPrice();
    }

    public double getPrice() {
        return price;
    }
    @Override
    public void setPrice() {
        price = 20;
    }
    public double calculateMatineeTicketsPrice(String[][] seats, double price) {
        int count = 0;
        double totalPrice = 0;
      for(int i = 1; i < 2; i++) {
          for(int k = 0; k < seats[i].length; k++ ) {
              if(seats[i][k].equals("X")) {
                  count++;
              }
              totalPrice = count * price;
          }
      }
      System.out.println("You purchased " + count + " matinee ticket/s");
      System.out.println("The total price for Matinee tickets is: " + totalPrice);
      return totalPrice;
    }

}
