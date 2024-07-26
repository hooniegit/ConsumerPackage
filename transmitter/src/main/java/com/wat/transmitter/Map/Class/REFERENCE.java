package com.wat.transmitter.Map.Class;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class REFERENCE {
    private Map<String, List<REFERENCE_BODY>> map;

    public REFERENCE() {
        this.map = new HashMap<>();
    }

    public void add(String location, REFERENCE_BODY body) {
    	map.computeIfAbsent(location, k -> new ArrayList<>()).add(body);
    }
}
