package com.example.consolodation.service.trip;

import com.example.consolodation.service.route.Route;
import com.example.consolodation.service.route.RouteService;
import com.example.consolodation.service.tap.model.TapRecord;
import com.example.consolodation.service.trip.model.TripRecord;
import com.example.consolodation.service.trip.model.TripStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TripRecordBuilder {

  private RouteService routeService;

  public TripRecordBuilder(RouteService routeService) {
    this.routeService = routeService;
  }

  public TripRecord incompleteTripStartingAt(TapRecord tapRecord) {
    TripRecord tripRecord = getTripRecordFromStartStop(tapRecord);

    tripRecord.setFinished(LocalDateTime.now());
    tripRecord.setDurationSecs(getDurationSecs(tapRecord.getDateTime(), LocalDateTime.now()));

    Route route = routeService.maxPriceForStop(tapRecord.getStopId());
    tripRecord.setToStopId(route.getEndStopId());
    tripRecord.setChargeAmount(route.getPrice());

    tripRecord.setStatus(TripStatus.INCOMPLETE);

    return tripRecord;
  }

  public TripRecord completedTrip(TapRecord previousRecord, TapRecord nextRecord) {
    TripRecord tripRecord = getTripRecordBetweenStops(previousRecord, nextRecord);

    tripRecord.setChargeAmount(routeService.priceBetweenStops(previousRecord.getStopId(), nextRecord.getStopId()));
    tripRecord.setStatus(TripStatus.COMPLETE);

    return tripRecord;
  }

  public TripRecord cancelledTrip(TapRecord previousRecord, TapRecord nextRecord) {
    TripRecord tripRecord = getTripRecordBetweenStops(previousRecord, nextRecord);

    tripRecord.setChargeAmount(BigDecimal.ZERO);
    tripRecord.setStatus(TripStatus.CANCELLED);

    return tripRecord;
  }

  private TripRecord getTripRecordBetweenStops(TapRecord previousRecord, TapRecord nextRecord) {
    TripRecord tripRecord = getTripRecordFromStartStop(previousRecord);
    tripRecord.setToStopId(nextRecord.getStopId());

    tripRecord.setFinished(nextRecord.getDateTime());
    tripRecord.setDurationSecs(getDurationSecs(previousRecord.getDateTime(), nextRecord.getDateTime()));
    return tripRecord;
  }

  private long getDurationSecs(LocalDateTime previousRecord, LocalDateTime nextRecord) {
    return nextRecord.toEpochSecond(ZoneOffset.UTC) - previousRecord.toEpochSecond(ZoneOffset.UTC);
  }

  private TripRecord getTripRecordFromStartStop(TapRecord previousRecord) {
    TripRecord tripRecord = new TripRecord();

    tripRecord.setFromStopId(previousRecord.getStopId());
    tripRecord.setBusId(previousRecord.getBusId());
    tripRecord.setCompanyId(previousRecord.getCompanyId());
    tripRecord.setStarted(previousRecord.getDateTime());
    tripRecord.setPrimaryAccountNumber(previousRecord.getPrimaryAccountNumber());

    return tripRecord;
  }
}
