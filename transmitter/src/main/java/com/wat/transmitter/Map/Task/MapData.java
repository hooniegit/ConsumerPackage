package com.wat.transmitter.Map.Task;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.wat.transmitter.Map.Class.REFERENCE;
import com.wat.transmitter.Map.Class.REFERENCE_BODY;

public class MapData {
	
	
	private REFERENCE createReference(String csvFile) {
		REFERENCE reference = new REFERENCE();
		
		// [Task] Read CSV File & Create REFERENCE Instance
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
            	// [Parse] CSV Data
                String SOURCE = line[0];
                String TAG = line[1];
                int INDEX = Integer.parseInt(line[2]);

                // [Task] Create REFERENCE_BODY Instance & Add to REFERENCE
                REFERENCE_BODY body = new REFERENCE_BODY(TAG, INDEX);
                reference.add(SOURCE, body);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return reference;
    }
}
