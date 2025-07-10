package com.example;

import com.example.service.HotelService;

public class Main {
    public static void main(String[] args) {
        try {
            new HotelService().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}