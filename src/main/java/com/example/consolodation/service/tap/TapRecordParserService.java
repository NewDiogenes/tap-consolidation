package com.example.consolodation.service.tap;

import com.example.consolodation.service.tap.model.TapRecord;
import com.example.consolodation.service.tap.model.TapType;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TapRecordParserService {

  public TapRecord parseFromString(String tapRecordString) {
    TapRecord tapRecord = new TapRecord();
    String[] recordValues = tapRecordString.split(", ");

    tapRecord.setTapRecordId(Long.parseLong(recordValues[0]));
    tapRecord.setDateTime(parseValueToUTCLocalDateTime(recordValues[1]));

    tapRecord.setTapType(parseTapType(recordValues[2]));
    tapRecord.setStopId(recordValues[3]);
    tapRecord.setCompanyId(recordValues[4]);
    tapRecord.setBusId(recordValues[5]);
    tapRecord.setPrimaryAccountNumber(Long.parseLong(recordValues[6]));
    return tapRecord;
  }

  private TapType parseTapType(String typeValue) {
    switch (typeValue.toLowerCase()) {
      case "on":
        return TapType.ON;
      case "off":
        return TapType.OFF;
      default:
        return TapType.ERROR;
    }
  }

  private LocalDateTime parseValueToUTCLocalDateTime(String recordValue) {
    return LocalDateTime.parse(recordValue, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
        .atOffset(ZoneOffset.UTC).toLocalDateTime();
  }
}
