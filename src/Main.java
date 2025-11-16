package hotel.Cli;

import hotel.domain.HotelManager;
import hotel.ExceptionHandling.*;
import hotel.Model.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static HotelManager manager;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        manager = new HotelManager();
        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = getIntInput("Chọn chức năng: ");
