package com.wat.transmitter.Poll.Class;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RAW_BODY {
    @JsonProperty("TAG")
    private String TAG;
    
    @JsonProperty("VALUE")
    private String VALUE;
    
    @JsonProperty("TIMESTAMP")
    private String TIMESTAMP;
}
