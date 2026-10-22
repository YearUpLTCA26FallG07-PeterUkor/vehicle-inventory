package com.pluralsight;

import java.util.Scanner;

public class Main {
    // scanner I will use throughout the program
    // made it stats
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // initialize vehicle array and
        // counter to keep count of vehicles in array
        Vehicle[] vehicleAray = new Vehicle[20];
        int countOfVehicles = 0;

        // create 6 vehicle objects in the array and initialize them
        // increase vehicle count to 6 for the 6 vehicles added
        vehicleAray[0] = new Vehicle(101121, "Ford Explorer", "Red", 45000, 13500);
        vehicleAray[1] = new Vehicle(101122, "Toyota Camry", "Blue", 60000, 11000);
        vehicleAray[2] = new Vehicle(101123, "Chevrolet Malibu", "Black", 50000, 9700);
        vehicleAray[3] = new Vehicle(101124, "Honda Civic", "White", 70000, 7500);
        vehicleAray[4] = new Vehicle(101125, "Subaru Outback", "Green", 55000, 14500);
        vehicleAray[5] = new Vehicle(101126, "Jeep Wrangler", "Yellow", 30000, 16000);
        countOfVehicles = 6;
        int choice;
        while (true) {
            System.out.println("""
                    \nWhat do you want to do?
                    1 - List all vehicles
                    2 - Search by make/model
                    3 - Search by price range
                    4 - Search by color
                    5 - Add a vehicle
                    6 - Quit
                    """);
            System.out.print("Enter your command: ");
            choice = input.nextInt();
            // consume \n
            input.nextLine();
            if (choice == 6 || choice < 1 || choice > 6) {
                System.out.println("=== Ending Program ===");
                break;
            }
            switch (choice) {
                case 1 -> listAllVehicles(vehicleAray, countOfVehicles);
                case 2 -> searchByMakeAndModel(vehicleAray, countOfVehicles);
                case 3 -> searchByPriceRange(vehicleAray, countOfVehicles);
                case 4 -> searchByColor(vehicleAray, countOfVehicles);
                case 5 -> addAVehicle(vehicleAray, countOfVehicles);
            }
        }
    }

    // helper method to print vehicle information
    public static void displayVehicleInformation(Vehicle vehicle){
        System.out.println("\nMake and Mode: " + vehicle.getMakeModel());
        System.out.println("Colour: " + vehicle.getColor());
        System.out.println("Odometer Reading: " + vehicle.getOdometerReading());
        System.out.println("Price: " + vehicle.getPrice());
    }

    // method to list all vehicles in the array
    // passed vehicle array and the count of vehicles in the rray
    public static void listAllVehicles(Vehicle[] vehicleArray, int countOfVehicles) {

        System.out.println("\n=== List all vehicles ===");
        // used a for loop to display all the total number of vehicles in the array
        // note that i used the count of vehicles in the array as my boundary
        // java may throw an error if you use length because some space may not be initialized yet
        for (int i = 0; i < countOfVehicles; i++) {
            System.out.println(i + ". " + vehicleArray[i].getMakeModel());
        }
    }

    public static void searchByMakeAndModel(Vehicle[] vehicleArray, int countOfVehicles){
        System.out.print("\nEnter the Make/Model: ");
        String makeAndModel = input.nextLine().trim();
        String vehicleMake;
        String vehicleModel;
        int found = 0;

        // use a for loop to find the vehicle make and model
        // note that i used the count of vehicles in the array as my boundary
        // java may throw an error if you use length because some space may not be initialized yet
        for (int i = 0; i < countOfVehicles; i++) {
            // in case use enters only make or model
            // prepare vehicle make and model for checking
            // split returns a String array with index 0: make and index 1 model so we call it twice
            vehicleMake = vehicleArray[i].getMakeModel().split(" ")[0].trim();
            vehicleModel = vehicleArray[i].getMakeModel().split(" ")[1].trim();

            // if we find a match print the vehicle information
            if (makeAndModel.equalsIgnoreCase(vehicleArray[i].getMakeModel())) {
                found = 1;
                displayVehicleInformation(vehicleArray[i]);

            } else if (makeAndModel.equalsIgnoreCase(vehicleMake)) {
                found = 1;
                displayVehicleInformation(vehicleArray[i]);

            } else if (makeAndModel.equalsIgnoreCase(vehicleModel)) {
                found = 1;
                displayVehicleInformation(vehicleArray[i]);
            }
        }
        if (found == 0) {
            System.out.println("\n!!! NO VEHICLES FOUND !!!");
        }

    }

    public static void searchByPriceRange(Vehicle[] vehicleArray, int countOfVehicles) {
        System.out.print("\nEnter the Lowest Price: ");
        double lowPrice = input.nextDouble();
        // consume \n
        input.nextLine();
        System.out.print("\nEnter Highest Price: ");
        double highPrice = input.nextDouble();
        // consume \n
        input.nextLine();
        int found = 0;

        System.out.println("\n=== Vehicles between $" + lowPrice + " and $" + highPrice + " ===");
        // loop through the array and find vehicle prices between lowPrice and highPrice
        for (int i = 0; i < countOfVehicles; i++) {
            if (vehicleArray[i].getPrice() > lowPrice && vehicleArray[i].getPrice() < highPrice) {
                found = 1;
                displayVehicleInformation(vehicleArray[i]);
            }
        }
        if (found == 0) {
            System.out.println("\n!!! NO VEHICLES FOUND !!!");
        }
    }

    public static void searchByColor(Vehicle[] vehicleArray, int countOfVehicles) {
        System.out.print("\nEnter Vehicle Color: ");
        String vehicleColor = input.nextLine().trim();
        int found = 0;

        // loop through the array and find vehicle prices that the color equals vehicleColor
        for (int i = 0; i < countOfVehicles; i++) {
            if (vehicleColor.equalsIgnoreCase(vehicleArray[i].getColor())) {
                found = 1;
                displayVehicleInformation(vehicleArray[i]);
            }
        }

        if (found == 0) {
            System.out.println("\n!!! NO VEHICLES FOUND !!!");
        }

    }

    public static void addAVehicle(Vehicle[] vehicleArray, int countOfVehicles) {
        // first check whether vehicleArray is full
        if (countOfVehicles == 20) {
            System.out.println("\n===Dealership has reached Max Vehicle Capacity===");
            return;
        }
        System.out.println("\n=== Enter The New Vehicle information ===");
        System.out.print("Vehicle ID (Number 0 - 999999): ");
        long vehicleID = input.nextLong();
        // consume \n
        input.nextLine();

        System.out.print("Make and Model (Make Model): ");
        String makeModel = input.nextLine();

        System.out.print("Color: ");
        String color = input.nextLine();

        System.out.print("Odometer Reading (Number 0 - 1000000): ");
        int odometerReading = input.nextInt();
        // consume \n
        input.nextLine();

        System.out.print("Price: ");
        double price = input.nextDouble();
        // consume \n
        input.nextLine();

        Vehicle newVehicle = new Vehicle(vehicleID, makeModel, color, odometerReading, price);
        vehicleArray[countOfVehicles + 1] = newVehicle;
        countOfVehicles++;

        System.out.println("\n=== Vehicle Added Successfully ===");
        displayVehicleInformation(newVehicle);

    }

}
