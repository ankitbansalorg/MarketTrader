package com.sa.mt.options.pipeline;

import com.sa.mt.options.domain.Instrument;
import com.sa.mt.options.downloader.DailyAverageCsvDownloader;
import com.sa.mt.options.loader.DailyAverageLoader;
import com.sa.mt.options.parser.DailyAverageCsvParser;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DailyAveragePipeline {
    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String DAY = "day";
    public static final String HYPHEN = "-";
    @Value(value = "${daily.average.download.url}")
    private String downloadUrl;

    @Value(value = "${daily.average.store.url}")
    private String storageUrl;

    private DailyAverageCsvDownloader dailyAverageCsvDownloader;

    private static final String DATE_FORMAT = "yyyy-MMM-dd";

    private DailyAverageFileServer dailyAverageFileServer;

    @Autowired
    public DailyAveragePipeline(DailyAverageCsvDownloader dailyAverageCsvDownloader,
                                DailyAverageFileServer dailyAverageFileServer) {
        this.dailyAverageCsvDownloader = dailyAverageCsvDownloader;
        this.dailyAverageFileServer = dailyAverageFileServer;
    }


    public void execute() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String[] dateStrings = sdf.format(new Date()).split(HYPHEN);
        String currentDateUrl = downloadUrl.replaceAll(YEAR, dateStrings[0]).replaceAll(MONTH, dateStrings[1].toUpperCase()).replaceAll(DAY, dateStrings[2]);
        dailyAverageCsvDownloader.download(currentDateUrl, storageUrl);

        dailyAverageFileServer.storeData(storageUrl);
    }

    //for testing purpose
    public void setDailyAverageCsvDownloader(DailyAverageCsvDownloader dailyAverageCsvDownloader) {
        this.dailyAverageCsvDownloader = dailyAverageCsvDownloader;
    }

    public void setStorageUrl(String storageUrl) {
        this.storageUrl = storageUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl =  downloadUrl;
    }
}
