package com.sa.mt.options.downloader;

import com.sa.mt.options.domain.DownloadStatus;
import com.sa.mt.options.repository.DownloadStatusRepository;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.sa.mt.options.downloader.DownloadType.INSTRUMENT;

@Service
public class InstrumentsDownloader {
    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String DAY = "day";
    public static final String HYPHEN = "-";
    @Value(value = "${instrument.download.url}")
    private String downloadUrl;

    @Value(value = "${instrument.store.url}")
    private String storageUrl;

    private HttpWebDownloader httpWebDownloader;
    private DownloadStatusRepository downloadStatusRepository;

    private static final String DATE_FORMAT = "yyyy-MMM-dd";

    @Autowired
    public InstrumentsDownloader(HttpWebDownloader httpWebDownloader, DownloadStatusRepository downloadStatusRepository) {
        this.httpWebDownloader = httpWebDownloader;
        this.downloadStatusRepository = downloadStatusRepository;
    }


    public void execute() {
        DownloadStatus downloadStatus = downloadStatusRepository.find(INSTRUMENT);
        int daysDiff = Days.daysBetween(new DateTime(downloadStatus.getLastDownloadedDate()), new DateTime(new Date())).getDays();
        for(int i = daysDiff; i > 0; i--) {
            DateTime dateTime = new DateTime(new Date());
            Date prevDate = dateTime.minusDays(i-1).toDate();
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            String[] dateStrings = sdf.format(prevDate).split(HYPHEN);
            String currentDateUrl = downloadUrl.replaceAll(YEAR, dateStrings[0]).
                    replaceAll(MONTH, dateStrings[1].toUpperCase()).
                    replaceAll(DAY, dateStrings[2]);
            boolean status = httpWebDownloader.download(currentDateUrl, storageUrl);

            if(status) {
                downloadStatusRepository.updateDownloadedDate(new DownloadStatus(INSTRUMENT, prevDate));
            }
        }

    }

    public void setStorageUrl(String storageUrl) {
        this.storageUrl = storageUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl =  downloadUrl;
    }
}
