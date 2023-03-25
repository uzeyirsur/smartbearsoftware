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
        "nm",
        "adr"
})
public class CreditorInformation {
    @JsonProperty("nm")
    private String nm;

    @JsonProperty("adr")
    private Address adr;
}
