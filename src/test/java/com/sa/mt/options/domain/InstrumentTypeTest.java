package com.sa.mt.options.domain;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class InstrumentTypeTest {

    @Test
    public void shouldIdentifyInstrumentType() {
       assertSame(InstrumentType.OPTION, InstrumentType.identify("OPTSTK"));
       assertSame(InstrumentType.OPTION, InstrumentType.identify("OPTIDX"));
       assertSame(InstrumentType.FUTURE, InstrumentType.identify("FUTIDX"));
       assertSame(InstrumentType.FUTURE, InstrumentType.identify("FUTSTK"));
       assertSame(InstrumentType.FUTURE, InstrumentType.identify("FUTINT"));
    }
}
