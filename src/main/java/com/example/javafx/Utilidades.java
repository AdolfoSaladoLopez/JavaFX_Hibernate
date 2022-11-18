package com.example.javafx;

import java.util.Scanner;

public class Utilidades {
    public static Scanner escaner() {
        return new Scanner(System.in);
    }

    public static void limpiarPantalla() {
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }

    public static void pulsarTeclaContinuar() {
        String seguir;

        Scanner teclado = new Scanner(System.in);

        System.out.println("\nPulsa una tecla para continuar...");
        try {
            seguir = teclado.nextLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
