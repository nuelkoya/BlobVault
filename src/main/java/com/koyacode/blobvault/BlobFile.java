package com.koyacode.blobvault;

import java.util.UUID;

public class BlobFile {

    private UUID fileId;
    private UUID userId;
    private String filePath;


    public BlobFile(UUID userId, String filePath){
        this.fileId = UUID.randomUUID();
        this.userId = userId;
        this.filePath = filePath;
    }

    public UUID getFileId() {
        return fileId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getFilePath() {
        return filePath;
    }




}
