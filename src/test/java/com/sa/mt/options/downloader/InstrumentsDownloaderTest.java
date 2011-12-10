package com.sa.mt.options.downloader;

import com.sa.mt.options.domain.DownloadStatus;
import com.sa.mt.options.repository.DownloadStatusRepository;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InstrumentsDownloaderTest {

    @Mock
    private HttpWebDownloader httpWebDownloader;

    @Autowired
    private InstrumentsDownloader instrumentsDownloader;

    private String storageUrl = "Some Path";

    private String downloadPath = "http://www.nseindia.com/content/historical/DERIVATIVES/year/month/fodaymonthyearbhav.csv.zip";
    private DownloadStatusRepository downloadStatusRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        instrumentsDownloader = new InstrumentsDownloader(httpWebDownloader);
        instrumentsDownloader.setStorageUrl(storageUrl);
        instrumentsDownloader.setDownloadUrl(downloadPath);
    }

    @Ignore
    @Test
    public void shouldDownloadCsvFileForLastTwoDays() {
        when(downloadStatusRepository.find(DownloadType.INSTRUMENT)).thenReturn(new DownloadStatus(DownloadType.INSTRUMENT, new Date()));
        instrumentsDownloader.execute();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
        String[] date = sdf.format(new Date()).split("-");
        String downloadPath = "http://www.nseindia.com/content/historical/DERIVATIVES/" + date[0] + "/" + date[1].toUpperCase()+
                "/fo" + date[2] + date[1].toUpperCase() + date[0] + "bhav.csv.zip";
        verify(httpWebDownloader, times(3)).download(eq(downloadPath), eq(storageUrl));
    }
}
