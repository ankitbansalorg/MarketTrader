package com.sa.mt.options.downloader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class DailyAverageCsvDownloader {

    public void download(String downloadFrom, String downloadTo) {
        HttpURLConnection connection = connection(downloadFrom);
        try {
            int response = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == response) {
                InputStream inputStream = connection.getInputStream();
               // return null;
            } else {
                throw new IllegalStateException("Could not establish a valid connection. Response Code: " + response);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    private HttpURLConnection connection(String downloadFrom) {
        try {
            return (HttpURLConnection) new URL(downloadFrom).openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
