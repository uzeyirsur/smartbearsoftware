package com.example.pojos.payment.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * A simple value object class that represents a bank account.
 */
@Data
@NoArgsConstructor
@Builder
@JsonPropertyOrder({"iban", "bban"})
public class Account {

    /**
     * The International Bank Account Number (IBAN) of the account.
     */
    @JsonProperty("iban")
    private String iban;

    /**
     * The Basic Bank Account Number (BBAN) of the account.
     */
    @JsonProperty("bban")
    private String bban;

    /**
     * Constructs a new Account instance from a given IBAN and BBAN.
     *
     * @param iban the International Bank Account Number of the account
     * @param bban the Basic Bank Account Number of the account
     */
    public Account(String iban, String bban) {
        this.iban = iban;
        this.bban = bban;
    }


    /**
     * Constructor for creating an instance of the class from a map of values.
     *
     * @param valuesMap a map of key-value pairs where the key is the name of the field and the value is the field's value.
     */
    public Account(Map<String, String> valuesMap, String prefix) {
        this.iban = valuesMap.get(prefix + "iban");
        this.bban = valuesMap.get(prefix + "bban");
    }
}
