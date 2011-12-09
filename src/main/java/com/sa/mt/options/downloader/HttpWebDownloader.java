package com.sa.mt.options.downloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class HttpWebDownloader {

    private  Content content;

    @Autowired
    public HttpWebDownloader(Content content) {
          this.content = content;
    }

    public void download(String downloadFrom, String downloadTo) {
        HttpURLConnection connection = connection(downloadFrom);
        String[] filePath= downloadFrom.split("/");
        String fileName = filePath[filePath.length - 1];
        System.out.println(downloadFrom);
        try {
            int response = connection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == response) {
                InputStream inputStream = connection.getInputStream();
                content.saveTo(inputStream, downloadTo, fileName);
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
