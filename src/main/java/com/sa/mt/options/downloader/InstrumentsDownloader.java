package com.sa.mt.options.downloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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

    private static final String DATE_FORMAT = "yyyy-MMM-dd";

    @Autowired
    public InstrumentsDownloader(HttpWebDownloader httpWebDownloader) {
        this.httpWebDownloader = httpWebDownloader;
    }


    public void execute() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String[] dateStrings = sdf.format(new Date()).split(HYPHEN);
        String currentDateUrl = downloadUrl.replaceAll(YEAR, dateStrings[0]).replaceAll(MONTH, dateStrings[1].toUpperCase()).replaceAll(DAY, dateStrings[2]);
        httpWebDownloader.download(currentDateUrl, storageUrl);
    }

    //for testing purpose
    public void setHttpWebDownloader(HttpWebDownloader httpWebDownloader) {
        this.httpWebDownloader = httpWebDownloader;
    }

    public void setStorageUrl(String storageUrl) {
        this.storageUrl = storageUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl =  downloadUrl;
    }
}
