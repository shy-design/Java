package com.company;
import java.util.*;

public class Main {
static Scanner input = new Scanner(System.in);
static int rowNumber;
static String column;
static String answer;
static int k;
static String [][] seats =  new String [6][6];
    public static void main(String[] args) {
        // write your code here
        //displayMessage();
        //System.out.println();
        //getInputs();
        Seats.setSeats(seats);
        reserveTickets();
        requestMoreTickets();
    }

    public static void displayMessage() {
        Thank_You object = new Thank_You() {
            @Override
            public void display() {
                String stringLine = "Thank you";
                for (int i = 1; i <= 4; i++) {
                    System.out.println();
                    for (int k = 1; k <= i; k++) {
                        System.out.print(stringLine);
                    }
                }
                for (int l = 1; l <= 3; l++) {
                    System.out.println();
                    for (int k = 3; k >= l; k--) {
                        System.out.print(stringLine);
                    }
                }
            }
        };
        object.display();
    }

  public static void reserveTickets() {
        boolean isInputInCorrect = true;
      System.out.println();
      System.out.println("Please, enter the row number: from 1 to 5");
      rowNumber = input.nextInt();
      while(isInputInCorrect) {
          if (rowNumber == 1 || rowNumber == 2 || rowNumber == 3 || rowNumber == 4 || rowNumber == 5) {
              isInputInCorrect = false;
              break;
          }
          else {
              System.out.println("Invalid entry! Please,re-enter the row number: from 1 to 5: ");
              rowNumber = input.nextInt();
          }
      }

      input.nextLine();
      System.out.println("Please,enter the column : from A to F");
      column = input.nextLine();
      isInputInCorrect = true;
      while(isInputInCorrect) {
          if (column.equals("A") || column.equals("B") || column.equals("C") || column.equals("D")
                  || column.equals("E") || column.equals("F")) {
              //System.out.println("inside if statement: " + column);
              isInputInCorrect = false;
              break;
          }
          else {
              System.out.println("Invalid entry! Please, re-enter the column : from A to F");
              column = input.nextLine();
              //System.out.println("inside else : " + column);
          }
      }


      if(column.equals("A")){
          k = 0;
      }
      else if(column.equals("B")) {
          k = 1;
      }
      else if(column.equals("C")) {
          k = 2;
      }
      else if(column.equals("D")) {
          k = 3;
      }
      else if(column.equals("E")) {
          k = 4;
      }
      else if(column.equals("F")) {
         k = 5;
      }

      Seats.markSeats(seats,rowNumber,k);
      Seats.displaySeats(seats);
      Matinee anyMSeat = new Matinee(rowNumber,column);
      Regular anyRegSeat = new Regular(rowNumber,column);
      double priceMatinee = anyMSeat.getPrice();
      double priceRegular = anyRegSeat.getPrice();
      double billMatinee, billRegular, billTotal;
      billMatinee = anyMSeat.calculateMatineeTicketsPrice(seats,priceMatinee);
      billRegular = anyRegSeat.calculateRegularTicketsPrice(seats,priceRegular);
      billTotal = billMatinee + billRegular;
      System.out.println("Your total bill is: " + billTotal);
      System.out.println();

  }

    public static void requestMoreTickets() {
        String requestYes = "Y";
        String requestNo = "N";
        System.out.println("Do you want to make more reservations? Y or N");
        answer = input.nextLine();
        while(!requestYes.equals(answer) && !requestNo.equals(answer)) {
            System.out.println("Please, enter Y or N");
            answer = input.nextLine();
        }
        while(requestYes.equals(answer)) {
            reserveTickets();
            System.out.println("Do you want to make more reservations? Y or N");
            answer = input.nextLine();
            while(!requestYes.equals(answer) && !requestNo.equals(answer)) {
                System.out.println("Please, enter Y or N");
                answer = input.nextLine();
            }
        }
        if (requestNo.equals(answer)) {
            System.out.println("\n");
            displayMessage();
        }
    }

}



