package com.example.pojos.payment;

import com.dover.assesment.pojos.payment.request.*;
import com.example.pojos.payment.request.*;
import com.example.utilities.DateTimeStampGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;


/**
 * Generates a payment request payload using lombok-builders.
 */
public class PaymentRequestPayloadGeneratorByUsingBuilders {

    /*
     * The PaymentRequestPayloadGeneratorByUsingBuilders class implements the Lombok Library Builders
     * to demonstrate the benefits of using code generators in constructing complex payloads.
     * Builders are a design pattern commonly used in object-oriented programming that offer a flexible and
     * convenient way to build objects in a readable and maintainable way. Lombok is a popular library for
     * Java that automates the generation of common boilerplate code, such as constructors, getters, setters,
     * and builders.
     * */


    /**
     * Builds a payload using the provided values map.
     *
     * @param valuesMap The values to use in building the payload.
     * @return The generated payload as a JSON string.
     * @throws JsonProcessingException If an error occurs while writing the JSON string.
     */
    public String buildPayload(Map<String, String> valuesMap) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        PaymentRequest.PaymentRequestBuilder builder = PaymentRequest.builder();

        builder.debtor(Debtor.builder()
                .countryCode(valuesMap.get("dbtr.countryCode"))
                .account(Account.builder()
                        .iban(valuesMap.get("dbtr.account.iban"))
                        .bban(valuesMap.get("dbtr.account.bban"))
                        .build())
                .bank(Bank.builder()
                        .nm(valuesMap.get("dbtr.bank.nm"))
                        .bicNb(valuesMap.get("dbtr.bank.bicNb"))
                        .routingNb(valuesMap.get("dbtr.bank.routingNb"))
                        .build())
                .build());

        builder.creditor(Creditor.builder()
                .countryCode(valuesMap.get("cdtr.countryCode"))
                .account(Account.builder()
                        .iban(valuesMap.get("cdtr.account.iban"))
                        .bban(valuesMap.get("cdtr.account.bban"))
                        .build())
                .bank(Bank.builder()
                        .nm(valuesMap.get("cdtr.bank.nm"))
                        .bicNb(valuesMap.get("cdtr.bank.bicNb"))
                        .routingNb(valuesMap.get("cdtr.bank.routingNb"))
                        .build())
                .build());

        builder.paymentInformation(PaymentInformation.builder()
                .currencyCode(valuesMap.get("pymtinf.ccy"))
                .amount(valuesMap.get("pymtinf.amt"))
                .build());

        builder.paymentDateStamp(valuesMap.get("paymentDateStamp").equalsIgnoreCase("Dynamic_Value")
                ? DateTimeStampGenerator.getCurrentDateTime() :
                valuesMap.get("paymentDateStamp"));


        PaymentRequest paymentRequest = builder.build();

        return objectMapper.writeValueAsString(paymentRequest);

    }
}