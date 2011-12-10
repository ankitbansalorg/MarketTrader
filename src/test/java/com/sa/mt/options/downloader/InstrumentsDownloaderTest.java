package com.sa.mt.options.downloader;

import com.sa.mt.options.domain.DownloadStatus;
import com.sa.mt.options.repository.DownloadStatusRepository;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
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
    @Mock
    private DownloadStatusRepository downloadStatusRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        instrumentsDownloader = new InstrumentsDownloader(httpWebDownloader, downloadStatusRepository);
        instrumentsDownloader.setStorageUrl(storageUrl);
        instrumentsDownloader.setDownloadUrl(downloadPath);
    }

    @Test
    public void shouldDownloadCsvFileForLastTwoDays() {
        when(downloadStatusRepository.find(DownloadType.INSTRUMENT)).thenReturn
                (new DownloadStatus(DownloadType.INSTRUMENT, dateBeforeSpecifiedDays(2)));
        instrumentsDownloader.execute();
        String prevDayUrl = urlForDaysBefore(1);
        String toDayUrl = urlForDaysBefore(0);

        ArgumentCaptor<String> downloadUrlCaptor = ArgumentCaptor.forClass(String.class);
        verify(httpWebDownloader, times(2)).download(downloadUrlCaptor.capture(), eq(storageUrl));

        List<String> capturedPeople = downloadUrlCaptor.getAllValues();
        assertEquals(prevDayUrl, capturedPeople.get(0));
        assertEquals(toDayUrl, capturedPeople.get(1));
    }

    private String urlForDaysBefore(int numberOfDays) {
        Date twoDaysBefore = dateBeforeSpecifiedDays(numberOfDays);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
        String[] date = sdf.format(twoDaysBefore).split("-");
        return "http://www.nseindia.com/content/historical/DERIVATIVES/" + date[0] + "/" + date[1].toUpperCase()+
                "/fo" + date[2] + date[1].toUpperCase() + date[0] + "bhav.csv.zip";
    }

    private Date dateBeforeSpecifiedDays(int numberOfDays) {
        DateTime dateTime = new DateTime(new Date());
        return dateTime.minusDays(numberOfDays).toDate();
    }
}
