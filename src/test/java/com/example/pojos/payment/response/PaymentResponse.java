package com.example.pojos.payment.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({
        "status",
        "cdtrInf"

})
public class PaymentResponse {

    @JsonProperty ("status")
    private String status;
    @JsonProperty ("cdtrInf")
    private CreditorInformation cdtrInf;


}
