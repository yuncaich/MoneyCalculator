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
        add(new Currency("USD", "Dolar americano", "$"));
        add(new Currency("EUR", "Euro", "€"));
        add(new Currency("BRL", "Real brasileño", ""));
        add(new Currency("CAD", "Dolar canadiense", ""));
        add(new Currency("CHF", "Franco suizo", ""));
        add(new Currency("CNY", "Yuan chino", ""));
        add(new Currency("JPY", "Yen", ""));
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