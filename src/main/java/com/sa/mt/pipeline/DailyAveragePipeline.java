package com.sa.mt.pipeline;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DailyAveragePipeline {
    @Value(value = "${daily.average.download.url}")
    private String downloadUrl;

    @Value(value = "${daily.average.store.url}")
    private String storageUrl;
}
