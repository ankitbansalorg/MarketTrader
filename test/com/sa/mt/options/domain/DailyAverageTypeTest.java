package com.sa.mt.options.domain;

import org.junit.Test;

import static com.sa.mt.options.domain.DailyAverageType.PUT;
import static com.sa.mt.options.domain.DailyAverageType.CALL;
import static com.sa.mt.options.domain.DailyAverageType.identify;
import static org.junit.Assert.assertSame;

public class DailyAverageTypeTest {
    
    @Test
    public void shouldIdentifyDailyAverageType() {
       assertSame(CALL, identify("CA"));
       assertSame(CALL, identify("CE"));
       assertSame(PUT, identify("PA"));
       assertSame(PUT, identify("PE"));
    }
}
