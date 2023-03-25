package com.example.pojos.payment.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({
        "countryCode",
        "bank",
        "account"
})
public class Creditor {
    /**
     * The country code of the debtor.
     */
    @JsonProperty("countryCode")
    private String countryCode;

    /**
     * The bank details of the debtor.
     */
    @JsonProperty("bank")
    private Bank bank;

    /**
     * The account details of the debtor.
     */
    @JsonProperty("account")
    private Account account;

    /**
     * Constructor for initializing Debtor object from valuesMap
     *
     * @param valuesMap a map of key-value pairs where the key is the name of the field and the value is the field's value.
     */
    public Creditor(Map<String, String> valuesMap, String prefix) {
        this.countryCode = valuesMap.get(prefix + "countryCode");
        this.bank = new Bank(valuesMap, prefix + "bank.");
        this.account = new Account(valuesMap, prefix + "account.");
    }

}
