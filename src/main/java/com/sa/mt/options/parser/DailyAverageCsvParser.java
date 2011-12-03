package com.sa.mt.options.parser;

import au.com.bytecode.opencsv.CSVReader;
import com.sa.mt.exception.ImproperFormatException;
import com.sa.mt.options.domain.DailyAverage;
import com.sa.mt.options.domain.DailyAverageType;
import com.sa.mt.options.domain.InstrumentType;
import com.sa.mt.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.sa.mt.options.domain.DailyAverageType.CALL;
import static com.sa.mt.options.domain.DailyAverageType.identify;
import static com.sa.mt.options.domain.InstrumentType.OPTION;
import static com.sa.mt.utils.DateUtils.getDate;
import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;

@Service
public class DailyAverageCsvParser {

    public static final String[] HEADER = {"INSTRUMENT", "SYMBOL", "EXPIRY_DT", "STRIKE_PR", "OPTION_TYP", "OPEN", "HIGH", "LOW", "CLOSE", "SETTLE_PR", "CONTRACTS", "VAL_INLAKH", "OPEN_INT", "CHG_IN_OI", "TIMESTAMP", ""};

    public List<DailyAverage> parse(File dailyAverageCsv) {
        List<String[]> rows = readFile(dailyAverageCsv);
        validate(rows);
        List<DailyAverage> dailyAverageList = new ArrayList<DailyAverage>();
        for (int i = 1; i < rows.size(); i++) {
            DailyAverage dailyAverage = transform(rows.get(i));
            if (dailyAverage != null) {
                dailyAverageList.add(dailyAverage);
            }
        }
        return dailyAverageList;
    }

    private DailyAverage transform(String[] row) {
        if (isValidOptionData(row)) {
            return new DailyAverage(row[1], OPTION, parseDouble(row[3]), identify(row[4]), parseDouble(row[5]),
                    parseDouble(row[6]), parseDouble(row[7]), parseDouble(row[8]),
                    parseDouble(row[9]), parseLong(row[10]), parseDouble(row[11]), parseLong(row[12]),
                    parseLong(row[13]),  getDate(row[14]), getDate(row[2]));
        }
        return null;
    }

    private void validate(List<String[]> rows) {
        String[] header = rows.get(0);
        if (!Arrays.equals(header, HEADER)) throw new ImproperFormatException("Not a Valid Header", header);
    }

    private static boolean isValidOptionData(String[] row) {
        return InstrumentType.identify(row[0]) == OPTION;
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
