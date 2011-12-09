package com.sa.mt.options.pipeline;

import com.sa.mt.options.domain.Instrument;
import com.sa.mt.options.loader.InstrumentLoader;
import com.sa.mt.options.parser.InstrumentCsvParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(InstrumentPipeline.class)
public class InstrumentPipelineTest {

    @Mock
    private InstrumentLoader instrumentLoader;
    @Mock
    private InstrumentCsvParser instrumentCsvParser;

    private InstrumentPipeline pipeline;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        pipeline = new InstrumentPipeline(instrumentCsvParser, instrumentLoader);
    }

    @Test
      public void shouldStoreData() throws Exception {
        File mockFile = mock(File.class);
        whenNew(File.class).withArguments(anyString()).thenReturn(mockFile);
        when(mockFile.list()).thenReturn(new String[] {"File1.csv", "File2.csv"});
        pipeline.storeData();
        verify(instrumentCsvParser, times(2)).parse(any(File.class));
        verify(instrumentLoader, times(2)).loadData(anyListOf(Instrument.class));
      }
}
