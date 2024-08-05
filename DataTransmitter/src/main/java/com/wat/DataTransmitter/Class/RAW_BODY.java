package com.wat.DataTransmitter.Class;

import java.lang.reflect.Field;

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
    
    public String toStream() {
        StringBuilder result = new StringBuilder();
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (!field.isSynthetic() && !java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(this);
                    if (value != null) {
                        result.append(value.toString()).append(":");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        if (result.length() > 0) {
            result.setLength(result.length() - 1);
        }

        return result.toString();
    }
}

