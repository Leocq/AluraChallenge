package leoCorales.AluraChallenge.Main;

import leoCorales.AluraChallenge.Models.ApiClient;
import leoCorales.AluraChallenge.Models.CurrencyConverter;

import java.util.Scanner;

public class Main {

    private static final String API_KEY = "61135f8f5d25b312a72530a6"; // Por favor reemplazar con una Key valida
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6";

    private static final String USD = "USD";
    private static final String ARS = "ARS";
    private static final String BRL = "BRL";
    private static final String COP = "COP";

    public static void main(String[] args) {
        var apiClient = new ApiClient(BASE_URL, API_KEY);
        var converter = new CurrencyConverter(apiClient);
        var scanner = new Scanner(System.in);

        boolean seguir = true;

        while (seguir) {
            mostrarMenu();

            int opcion = leerOpcionValida(scanner);

            if (opcion == 7) {
                System.out.println("Saliendo... ¡Gracias por usar el conversor!");
                seguir = false;
                continue;
            }

            double monto = leerMontoValido(scanner);

            switch (opcion) {
                case 1 -> convertir(converter, USD, ARS, monto);
                case 2 -> convertir(converter, ARS, USD, monto);
                case 3 -> convertir(converter, USD, BRL, monto);
                case 4 -> convertir(converter, BRL, USD, monto);
                case 5 -> convertir(converter, USD, COP, monto);
                case 6 -> convertir(converter, COP, USD, monto);
                default -> System.out.println("Opción fuera de rango. (Esto no debería ocurrir).");
            }

            System.out.println();
        }
    }

    private static void mostrarMenu() {
        System.out.println("""
                ==============================
                CONVERSOR DE MONEDAS (ALURA)
                ==============================3
                1) Dólar -> Peso Argentino
                2) Peso Argentino-> Dólar
                3) Dólar -> Real Brasilero
                4) Real Brasilero -> Dólar
                5) Dólar -> Peso colombiano
                6) Peso colombiano -> Dólar
                7) Salir
                """);
    }

    private static int leerOpcionValida(Scanner scanner) {
        while (true) {
            System.out.print("Elija una opción válida: ");
            String opcionStr = scanner.nextLine().trim();

            try {
                int opcion = Integer.parseInt(opcionStr);
                if (opcion >= 1 && opcion <= 7) {
                    return opcion;
                } else {
                    System.out.println("Opción inválida. Por favor, ingrese un número entre 1 y 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no numérica. Por favor, ingrese un número entre 1 y 7.");
            }
        }
    }

    private static double leerMontoValido(Scanner scanner) {
        while (true) {
            System.out.print("Ingrese el monto a convertir: ");
            String montoStr = scanner.nextLine().trim().replace(",", ".");
            try {
                double monto = Double.parseDouble(montoStr);
                if (monto < 0) {
                    System.out.println("El monto no puede ser negativo. Intente nuevamente.");
                } else {
                    return monto;
                }
            } catch (NumberFormatException e) {
                System.out.println("Monto inválido. Intente nuevamente (use punto o coma para decimales).");
            }
        }
    }

    private static void convertir(CurrencyConverter converter, String from, String to, double amount) {
        try {
            CurrencyConverter.ResultadoConversion resultado = converter.convertWithTwoRequests(from, to, amount);
            System.out.printf("El resultado de la conversion es:  %.2f %s = %.2f %s%n", amount, from, resultado.valorConvertido(), to);
            System.out.printf("   Tasa utilizada: 1 %s = %.4f %s%n", from, resultado.tasaUsada(), to);
        } catch (Exception e) {
            System.out.println("Ocurrió un error al convertir: " + e.getMessage());
        }
    }
}
