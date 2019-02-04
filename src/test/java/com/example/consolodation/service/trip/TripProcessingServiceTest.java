package com.example.consolodation.service.trip;

import com.example.consolodation.service.csv.CSVOutputService;
import com.example.consolodation.service.tap.model.TapRecord;
import com.example.consolodation.service.tap.model.TapType;
import com.example.consolodation.service.trip.model.TripRecord;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class TripProcessingServiceTest {

  private TripProcessingService tripProcessingService;

  @Mock
  private CSVOutputService csvOutputService;
  @Mock
  private TripRecordBuilder tripBuilder;

  @Test
  public void writeTripToFile_shouldreturnSecondTapArgument() {
    TapRecord record = new TapRecord();
    record.setStopId("stop");
    record.setTapType(TapType.ON);

    assertEquals(record, tripProcessingService.writeTripToFile(null, record));
  }



  @Test
  public void givenAnOnTapFollowedByAnOffTap_writeTripToFile_shouldCallTripBuilderForC0mpletedTrip() {
    TapRecord startRecord = new TapRecord();
    startRecord.setStopId("stop");
    startRecord.setTapType(TapType.ON);

    TapRecord endRecord = new TapRecord();
    endRecord.setStopId("other stop");
    endRecord.setTapType(TapType.OFF);

    when(tripBuilder.completedTrip(any(), any())).thenReturn(new TripRecord());
    tripProcessingService.writeTripToFile(startRecord, endRecord);

    verify(tripBuilder).completedTrip(eq(startRecord), eq(endRecord));
  }

  @Test
  public void givenAnOnTapFollowedByAnotherOnTap_writeTripToFile_shouldCallTripBuilderForIncompleteTrip() {
    TapRecord startRecord = new TapRecord();
    startRecord.setStopId("stop");
    startRecord.setTapType(TapType.ON);

    TapRecord endRecord = new TapRecord();
    endRecord.setStopId("other stop");
    endRecord.setTapType(TapType.ON);

    when(tripBuilder.incompleteTripStartingAt(any())).thenReturn(new TripRecord());
    tripProcessingService.writeTripToFile(startRecord, endRecord);

    verify(tripBuilder).incompleteTripStartingAt(eq(startRecord));
  }

  @Test
  public void givenAnOnTapFollowedByAnOffTapAtTheSameStop_writeTripToFile_shouldCallTripBuilderForCancelledTrip() {
    TapRecord startRecord = new TapRecord();
    startRecord.setStopId("stop");
    startRecord.setTapType(TapType.ON);

    TapRecord endRecord = new TapRecord();
    endRecord.setStopId("stop");
    endRecord.setTapType(TapType.OFF);

    when(tripBuilder.cancelledTrip(any(), any())).thenReturn(new TripRecord());
    tripProcessingService.writeTripToFile(startRecord, endRecord);

    verify(tripBuilder).cancelledTrip(eq(startRecord), eq(endRecord));
  }

  @Test
  public void givenAPreviousTapThatWasNotAnOnTap_writeTripToFile_shouldNotCallTheTripBuilder() {
    TapRecord previousRecord = new TapRecord();
    previousRecord.setStopId("stop");
    previousRecord.setTapType(TapType.OFF);

    TapRecord nextRecord = new TapRecord();
    nextRecord.setStopId("other stop");
    nextRecord.setTapType(TapType.ON);

    tripProcessingService.writeTripToFile(previousRecord, nextRecord);

    verify(tripBuilder, times(0)).completedTrip(eq(previousRecord), eq(nextRecord));
  }


  @Test
  public void givenATripRecordIsCreated_writeTripToFile_shouldPassTheTripRecordToCSVOutputService() {
    TapRecord previousRecord = new TapRecord();
    previousRecord.setStopId("stop");
    previousRecord.setTapType(TapType.ON);

    TapRecord nextRecord = new TapRecord();
    nextRecord.setStopId("other stop");
    nextRecord.setTapType(TapType.OFF);

    TripRecord record = new TripRecord();
    when(tripBuilder.completedTrip(any(), any())).thenReturn(record);

    tripProcessingService.writeTripToFile(previousRecord, nextRecord);

    verify(csvOutputService).writeToFile(eq(record));
  }


  @Before
  public void setUp() {
    initMocks(this);

    tripProcessingService = new TripProcessingService(csvOutputService, tripBuilder);
  }
}