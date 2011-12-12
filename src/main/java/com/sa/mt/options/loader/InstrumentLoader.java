package com.sa.mt.options.loader;

import com.sa.mt.options.domain.ExpiryDate;
import com.sa.mt.options.domain.Instrument;
import com.sa.mt.options.repository.ExpiryDatesRepository;
import com.sa.mt.options.repository.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class InstrumentLoader {

    private InstrumentRepository instrumentRepository;
    private ExpiryDatesRepository expiryDatesRepository;
    
    @Autowired
    public InstrumentLoader(InstrumentRepository instrumentRepository, ExpiryDatesRepository expiryDatesRepository) {
        this.instrumentRepository = instrumentRepository;
        this.expiryDatesRepository = expiryDatesRepository;
    }

    public void loadData(List<Instrument> instruments) {
        if(instruments.size() > 0) {
            Date date = instruments.get(0).getDate();
            if(!instrumentRepository.dataExistsForDate(date)) {
                instrumentRepository.save(instruments);
                expiryDatesRepository.save(extractExpiryDates(instruments) );
            }
        }
    }

	private List<ExpiryDate> extractExpiryDates(List<Instrument> instruments) {
		Set<ExpiryDate> expiryDates = new HashSet<ExpiryDate>();
		for (Instrument instrument : instruments) {
			expiryDates.add(new ExpiryDate(instrument.getExpiryDate()));
		}	
		return new ArrayList<ExpiryDate>(expiryDates);
	}
}
