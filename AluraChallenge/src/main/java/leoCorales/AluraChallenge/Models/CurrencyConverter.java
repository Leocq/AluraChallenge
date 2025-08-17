package leoCorales.AluraChallenge.Models;

import java.io.IOException;

public class CurrencyConverter {

    private final ApiClient apiClient;

    public CurrencyConverter(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public record ResultadoConversion(double valorConvertido, double tasaUsada) {}

    public ResultadoConversion convertWithTwoRequests(String from, String to, double amount)
            throws IOException, InterruptedException {

        String FROM = from.toUpperCase();
        String TO = to.toUpperCase();

        ExchangeResponse fromBase = apiClient.getLatestForBase(FROM);
        Double directRate = (fromBase.conversion_rates != null) ? fromBase.conversion_rates.get(TO) : null;

        ExchangeResponse toBase = apiClient.getLatestForBase(TO);
        Double inverseFromInTo = (toBase.conversion_rates != null) ? toBase.conversion_rates.get(FROM) : null;

        double rate;
        if (directRate != null) {
            rate = directRate;
        } else if (inverseFromInTo != null && inverseFromInTo != 0.0) {
            rate = 1.0 / inverseFromInTo;
        } else {
            throw new IOException("No se encontró tasa para convertir de " + FROM + " a " + TO);
        }

        return new ResultadoConversion(amount * rate, rate);
    }
}
