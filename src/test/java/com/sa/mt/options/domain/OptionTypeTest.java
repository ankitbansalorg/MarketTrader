package com.sa.mt.options.domain;

import org.junit.Test;

import static com.sa.mt.options.domain.OptionType.PUT;
import static com.sa.mt.options.domain.OptionType.CALL;
import static com.sa.mt.options.domain.OptionType.identify;
import static org.junit.Assert.assertSame;

public class OptionTypeTest {
    
    @Test
    public void shouldIdentifyDailyAverageType() {
       assertSame(CALL, identify("CA"));
       assertSame(CALL, identify("CE"));
       assertSame(PUT, identify("PA"));
       assertSame(PUT, identify("PE"));
    }
}
