package com.sa.mt.options.parser;

import au.com.bytecode.opencsv.CSVReader;
import com.sa.mt.options.domain.DailyAverage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DailyAverageCsvParser {

    public List<String[]> parse(File dailyAverageCsv) {
        try {
            FileReader reader = new FileReader(dailyAverageCsv);
            CSVReader csvReader = new CSVReader(reader);
            return csvReader.readAll();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
