package com.example.consolodation.service.trip;

import com.example.consolodation.service.csv.CSVOutputService;
import com.example.consolodation.service.tap.model.TapRecord;
import com.example.consolodation.service.tap.model.TapType;
import com.example.consolodation.service.trip.model.TripRecord;

import java.util.Optional;
import java.util.function.Function;

public class TripProcessingService {
  private CSVOutputService csvOutputService;
  private TripRecordBuilder tripBuilder;

  public TripProcessingService(CSVOutputService csvOutputService, TripRecordBuilder tripBuilder) {
    this.csvOutputService = csvOutputService;
    this.tripBuilder = tripBuilder;
  }

  public TapRecord writeTripToFile(TapRecord previous, TapRecord next) {
    Optional.ofNullable(previous)
        .filter(record -> TapType.ON.equals(record.getTapType()))
        .flatMap(closeTripWith(next))
        .ifPresent(csvOutputService::writeToFile);
    return next;
  }

  private Function<TapRecord, Optional<TripRecord>> closeTripWith(TapRecord nextRecord) {
    return previousRecord -> {
      switch (nextRecord.getTapType()) {
        case ON:
          return Optional.of(tripBuilder.incompleteTripStartingAt(previousRecord));
        case OFF:
          return Optional.of(nextRecord.getStopId().equals(previousRecord.getStopId()) ?
              tripBuilder.cancelledTrip(previousRecord, nextRecord) : tripBuilder.completedTrip(previousRecord, nextRecord));
        default:
          return Optional.empty();
      }
    };
  }
}
