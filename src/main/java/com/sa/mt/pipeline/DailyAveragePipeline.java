package com.sa.mt.pipeline;

import com.sa.mt.options.domain.DailyAverage;
import com.sa.mt.options.downloader.DailyAverageCsvDownloader;
import com.sa.mt.options.loader.DailyAverageLoader;
import com.sa.mt.options.parser.DailyAverageCsvParser;
import com.sa.mt.options.repository.DailyAverageRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.Field;
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
    private DailyAverageCsvParser dailyAverageCsvParser;
    private DailyAverageLoader dailyAverageLoader;

    @Autowired
    public DailyAveragePipeline(DailyAverageCsvDownloader dailyAverageCsvDownloader,
                                DailyAverageCsvParser dailyAverageCsvParser, DailyAverageLoader dailyAverageLoader) {
        this.dailyAverageCsvDownloader = dailyAverageCsvDownloader;
        this.dailyAverageCsvParser = dailyAverageCsvParser;
        this.dailyAverageLoader = dailyAverageLoader;
    }


    public void execute() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String[] dateStrings = sdf.format(new Date()).split(HYPHEN);
        String currentDateUrl = downloadUrl.replaceAll(YEAR, dateStrings[0]).replaceAll(MONTH, dateStrings[1].toUpperCase()).replaceAll(DAY, dateStrings[2]);
        dailyAverageCsvDownloader.download(currentDateUrl, storageUrl);


        String[] fileNames = new File(storageUrl).list();
        for(String fileName : fileNames) {
            if(fileName.endsWith(".csv")) {
                File parsableFile = new File(storageUrl + fileName);
                storeFileData(parsableFile);
                parsableFile.delete();
            }
        }
    }

    private void storeFileData(File file) {
        List<DailyAverage> dailyAverages =  dailyAverageCsvParser.parse(file);
        dailyAverageLoader.loadData(dailyAverages);
    }

    private Iterator<File> csvFilesAt(String storageUrl) {
        String[] ext = {"zip"};
        return FileUtils.iterateFiles(new File(storageUrl), ext, false);
    }

    //for testing purpose
    public void setDailyAverageCsvDownloader(DailyAverageCsvDownloader dailyAverageCsvDownloader) {
        this.dailyAverageCsvDownloader = dailyAverageCsvDownloader;
    }
}
