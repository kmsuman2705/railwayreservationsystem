import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int firstTrainSeats = 50;
    public static int secondTrainSeats = 50;
    public static int thirdTrainSeats = 50;
    public static int count = 0;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String GREEN_BRIGHT = "\033[0;92m";
    public static final String BLUE_BRIGHT = "\033[0;94m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";

    public static ArrayList<String> passengerNames = new ArrayList<String>(50);
    public static ArrayList<String> phoneNumbers = new ArrayList<String>(50);
    public static ArrayList<Integer> ticketFares = new ArrayList<Integer>(50);
    public static ArrayList<Integer> seatNumbers = new ArrayList<Integer>(50);
    public static ArrayList<String> trainDetails = new ArrayList<String>(50);

    public static void main(String[] args) {
        ArrayList<String> stations = new ArrayList<>(List.of("Delhi", "Jaipur", "Prayagraj", "Mumbai"));
        System.out.println("\t\t\t\t\t\t\t ***************************");
        System.out.println(ANSI_RED + "\t\t\t\t\t\t\t Railway Reservation System" + ANSI_RESET);
        System.out.println("\t\t\t\t\t\t\t ***************************");
        System.out.println();
        System.out.println(ANSI_BLUE + "Now available stations are: " + stations + ANSI_RESET);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println(ANSI_BLUE_BACKGROUND + ANSI_BLACK + "*****************************************************************************" + ANSI_RESET);
            System.out.println(ANSI_BLUE_BACKGROUND + ANSI_BLACK + "Available Trains between Stations:                                       " + ANSI_RESET);
            System.out.println(ANSI_BLUE_BACKGROUND + ANSI_BLACK + "*****************************************************************************" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "Train Name\t\t\t" + "Time\t\t\t" + "Passenger Strength\t\t\t" + "Train Number");
            System.out.println("Mumbai-Delhi\t\t" + "13:05\t\t\t\t\t" + firstTrainSeats + "\t\t\t\t\t1010 (Mumbai Superfast)");
            System.out.println("Delhi-Jaipur\t\t" + "7:00\t\t\t\t\t" + secondTrainSeats + "\t\t\t\t\t2013 (Delhi SuperFast)");
            System.out.println("Prayagraj-Delhi\t\t" + "10:00\t\t\t\t\t" + thirdTrainSeats + "\t\t\t\t\t3045 (Prayagraj EXP)" + ANSI_RESET);
            System.out.println("*****************************************************************************");
            System.out.println(BLUE_BRIGHT + "1. Book Your Ticket              " + ANSI_RESET);
            System.out.println(BLUE_BRIGHT + "2. Display All Ticket Information" + ANSI_RESET);
            System.out.println(BLUE_BRIGHT + "3. Search Your Own Ticket.       " + ANSI_RESET);
            System.out.println(BLUE_BRIGHT + "4. Cancel Your Ticket            " + ANSI_RESET);
            System.out.println(BLUE_BRIGHT + "5. Exit From The Code            " + ANSI_RESET);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Your Choice: ");
            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        try {
                            System.out.println("Enter Train Number: ");
                            int trainNumber = scanner.nextInt();
                            if (isSeatAvailable(trainNumber)) {
                                bookTicket(trainNumber);
                                break;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        displayAllTickets();
                        break;
                    case 3:
                        if (passengerNames.size() > 0) {
                            System.out.println("Enter your Phone Number: ");
                            String phoneNumber = scanner.next();
                            searchTicket(phoneNumber);
                            break;
                        } else {
                            System.out.println("No tickets have been booked today.");
                            break;
                        }
                    case 4:
                        if (passengerNames.size() > 0) {
                            System.out.println("Enter your Phone Number: ");
                            String phoneNo = scanner.next();
                            cancelTicket(phoneNo);
                            break;
                        } else {
                            System.out.println("No tickets have been booked today.");
                            break;
                        }
                    case 5:
                        System.out.println("Enjoy Your Day!");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void cancelTicket(String phoneNumber) {
        try {
            for (int i = 0; i < passengerNames.size(); i++) {
                if (phoneNumber.equals(phoneNumbers.get(i))) {
                    passengerNames.remove(i);
                    seatNumbers.remove(i);
                    phoneNumbers.remove(i);
                    trainDetails.remove(i);
                    ticketFares.remove(i);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception occurred");
        }
    }

    public static void displayAllTickets() {
        System.out.println();
        System.out.println("***************************************************************************************************");
        System.out.println(GREEN_BRIGHT + "Sno\t\t\t\t\tTicket Owner\t\t\t\t\tSeat Number\t\t\t\t\tTrain Ticket Price\t\t\t\t\t\t\t\tTrain Details" + ANSI_RESET);
        if (passengerNames.size() > 0) {
            for (int i = 0; i < passengerNames.size(); i++) {
                System.out.println(GREEN_BRIGHT + (i + 1) + "\t\t\t\t\t" + passengerNames.get(i) + "\t\t\t\t\t" + seatNumbers.get(i) + "\t\t\t\t\t\t\t\t" + ticketFares.get(i) + "\t\t\t\t\t\t\t\t\t\t\t" + trainDetails.get(i) + ANSI_RESET);
            }
        } else {
            System.out.println("No tickets have been booked so far.");
        }
        System.out.println("***************************************************************************************************");
    }

    public static void searchTicket(String phoneNumber) {
        System.out.println();
        for (int i = 0; i < passengerNames.size(); i++) {
            String p = phoneNumbers.get(i);
            if (phoneNumber.equals(p)) {
                System.out.println("***************************************************************************************************");
                System.out.println(GREEN_BRIGHT + "Sno\t\t\t\t\tTicket Owner\t\t\t\t\tSeat Number\t\t\t\t\tTrain Ticket Price\t\t\t\t\t\t\t\tTrain Details" + ANSI_RESET);
                System.out.println(GREEN_BRIGHT + (i + 1) + "\t\t\t\t\t" + passengerNames.get(i) + "\t\t\t\t\t" + seatNumbers.get(i) + "\t\t\t\t\t\t\t\t" + ticketFares.get(i) + "\t\t\t\t\t\t\t\t\t\t\t" + trainDetails.get(i) + ANSI_RESET);
                System.out.println("***************************************************************************************************");
            }
        }
    }

    public static void bookTicket(int trainNumber) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println();
            System.out.println("Enter Your Name:");
            String name = scanner.nextLine();
            passengerNames.add(name);
            System.out.println("Enter Your Phone Number: ");
            String phone = scanner.nextLine();
            phoneNumbers.add(phone);
            Random rand = new Random();
            Integer seatNo = rand.nextInt(1000 - 500 + 1) + 500;
            seatNumbers.add(seatNo);
            if (trainNumber == 1010) {
                System.out.println("Ticket Price: 1000");
                ticketFares.add(1000);
                trainDetails.add("Mumbai-Delhi   13:05   Mumbai Superfast");
                firstTrainSeats--;
            } else if (trainNumber == 2013) {
                System.out.println("Ticket Price: 500");
                ticketFares.add(500);
                trainDetails.add("Delhi-Jaipur   7:00    Delhi Superfast");
                secondTrainSeats--;
            } else {
                System.out.println("Ticket Price: 800");
                ticketFares.add(800);
                trainDetails.add("Prayagraj-Delhi    10:00      Prayagraj EXP");
                thirdTrainSeats--;
            }
            count++;
            System.out.println("Ticket Booking is successfully done.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isSeatAvailable(int trainNumber) {
        if (trainNumber == 1010) {
            return firstTrainSeats > 0;
        } else if (trainNumber == 2013) {
            return secondTrainSeats > 0;
        } else if (trainNumber == 3045) {
            return thirdTrainSeats > 0;
        } else {
            System.out.println("Choose the right Train Number.");
            return false;
        }
    }
}
