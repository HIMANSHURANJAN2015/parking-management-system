package util;

import model.constant.ParkingSpotType;
import model.constant.VehicleType;

import java.util.Scanner;

public class InputUtils {

    // Method to validate and collect an integer input
    public static int getValidInt(Scanner sc, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = sc.nextLine();
            try {
                return Integer.parseInt(input); // Attempt to parse as integer
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        }
    }

    // Method to validate and collect an long input
    public static long getValidLong(Scanner sc, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = sc.nextLine();
            try {
                return Long.parseLong(input); // Attempt to parse as integer
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid long.");
            }
        }
    }

    // Method to validate and collect a string input
    public static String getValidString(Scanner sc, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = sc.nextLine().trim(); // Trim whitespace
            if (!input.isEmpty()) {
                return input; // Accept non-empty input
            } else {
                System.out.println("Invalid input! Please enter a non-empty string.");
            }
        }
    }

    public static ParkingSpotType getValidParkingSpotType(Scanner sc, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = sc.nextLine();
            try {
                return ParkingSpotType.valueOf(input); // Attempt to parse as ParkingSpotType
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid ParkingSpotType.");
            }
        }
    }

    public static VehicleType getValidVehicleType(Scanner sc, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = sc.nextLine();
            try {
                return VehicleType.valueOf(input); // Attempt to parse as VehicleType
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid VehicleType.");
            }
        }
    }
}
