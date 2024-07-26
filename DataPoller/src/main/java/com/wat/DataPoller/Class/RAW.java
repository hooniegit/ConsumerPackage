package com.wat.DataPoller.Class;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RAW {
    @JsonProperty("Header")
	private RAW_HEADER Header;
    
    @JsonProperty("Body")
	private List<RAW_BODY> Body;
}
