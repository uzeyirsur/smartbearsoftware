package com.example.pojos.payment.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.Map;

/**
 * Class representing the Payment Information for a Payment Request
 */
@Data
@NoArgsConstructor
@Builder
@JsonPropertyOrder({
        "ccy",
        "amt"

})
public class PaymentInformation {


    /**
     * The currency code of the amount.
     */
    @JsonProperty("ccy")
    private String currencyCode;

    /**
     * The numerical value of the amount.
     */
    @JsonProperty("amt")
    private String amount;

    /**
     * Constructs a new PaymentInformation instance from a given currency code and amount.
     *
     * @param currencyCode the currency code of the amount
     * @param amount       the numerical value of the amount
     */
    public PaymentInformation(String currencyCode, String amount) {
        this.currencyCode = currencyCode;
        this.amount = amount;
    }

    /**
     * Constructs a new PaymentInformation instance from a map of values.
     *
     * @param valuesMap a map of key-value pairs where the key is the name of the field and the value is the field's value.
     */
    public PaymentInformation(Map<String, String> valuesMap, String prefix) {
        this.currencyCode = valuesMap.get(prefix + "ccy");
        this.amount = valuesMap.get(prefix + "amt");
    }
}