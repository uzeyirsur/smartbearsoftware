package com.example.pojos.payment;

import com.dover.assesment.pojos.payment.request.*;
import com.example.pojos.payment.request.*;
import com.example.utilities.DateTimeStampGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.Map;


/**
 * Class responsible for generating the payment request payload using lombok-builder.
 * <p>
 * Note : Difference between PaymentRequestPayloadGeneratorByUsingBuilders and PaymentRequestPayloadGeneratorByUsingBuildersVersion2
 * <p>
 * The process of generating a payment request payload can be complex and error-prone.
 * This class provides a simple and straightforward approach to constructing the payload.
 * By dividing the process into smaller parts, it ensures that the code is easy to understand, maintain and debug.
 * <p/>
 */
public class PaymentRequestPayloadGeneratorByUsingBuildersVersion2 {
    /**
     * Builds the payment request payload by mapping the values in the provided map to the corresponding fields.
     *
     * @param valuesMap A map containing the payment request values.
     * @return A JSON string representation of the payment request.
     * @throws JsonProcessingException If the payment request cannot be converted to a JSON string.
     */
    public String buildPayload(Map<String, String> valuesMap) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .debtor(mapDebtor(valuesMap))
                .creditor(mapCreditor(valuesMap))
                .paymentInformation(mapPaymentInformation(valuesMap))
                .paymentDateStamp(mapPaymentDateStamp(valuesMap))
                .build();
        return objectMapper.writeValueAsString(paymentRequest);
    }

    /**
     * Maps the values in the provided map to the fields of the debtor.
     *
     * @param valuesMap A map containing the payment request values.
     * @return A debtor object.
     */
    private Debtor mapDebtor(Map<String, String> valuesMap) {
        return Debtor.builder()
                .countryCode(valuesMap.get("dbtr.countryCode"))
                .account(mapAccount(valuesMap, "dbtr.account"))
                .bank(mapBank(valuesMap, "dbtr.bank"))
                .build();
    }

    /**
     * Maps the values in the provided map to the fields of the creditor.
     *
     * @param valuesMap A map containing the payment request values.
     * @return A creditor object.
     */
    private Creditor mapCreditor(Map<String, String> valuesMap) {
        return Creditor.builder()
                .countryCode(valuesMap.get("cdtr.countryCode"))
                .account(mapAccount(valuesMap, "cdtr.account"))
                .bank(mapBank(valuesMap, "cdtr.bank"))
                .build();
    }

    /**
     * Maps the values in the provided map to the fields of the account.
     *
     * @param valuesMap A map containing the payment request values.
     * @param prefix    The prefix used to identify the values in the map.
     * @return An account object.
     */
    private Account mapAccount(Map<String, String> valuesMap, String prefix) {
        return Account.builder()
                .iban(valuesMap.get(prefix + ".iban"))
                .bban(valuesMap.get(prefix + ".bban"))
                .build();
    }

    /**
     * Maps the values in the provided map to the fields of the bank.
     *
     * @param valuesMap A map containing the payment request values.
     * @param prefix    The prefix used to identify the values in the map.
     * @return A bank object.
     */
    private Bank mapBank(Map<String, String> valuesMap, String prefix) {
        return Bank.builder()
                .nm(valuesMap.get(prefix + ".nm"))
                .bicNb(valuesMap.get(prefix + ".bicNb"))
                .routingNb(valuesMap.get(prefix + ".routingNb"))
                .build();
    }


    /**
     * Maps the values in the provided map to the fields of the paymentInformation.
     *
     * @param valuesMap A map containing the payment request values.
     * @return A paymentInformation object.
     */
    private PaymentInformation mapPaymentInformation(Map<String, String> valuesMap) {
        return PaymentInformation.builder()
                .currencyCode(valuesMap.get("pymtinf.ccy"))
                .amount(valuesMap.get("pymtinf.amt"))
                .build();
    }


    /**
     Maps the payment date stamp either from the input map or generates a dynamic value.
     @param valuesMap Map containing key-value pairs of payment request details
     @return Mapped payment date stamp value
     */
    private String mapPaymentDateStamp(Map<String, String> valuesMap) {
        String paymentDateStamp = valuesMap.get("paymentDateStamp");
        return paymentDateStamp.equalsIgnoreCase("Dynamic_Value")
                ? DateTimeStampGenerator.getCurrentDateTime()
                : paymentDateStamp;
    }
}