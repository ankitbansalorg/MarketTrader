package com.sa.mt.downloader;

import com.sa.mt.options.downloader.DailyAverageCsvDownloader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.SendingContext.RunTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.IllegalFormatException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/spring-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class DailyAverageCsvDownloaderTest {

    @Autowired
    private DailyAverageCsvDownloader downloader;

    @Test(expected = RuntimeException.class)
      public void shouldThrowExceptionWhenHostNotFound() {
        downloader.download("http://test", null);
      }

     @Test(expected = RuntimeException.class)
      public void shouldThrowExceptionForInvalidProtocol() {
         downloader.download("test", null);
         downloader.download("http1://test", null);
      }

    @Test(expected = IllegalStateException.class)
      public void shouldThrowExceptionForInvalidPath() {
         downloader.download("http://www.google.com/{sfdsd}", null);
      }

     @Test
      public void shouldNotThrowExceptionForValidResponse() {
         downloader.download("http://localhost/", null);
      }
}
