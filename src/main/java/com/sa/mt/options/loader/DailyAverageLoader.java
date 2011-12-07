package com.sa.mt.options.loader;

import com.sa.mt.options.domain.Instrument;
import com.sa.mt.options.repository.DailyAverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DailyAverageLoader {

    private DailyAverageRepository dailyAverageRepository;

    @Autowired
    public DailyAverageLoader(DailyAverageRepository dailyAverageRepository) {
        this.dailyAverageRepository = dailyAverageRepository;
    }

    public void loadData(List<Instrument> dailyAverages) {
        if(dailyAverages.size() > 0) {
            Date date = dailyAverages.get(0).getDate();
            if(!dailyAverageRepository.dataExistsForDate(date)) {
                dailyAverageRepository.save(dailyAverages);
            }
        }
    }
}
