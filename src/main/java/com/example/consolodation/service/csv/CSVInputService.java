package com.example.consolodation.service.csv;

import com.example.consolodation.service.tap.TapRecordParserService;
import com.example.consolodation.service.tap.model.TapRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CSVInputService {

  private TapRecordParserService tapRecordParserService;

  public CSVInputService(TapRecordParserService tapRecordParserService) {
    this.tapRecordParserService = tapRecordParserService;
  }

  public Stream<TapRecord> readFromFile(String fileName) throws IOException {
    return Files.lines(Paths.get(fileName))
        .map(tapRecordParserService::parseFromString);
  }
}
