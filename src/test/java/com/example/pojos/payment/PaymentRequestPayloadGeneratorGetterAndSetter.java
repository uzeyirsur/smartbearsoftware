package com.example.pojos.payment;

import com.dover.assesment.pojos.payment.request.*;
import com.example.pojos.payment.request.*;
import com.example.utilities.DateTimeStampGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;


import java.util.Map;
import java.util.Set;

public class PaymentRequestPayloadGeneratorGetterAndSetter {

    /*
    *
    * The PaymentRequestPayloadGeneratorGetterAndSetter.class implements a no-argument constructor and getter and
    * setter methods to pass values and manage the state of the object being constructed. Getter methods are used
    *  to retrieve the values of an object's properties, while setter methods are used to set the values of an object's
    *  properties.
    *
    * */

    /**
     * buildPayload the payment request payload from a values map.
     *
     * @param valuesMap HashMap containing the values for the payment request
     * @return Constructed JSON string for the pa yment request
     * @throws JsonProcessingException If an error occurs while converting the payment request object to a JSON string
     */
    public String buildPayload(Map<String, String> valuesMap) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Set<String> keys = valuesMap.keySet();

        PaymentRequest paymentRequest = null;
        String paymentDateStamp = null;

        String dbtr_countryCode = null;
        String dbtr_bank_bicNb = null;
        String dbtr_bank_routingNb = null;
        String dbtr_bank_nm = null;

        String dbtr_account_iban = null;
        String dbtr_account_bban = null;

        String cdtr_countryCode = null;
        String cdtr_bank_bicNb = null;
        String cdtr_bank_routingNb = null;
        String cdtr_bank_nm = null;
        String cdtr_account_iban = null;
        String cdtr_account_bban = null;

        String pymtinf_ccy = null;
        String pymtinf_amt = null;


        try {
            for (String key : keys) {
                if (key.contains("dbtr")) {
                    switch (key.trim()) {
                        case "dbtr.countryCode":
                            dbtr_countryCode = valuesMap.get(key);
                            break;
                        case "dbtr.bank.bicNb":
                            dbtr_bank_bicNb = valuesMap.get(key);
                            break;
                        case "dbtr.bank.routingNb":
                            dbtr_bank_routingNb = valuesMap.get(key);
                            break;
                        case "dbtr.bank.nm":
                            dbtr_bank_nm = valuesMap.get(key);
                            break;
                        case "dbtr.account.iban":
                            dbtr_account_iban = valuesMap.get(key);
                            break;
                        case "dbtr.account.bban":
                            dbtr_account_bban = valuesMap.get(key);
                            break;
                        default:
                            throw new IllegalArgumentException("Unexpedted Value : " + key.trim());
                    }
                } else if (key.contains("cdtr.")) {
                    switch (key.trim()) {
                        case "cdtr.countryCode":
                            cdtr_countryCode = valuesMap.get(key);
                            break;
                        case "cdtr.bank.bicNb":
                            cdtr_bank_bicNb = valuesMap.get(key);
                            break;
                        case "cdtr.bank.routingNb":
                            cdtr_bank_routingNb = valuesMap.get(key);
                            break;
                        case "cdtr.bank.nm":
                            cdtr_bank_nm = valuesMap.get(key);
                            break;
                        case "cdtr.account.iban":
                            cdtr_account_iban = valuesMap.get(key);
                            break;
                        case "cdtr.account.bban":
                            cdtr_account_bban = valuesMap.get(key);
                            break;

                        default:
                            throw new IllegalArgumentException("Unexpedted Value : " + key.trim());
                    }
                } else if (key.contains("pymtinf")) {
                    switch (key.trim()) {
                        case "pymtinf.ccy":
                            pymtinf_ccy = valuesMap.get(key);
                            break;
                        case "pymtinf.amt":
                            pymtinf_amt = valuesMap.get(key);
                            break;
                        default:
                            throw new IllegalArgumentException("Unexpedted Value : " + key.trim());
                    }
                } else {
                    if (key.trim().equalsIgnoreCase("paymentDateStamp") && valuesMap.get(key).equalsIgnoreCase("Dynamic_Value")) {
                        paymentDateStamp = DateTimeStampGenerator.getCurrentDateTime();
                    } else if (key.trim().equalsIgnoreCase("paymentDateStamp")) {
                        paymentDateStamp = valuesMap.get(key);
                    }
                }
            }

            Account dbtr_account = new Account();
            dbtr_account.setBban(dbtr_account_bban);
            dbtr_account.setIban(dbtr_account_iban);


            Bank dbtr_bank = new Bank();
            dbtr_bank.setNm(dbtr_bank_nm);
            dbtr_bank.setBicNb(dbtr_bank_bicNb);
            dbtr_bank.setRoutingNb(dbtr_bank_routingNb);


            Debtor dbtr = new Debtor();
            dbtr.setCountryCode(dbtr_countryCode);
            dbtr.setAccount(dbtr_account);
            dbtr.setBank(dbtr_bank);


            Account cdtr_account = new Account();
            cdtr_account.setBban(cdtr_account_bban);
            cdtr_account.setIban(cdtr_account_iban);

            Bank cdtr_bank = new Bank();
            cdtr_bank.setNm(cdtr_bank_nm);
            cdtr_bank.setBicNb(cdtr_bank_bicNb);
            cdtr_bank.setRoutingNb(cdtr_bank_routingNb);


            Creditor cdtr = new Creditor();
            cdtr.setCountryCode(cdtr_countryCode);
            cdtr.setAccount(cdtr_account);
            cdtr.setBank(cdtr_bank);


            PaymentInformation pymtinf = new PaymentInformation();
            pymtinf.setAmount(pymtinf_amt);
            pymtinf.setCurrencyCode(pymtinf_ccy);


            paymentRequest = new PaymentRequest();
            paymentRequest.setPaymentDateStamp(paymentDateStamp);
            paymentRequest.setDebtor(dbtr);
            paymentRequest.setCreditor(cdtr);
            paymentRequest.setPaymentInformation(pymtinf);

        } catch (
                Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }


        return objectMapper.writeValueAsString(paymentRequest);
    }

}
