package com.wat.transmitter.Map.Class;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class REFERENCE_BODY {
	private String tag;
	private Integer index;
	
	public REFERENCE_BODY(String tag, Integer index) {
		this.tag = tag;
		this.index = index;
	}
}
