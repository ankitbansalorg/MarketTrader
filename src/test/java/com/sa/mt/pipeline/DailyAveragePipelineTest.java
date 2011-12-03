package com.sa.mt.pipeline;

import com.sa.mt.options.downloader.DailyAverageCsvDownloader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/spring-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class DailyAveragePipelineTest {

    @Mock
    private DailyAverageCsvDownloader dailyAverageCsvDownloader;

    @Autowired
    private DailyAveragePipeline dailyAveragePipeline;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dailyAveragePipeline.setDailyAverageCsvDownloader(dailyAverageCsvDownloader);
    }

     @Test
      public void shouldDownloadCsvFileForCurrentDate() {
         dailyAveragePipeline.execute();
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
         String[] date = sdf.format(new Date()).split("-");
         String downloadPath = "http://www.nseindia.com/content/historical/DERIVATIVES/" + date[0] + "/" + date[1].toUpperCase()+
                 "/fo" + date[2] + date[1].toUpperCase() + date[0] + "bhav.csv.zip";
         verify(dailyAverageCsvDownloader).download(eq(downloadPath), eq("/usr/htdocs/options/"));
      }

     @Test
      public void shouldParseAllTheDailyAverageFilesPresentInTheStorageFolder() {

      }
}
