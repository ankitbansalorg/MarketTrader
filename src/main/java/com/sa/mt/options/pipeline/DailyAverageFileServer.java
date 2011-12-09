package com.sa.mt.options.pipeline;

import com.sa.mt.options.domain.Instrument;
import com.sa.mt.options.loader.DailyAverageLoader;
import com.sa.mt.options.parser.DailyAverageCsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class DailyAverageFileServer {
    private DailyAverageCsvParser dailyAverageCsvParser;
    private DailyAverageLoader dailyAverageLoader;

    @Autowired
    public DailyAverageFileServer(DailyAverageCsvParser dailyAverageCsvParser, DailyAverageLoader dailyAverageLoader) {
        this.dailyAverageCsvParser = dailyAverageCsvParser;
        this.dailyAverageLoader = dailyAverageLoader;
    }

    public void storeData(String storageUrl) {
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
        List<Instrument> dailyAverages =  dailyAverageCsvParser.parse(file);
        dailyAverageLoader.loadData(dailyAverages);
    }
}
