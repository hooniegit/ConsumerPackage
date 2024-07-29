package com.wat.DataPoller.Class;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RAW_BODY {
	// [etc.] Add JsonProperty for Data Matching
	@JsonProperty("SOURCE")
	private String SOURCE;
	
    @JsonProperty("TAG")
    private String TAG;
    
    @JsonProperty("VALUE")
    private String VALUE;
    
    @JsonProperty("TIMESTAMP")
    private String TIMESTAMP;
}
