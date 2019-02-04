package com.example.consolodation.service.tap;

import com.example.consolodation.service.tap.model.TapType;
import com.example.consolodation.service.tap.TapRecordParserService;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class TapRecordParserServiceTest {

  private TapRecordParserService tapRecordParserService;

  @Test
  public void givenRecordStringWithNumericIdInFirstValue_parseFromString_shouldMapValueToTapRecordId() {
    Long expectedId = 1L;
    String testData = "1, 22-01-2018 13:00:00, ON, Stop1, Company1, Bus37, 5500005555555559";

    assertEquals(expectedId, tapRecordParserService.parseFromString(testData).getTapRecordId());
  }

  @Test
  public void givenRecordStringWithUTCDateTimeInSecondValue_parseFromString_shouldMapValueToTapDateTime() {
    LocalDateTime expectedDateTime = LocalDateTime.of(2018, 1, 22, 13, 0);
    String testData = "1, 22-01-2018 13:00:00, ON, Stop1, Company1, Bus37, 5500005555555559";

    assertEquals(expectedDateTime, tapRecordParserService.parseFromString(testData).getDateTime());
  }

  @Test
  public void givenRecordStringWithONTapTypeInThirdValue_parseFromString_shouldMapRecordTapTypeToOn() {
    TapType expectedType = TapType.ON;
    String testData = "1, 22-01-2018 13:00:00, ON, Stop1, Company1, Bus37, 5500005555555559";

    assertEquals(expectedType, tapRecordParserService.parseFromString(testData).getTapType());
  }

  @Test
  public void givenRecordStringWithOFFTapTypeInThirdValue_parseFromString_shouldMapRecordTapTypeToOFF() {
    TapType expectedType = TapType.OFF;
    String testData = "1, 22-01-2018 13:00:00, OFF, Stop1, Company1, Bus37, 5500005555555559";

    assertEquals(expectedType, tapRecordParserService.parseFromString(testData).getTapType());
  }

  @Test
  public void givenRecordStringWithAValueOtherThatONOrOFFTapTypeInThirdValue_parseFromString_shouldMapRecordTapTypeToERROR() {
    TapType expectedType = TapType.ERROR;
    String testData = "1, 22-01-2018 13:00:00, something-else, Stop1, Company1, Bus37, 5500005555555559";

    assertEquals(expectedType, tapRecordParserService.parseFromString(testData).getTapType());
  }

  @Test
  public void givenRecordStringWithStopIdInTheFourthValue_parseFromString_shouldMapValueToTapStopId() {
    String expectedStopId = "Stop1";
    String testData = "1, 22-01-2018 13:00:00, something-else, Stop1, Company1, Bus37, 5500005555555559";

    assertEquals(expectedStopId, tapRecordParserService.parseFromString(testData).getStopId());
  }

  @Test
  public void givenRecordStringWithCompanyIdInTheFifthValue_parseFromString_shouldMapValueToTapCompanyId() {
    String expectedCompanyId = "Company1";
    String testData = "1, 22-01-2018 13:00:00, something-else, Stop1, Company1, Bus37, 5500005555555559";

    assertEquals(expectedCompanyId, tapRecordParserService.parseFromString(testData).getCompanyId());
  }

  @Test
  public void givenRecordStringWithBusIdInTheSixthValue_parseFromString_shouldMapValueToTapBusIs() {
    String expectedBusId = "Bus37";
    String testData = "1, 22-01-2018 13:00:00, something-else, Stop1, Company1, Bus37, 5500005555555559";

    assertEquals(expectedBusId, tapRecordParserService.parseFromString(testData).getBusId());
  }

  @Test
  public void givenRecordStringWithPANInTheSixthValue_parseFromString_shouldMapValueToPrimaryAccountNumber() {
    Long expectedPAN = 5500005555555559L;
    String testData = "1, 22-01-2018 13:00:00, something-else, Stop1, Company1, Bus37, 5500005555555559";

    assertEquals(expectedPAN, tapRecordParserService.parseFromString(testData).getPrimaryAccountNumber());
  }

  @Before
  public void setUp() {
    tapRecordParserService = new TapRecordParserService();
  }
}