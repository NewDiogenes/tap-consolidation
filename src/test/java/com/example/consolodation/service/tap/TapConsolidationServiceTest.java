package com.example.consolodation.service.tap;

import com.example.consolodation.service.tap.model.TapRecord;
import com.example.consolodation.service.tap.model.TapType;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static junit.framework.TestCase.assertTrue;

public class TapConsolidationServiceTest {

  private TapConsolidationService tapConsolidationService;

  @Test
  public void consolidateTaps_shouldFilterOutErrorTaps() {
    TapRecord record = getTapRecord("stop", TapType.ERROR, 1L);

    tapConsolidationService.consolidateTaps(Stream.of(record))
        .test()
        .assertNoValues();
  }

  @Test
  public void consolidateTaps_shouldGroupTapsByPAN() {
    Long pan1 = 3456L;
    Long pan2 = 7894567L;

    tapConsolidationService.consolidateTaps(
        Stream.of(
            getTapRecord("stop", TapType.ON, pan1),
            getTapRecord("hi", TapType.ON, pan2),
            getTapRecord("hi", TapType.ON, pan2),
            getTapRecord("start", TapType.OFF, pan1),
            getTapRecord("boop", TapType.ON, pan1),
            getTapRecord("boop", TapType.OFF, pan2)
        ))
        .test()
        .assertValueCount(2)
        .assertOf(obs -> assertTrue(obs.values().stream().anyMatch(records -> pan1.equals(records.getKey()))))
        .assertOf(obs -> assertTrue(obs.values().stream().anyMatch(records -> pan2.equals(records.getKey()))));
  }

  private TapRecord getTapRecord(String stop, TapType tapType, Long pan) {
    TapRecord record = new TapRecord();

    record.setStopId(stop);
    record.setTapType(tapType);
    record.setPrimaryAccountNumber(pan);
    return record;
  }

  @Before
  public void setUp() {
    tapConsolidationService = new TapConsolidationService();
  }
}