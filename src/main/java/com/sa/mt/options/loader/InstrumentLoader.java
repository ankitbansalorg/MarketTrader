package com.sa.mt.options.loader;

import com.sa.mt.options.domain.Instrument;
import com.sa.mt.options.repository.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InstrumentLoader {

    private InstrumentRepository instrumentRepository;

    @Autowired
    public InstrumentLoader(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    public void loadData(List<Instrument> dailyAverages) {
        if(dailyAverages.size() > 0) {
            Date date = dailyAverages.get(0).getDate();
            if(!instrumentRepository.dataExistsForDate(date)) {
                instrumentRepository.save(dailyAverages);
            }
        }
    }
}
