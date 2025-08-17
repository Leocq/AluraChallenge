package leoCorales.AluraChallenge.Models;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;   // HttpClient
import java.net.http.HttpRequest;  // HttpRequest
import java.net.http.HttpResponse; // HttpResponse
import java.time.Duration;

public class ApiClient {
    private final String baseUrl;
    private final String apiKey;
    private final HttpClient httpClient;
    private final Gson gson;

    public ApiClient(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.gson = new Gson();
    }

    public ExchangeResponse getLatestForBase(String baseCurrency) throws IOException, InterruptedException {
        // https://v6.exchangerate-api.com/v6/API_KEY/latest/USD
        String url = String.format("%s/%s/latest/%s", baseUrl, apiKey, baseCurrency);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(15))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("HTTP " + response.statusCode() + " al llamar " + url);
        }

        ExchangeResponse parsed = gson.fromJson(response.body(), ExchangeResponse.class);

        if (parsed == null || parsed.conversion_rates == null) {
            throw new IOException("Respuesta inválida de la API para base " + baseCurrency);
        }
        return parsed;
    }
}
