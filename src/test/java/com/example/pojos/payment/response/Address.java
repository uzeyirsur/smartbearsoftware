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
        "crty",
        "city",
        "pstcd",
        "bldNb",

})
public class Address {
    @JsonProperty("crty")
    private String crty;
    @JsonProperty("city")
    private String city;
    @JsonProperty("pstcd")
    private String pstcd;
    @JsonProperty("bldNb")
    private String bldNb;
}
