package com.sa.mt.options.repository;

import com.sa.mt.options.domain.DownloadStatus;
import com.sa.mt.options.downloader.DownloadType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.data.document.mongodb.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DownloadStatusRepository {
    public static final String DOWNLOAD_STATUSES = "download_statuses";

    @Autowired
    private MongoTemplate mongoTemplate;

    public DownloadStatus find(DownloadType downloadType) {
        Query query = new Query(Criteria.where("downloadType").is(downloadType));
        return mongoTemplate.findOne(DOWNLOAD_STATUSES, query, DownloadStatus.class);
    }

    public void save(List<DownloadStatus> downloadStatuses) {
        mongoTemplate.insertList(DOWNLOAD_STATUSES, downloadStatuses);
    }

    public void updateDownloadedDate(DownloadStatus downloadStatus) {
        Query query = new Query(Criteria.where("downloadType").is(downloadStatus.getDownloadType()));
        mongoTemplate.updateFirst(DOWNLOAD_STATUSES, query, Update.update("lastDownloadedDate", downloadStatus.getLastDownloadedDate()));
    }
}
