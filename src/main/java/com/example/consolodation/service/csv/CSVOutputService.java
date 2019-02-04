package com.example.consolodation.service.csv;

import com.example.consolodation.service.trip.model.TripRecord;

public class CSVOutputService {

  /**
   * Pretend that this is writing to an file...
   */
  public void writeToFile(TripRecord record) {
    System.out.println(record.toString());
  }
}
