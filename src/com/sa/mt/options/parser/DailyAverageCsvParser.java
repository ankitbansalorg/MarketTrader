package com.sa.mt.options.parser;

import au.com.bytecode.opencsv.CSVReader;
import com.sa.mt.options.domain.DailyAverage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DailyAverageCsvParser {

    public List<DailyAverage> parse(File dailyAverageCsv) {
        List<String[]> rows = readFile(dailyAverageCsv);
        String[] header = rows.get(0);
        List<DailyAverage> dailyAverageList = new ArrayList<DailyAverage>();
        for (int i = 1; i < rows.size(); i++) {
            dailyAverageList.add(new DailyAverage());
        }
        return dailyAverageList;
    }

    private List<String[]> readFile(File dailyAverageCsv) {
        try {
            FileReader reader = new FileReader(dailyAverageCsv);
            CSVReader csvReader = new CSVReader(reader);
            return csvReader.readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
