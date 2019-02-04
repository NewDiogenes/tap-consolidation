package com.example.consolodation;

import com.example.consolodation.service.csv.CSVInputService;
import com.example.consolodation.service.csv.CSVOutputService;
import com.example.consolodation.service.route.RouteService;
import com.example.consolodation.service.tap.TapConsolidationService;
import com.example.consolodation.service.tap.TapRecordParserService;
import com.example.consolodation.service.trip.TripProcessingService;
import com.example.consolodation.service.trip.TripRecordBuilder;

import java.io.IOException;

public class TripConsolidationApplication {

  public static void main(String[] args) throws IOException {
    CSVInputService csvInputService = new CSVInputService(new TapRecordParserService());
    TapConsolidationService tapConsolidationService = new TapConsolidationService();

    TripProcessingService tripProcessingService = new TripProcessingService(new CSVOutputService(),
        new TripRecordBuilder(new RouteService()));

    tapConsolidationService
        .consolidateTaps(csvInputService.readFromFile(args[0]))
        .flatMap(records -> records.scan(tripProcessingService::writeTripToFile))
        .subscribe();
  }
}
