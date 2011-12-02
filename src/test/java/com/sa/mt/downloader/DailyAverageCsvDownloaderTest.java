package com.sa.mt.downloader;

import com.sa.mt.options.downloader.DailyAverageCsvDownloader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/spring-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class DailyAverageCsvDownloaderTest {

    @Autowired
    private DailyAverageCsvDownloader downloader;

     @Test
      public void shouldDownloadCsvFile() {
         downloader.download();
      }
}
