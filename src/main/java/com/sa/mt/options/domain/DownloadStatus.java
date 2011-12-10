package com.sa.mt.options.domain;

import com.sa.mt.options.downloader.DownloadType;

import java.util.Date;

public class DownloadStatus {

    private DownloadType downloadType;
    private Date lastDownloadedDate;

    public DownloadStatus() {

    }

    public DownloadStatus(DownloadType downloadType, Date lastDownloadedDate) {
        this.downloadType = downloadType;
        this.lastDownloadedDate = lastDownloadedDate;
    }

    public DownloadType getDownloadType() {
        return downloadType;
    }

    public Date getLastDownloadedDate() {
        return lastDownloadedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DownloadStatus that = (DownloadStatus) o;

        if (downloadType != that.downloadType) return false;
        if (lastDownloadedDate != null ? !lastDownloadedDate.equals(that.lastDownloadedDate) : that.lastDownloadedDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = downloadType != null ? downloadType.hashCode() : 0;
        result = 31 * result + (lastDownloadedDate != null ? lastDownloadedDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DownloadStatus{" +
                "downloadType=" + downloadType +
                ", lastDownloadedDate=" + lastDownloadedDate +
                '}';
    }
}
