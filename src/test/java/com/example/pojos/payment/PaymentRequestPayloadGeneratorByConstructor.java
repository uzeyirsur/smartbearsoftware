package com.example.pojos.payment;


import com.example.pojos.payment.request.PaymentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;


/**
 * Classes responsible for generating the payment request payload through its constructors.
 */
public class PaymentRequestPayloadGeneratorByConstructor {

    /*
    * The PaymentRequestPayloadGeneratorByConstructor.class uses a custom constructor to build the payload,
    *  leveraging the advantages of object constructors to create complex data structures in a concise and
    *  maintainable manner.
    *
    * */


    /**
     * Builds the payload for a payment request by using a constructor.
     *
     * @param valuesMap A map containing the payment request values.
     * @return A JSON string representation of the payment request.
     * @throws JsonProcessingException If the payment request cannot be converted to a JSON string.
     */
    public String buildPayload(Map<String, String> valuesMap) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        PaymentRequest paymentRequest = new PaymentRequest(valuesMap);
        return objectMapper.writeValueAsString(paymentRequest);
    }
}