package com.example.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import example.dao.RoomDAO;
import example.model.room;

public class HotelService {
    private RoomDAO dao = new RoomDAO();
    private Scanner scanner = new Scanner(System.in);

    public void start() throws SQLException {
        while (true) {
            System.out.println("\n1. View Rooms\n2. Book Room\n3. Exit");
            System.out.print("Enter choice: ");
            int ch = scanner.nextInt();
            scanner.nextLine();  // Clear buffer

            switch (ch) {
                case 1:
                    viewRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void viewRooms() throws SQLException {
        List<Room> rooms = dao.getAllRooms();
        for (Room r : rooms) {
            System.out.println(r);
        }
    }

    private void bookRoom() throws SQLException {
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        dao.bookRoom(roomNumber, name);
        System.out.println("Room booked!");
    }
}
