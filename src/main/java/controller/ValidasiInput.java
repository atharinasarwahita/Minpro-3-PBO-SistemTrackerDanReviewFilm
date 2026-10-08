package controller;

import java.util.Scanner;

public class ValidasiInput {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputAngka(String pesan) {
            while (true) {
                System.out.print(pesan);
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Input tidak boleh kosong!");
                    continue;
                }

                try {
                    return Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Input harus berupa angka!");
                }
            }
        }

    public static double inputRating(String pesan) {
        while (true) {
            try {
                return Double.parseDouble(inputNonKosong(pesan));
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }

    public static String inputNonKosong(String pesan) {
        System.out.print(pesan);
        String input = scanner.nextLine();
        while (input.trim().isEmpty()) {
            System.out.println("Input tidak boleh kosong!");
            System.out.print(pesan);
            input = scanner.nextLine();
        }
        return input;
    }

    public static boolean inputKonfirmasi(String pesan) {
        while (true) {
            System.out.print(pesan);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("Y")) {
                return true;
            } else if (input.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Input tidak valid! Harap masukkan Y atau N.");
        }
    }
}