package com.example.pojos.payment.request;

import com.example.utilities.DateTimeStampGenerator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * This class represents a PaymentRequest which contains information
 * about the payment date, debtor, creditor, and payment information.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({"paymentDateStamp",
        "dbtr",
        "cdtr",
        "pymtinf"
})
public class PaymentRequest {

    /**
     * The payment date stamp.
     */
    @JsonProperty("paymentDateStamp")
    private String paymentDateStamp;

    /**
     * The debtor of the payment.
     */
    @JsonProperty("dbtr")
    private Debtor debtor;

    /**
     * The creditor of the payment.
     */
    @JsonProperty("cdtr")
    private Creditor creditor;

    /**
     * The payment information.
     */
    @JsonProperty("pymtinf")
    private PaymentInformation paymentInformation;


    /**
     * Constructs a PaymentRequest object using the values in the provided valuesMap.
     *
     * @param valuesMap a map containing the payment date stamp, debtor, creditor, and payment information.
     */
    public PaymentRequest(Map<String, String> valuesMap) {
        String paymentDateStampString = valuesMap.get("paymentDateStamp");
        paymentDateStamp = paymentDateStampString.equalsIgnoreCase("Dynamic_Value") ? DateTimeStampGenerator.getCurrentDateTime() : paymentDateStampString;
        debtor = new Debtor(valuesMap, "dbtr.");
        creditor = new Creditor(valuesMap, "cdtr.");
        paymentInformation = new PaymentInformation(valuesMap, "pymtinf.");
    }

}
