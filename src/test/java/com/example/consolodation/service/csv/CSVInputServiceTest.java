package com.example.consolodation.service.csv;

import com.example.consolodation.service.csv.CSVInputService;
import com.example.consolodation.service.tap.model.TapRecord;
import com.example.consolodation.service.tap.TapRecordParserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CSVInputServiceTest {
  private final String testDataPath = "src/test/resources/data/inputReaderTestData.csv";

  @Mock
  private TapRecordParserService tapRecordParserService;

  private CSVInputService csvInputService;

  @Test(expected = IOException.class)
  public void whenAnArgumentThatIsNotAFileName_getReader_shouldThrowException() throws IOException {
    String fakeFilePath = "notAFile";
    csvInputService.readFromFile(fakeFilePath);
  }

  @Test
  public void whenGivenAnArgumentThatIsAFileName_readFromFile_shouldPassEachRecordToTapRecordParserService() throws IOException {
    String recordOne = "1, 22-01-2018 13:00:00, ON, Stop1, Company1, Bus37, 5500005555555559";
    String recordTwo = "2, 22-01-2018 13:05:00, OFF, Stop2, Company1, Bus37, 5500005555555559";

    csvInputService.readFromFile(testDataPath).collect(Collectors.toList()); //Need to invoke something on the return to ensure the code completes

    verify(tapRecordParserService).parseFromString(eq(recordOne));
    verify(tapRecordParserService).parseFromString(eq(recordTwo));
  }

  @Before
  public void setUp() {
    initMocks(this);
    when(tapRecordParserService.parseFromString(any())).thenReturn(new TapRecord());
    csvInputService = new CSVInputService(tapRecordParserService);
  }
}