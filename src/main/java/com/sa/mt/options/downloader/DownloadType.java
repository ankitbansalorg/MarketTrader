package com.sa.mt.options.downloader;

public enum DownloadType {
    STOCK("Stock"), INSTRUMENT("Instrument");
    private String downloadType;

    private DownloadType(String downloadType) {
         this.downloadType = downloadType;
    }
}
