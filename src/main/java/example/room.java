package com.example.model;

public class room {
    private int roomNumber;
    private boolean isBooked;
    private String customerName;

    public room(int roomNumber, boolean isBooked, String customerName) {
        this.roomNumber = roomNumber;
        this.isBooked = isBooked;
        this.customerName = customerName;
    }

    // Getters and Setters

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " | Booked: " + isBooked + " | Customer: " + customerName;
    }
}