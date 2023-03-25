package com.example.pojos.payment.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.util.Map;

/**
 * This class represents the bank details of a debtor or creditor in a payment request.
 * It contains the BIC number, routing number and name of the bank.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({
        "bicNb",
        "routingNb",
        "nm"
})
public class Bank {

    /**
     * The BIC (Bank Identifier Code) number of the bank.
     */
    @JsonProperty("bicNb")
    private String bicNb;

    /**
     * The routing number of the bank.
     */
    @JsonProperty("routingNb")
    private String routingNb;

    /**
     * The name of the bank.
     */
    @JsonProperty("nm")
    private String nm;

    /**
     * Constructor for creating an instance of the class from a map of values.
     *
     * @param valuesMap a map of key-value pairs where the key is the name of the field and the value is the field's value.
     */
    public Bank(Map<String, String> valuesMap, String prefix) {
        this.bicNb = valuesMap.get(prefix + "bicNb");
        this.routingNb = valuesMap.get(prefix + "routingNb");
        this.nm = valuesMap.get(prefix + "nm");
    }

}
