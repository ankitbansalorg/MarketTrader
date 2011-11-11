package com.sa.mt.options.parser;

import au.com.bytecode.opencsv.CSVReader;
import com.sa.mt.exception.ImproperFormatException;
import com.sa.mt.options.domain.DailyAverage;
import com.sa.mt.options.domain.DailyAverageType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DailyAverageCsvParser {

    public List<DailyAverage> parse(File dailyAverageCsv) {
        List<String[]> rows = readFile(dailyAverageCsv);
        String[] header = rows.get(0);
        if(!isValidHeader(header)) throw new ImproperFormatException("Not a Valid Header",header);
        List<DailyAverage> dailyAverageList = new ArrayList<DailyAverage>();
        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);
            if(isValidOptionData(row)){
            dailyAverageList.add(new DailyAverage(row[1],Integer.valueOf(row[3]), DailyAverageType.PA));
            }
        }
        return dailyAverageList;
    }

    private boolean isValidHeader(String[] header) {
      String[] validHeader = {"INSTRUMENT","SYMBOL","EXPIRY_DT","STRIKE_PR","OPTION_TYP","OPEN","HIGH","LOW","CLOSE","SETTLE_PR","CONTRACTS","VAL_INLAKH","OPEN_INT","CHG_IN_OI","TIMESTAMP",""};
      return Arrays.equals(header,validHeader);
    }

    private static boolean isValidOptionData(String[] row) {
        return row[0].equals("OPTSTK");
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
