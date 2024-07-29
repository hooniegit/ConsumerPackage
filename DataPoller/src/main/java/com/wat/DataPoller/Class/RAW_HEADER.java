package com.wat.DataPoller.Class;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RAW_HEADER {
	// [etc.] Add JsonProperty for Data Matching
	@JsonProperty("LOCATION")
	private String LOCATION;
}
