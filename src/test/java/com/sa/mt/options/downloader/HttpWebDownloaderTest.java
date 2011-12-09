package com.sa.mt.options.downloader;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;
public class HttpWebDownloaderTest {

    @Mock
    private Content content;

    private HttpWebDownloader downloader;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        downloader = new HttpWebDownloader(content);
    }

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
      public void shouldDownloadAndSaveFile() {
         String downloadTo = "test";
         downloader.download("http://localhost/", downloadTo);
         verify(content).saveTo(any(InputStream.class), eq(downloadTo), anyString());
      }
}
