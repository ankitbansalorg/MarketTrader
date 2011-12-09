package com.sa.mt.options.pipeline;

import com.sa.mt.options.downloader.HttpWebDownloader;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class InstrumentPipelineTest {
	
	@Mock
    private HttpWebDownloader httpWebDownloader;

    @Autowired
    private InstrumentPipeline instrumentPipeline;

    private String storageUrl = "Some Path";
    @Mock
    private InstrumentFileServer instrumentFileServer;

    private String downloadPath = "http://www.nseindia.com/content/historical/DERIVATIVES/year/month/fodaymonthyearbhav.csv.zip";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        instrumentPipeline = new InstrumentPipeline(httpWebDownloader, instrumentFileServer);
        instrumentPipeline.setStorageUrl(storageUrl);
        instrumentPipeline.setDownloadUrl(downloadPath);
    }

     @Test
      public void shouldDownloadCsvFileForCurrentDate() {
         instrumentPipeline.execute();
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
         String[] date = sdf.format(new Date()).split("-");
         String downloadPath = "http://www.nseindia.com/content/historical/DERIVATIVES/" + date[0] + "/" + date[1].toUpperCase()+
                 "/fo" + date[2] + date[1].toUpperCase() + date[0] + "bhav.csv.zip";
         verify(httpWebDownloader).download(eq(downloadPath), eq(storageUrl));
         verify(instrumentFileServer).storeData(eq(storageUrl));
      }
}
