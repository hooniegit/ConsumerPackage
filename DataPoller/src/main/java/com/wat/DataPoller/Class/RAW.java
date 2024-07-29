package com.wat.DataPoller.Class;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RAW {
	// [etc.] Add JsonProperty for Data Matching
    @JsonProperty("HEADER")
	private RAW_HEADER HEADER;
    
    @JsonProperty("BODY")
	private List<RAW_BODY> BODY;
}
