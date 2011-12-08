package com.sa.mt.options.pipeline;

import com.sa.mt.options.domain.Instrument;
import com.sa.mt.options.loader.InstrumentLoader;
import com.sa.mt.options.parser.InstrumentCsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class InstrumentFileServer {
    private InstrumentCsvParser instrumentCsvParser;
    private InstrumentLoader instrumentLoader;

    @Autowired
    public InstrumentFileServer(InstrumentCsvParser instrumentCsvParser, InstrumentLoader instrumentLoader) {
        this.instrumentCsvParser = instrumentCsvParser;
        this.instrumentLoader = instrumentLoader;
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
        List<Instrument> instruments =  instrumentCsvParser.parse(file);
        instrumentLoader.loadData(instruments);
    }
}
