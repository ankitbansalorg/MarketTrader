package com.sa.mt.options.downloader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class DailyAverageCsvDownloader {

    @Value(value = "${daily.average.download.url}")
    private String downloadFrom;

    @Value(value = "${daily.average.store.path}")
    private String downloadTo;

    public void download() {
         HttpURLConnection connection = connection(downloadFrom);
    }

    private HttpURLConnection connection(String downloadUrl) {
        try {
            return (HttpURLConnection) new URL(downloadUrl).openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
