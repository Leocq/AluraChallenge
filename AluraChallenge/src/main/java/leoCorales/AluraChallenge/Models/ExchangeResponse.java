package leoCorales.AluraChallenge.Models;

import java.util.Map;

public class ExchangeResponse {
    public String result;                   // "success" o "error"
    public String base_code;                // ejemplo USD "USD"
    public Map<String, Double> conversion_rates; // emeplo  {"ARS": 1000.12, "BRL": 5.42, ...}
}
