package moneycalculator.Model;

/**
 *
 * @author yuncai
 */

import java.util.HashMap;
import java.util.Map;

public class CurrencyList {

    private final Map<String, Currency> currencies = new HashMap<>();

    public CurrencyList() {
        add(new Currency("USD", "USD", "$"));
        add(new Currency("EUR", "Euro", "€"));
        add(new Currency("BRL", "Brazilian Real", "R$"));
        add(new Currency("CAD", "Canadian dollar", "C$"));
        add(new Currency("CHF", "‎CHF", "SFr"));
        add(new Currency("CNY", "Chinese yuan", "‎¥"));
        add(new Currency("JPY", "Yen", "JP¥"));
    }

    private void add(Currency currency) {
        currencies.put(currency.getCode(), currency);
    }

    public Currency get(String code) {
        return currencies.get(code.toUpperCase());
    }
    
    public Map<String, Currency> getCurrencyList(){
        return currencies;
    }
}