package com.sa.mt.options.repository;

import com.sa.mt.options.domain.DownloadStatus;
import com.sa.mt.options.downloader.DownloadType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Update.update;

@Repository
public class DownloadStatusRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public DownloadStatus find(DownloadType downloadType) {
        Query query = new Query(Criteria.where("downloadType").is(downloadType));
        return mongoTemplate.findOne(query, DownloadStatus.class);
    }

    public void save(List<DownloadStatus> downloadStatuses) {
        mongoTemplate.insertAll(downloadStatuses);
    }

    public void updateDownloadedDate(DownloadStatus downloadStatus) {
        Query query = new Query(Criteria.where("downloadType").is(downloadStatus.getDownloadType()));
        mongoTemplate.updateFirst(query, update("lastDownloadedDate", downloadStatus.getLastDownloadedDate()),
                DownloadStatus.class);
    }
}
