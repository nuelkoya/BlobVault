package com.koyacode.blobvault;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    private final BlobStorageService bss;

    public FileService(BlobStorageService bss) {
        this.bss = bss;
    }

    public void uploadFile(UUID userId, String filePath){
        BlobFile file = new BlobFile(userId, filePath);
        bss.uploadFile(file.getUserId(), filePath);
    }

    public List<String> getFilesForUser(UUID userId) {
        return bss.getFilesForUser(userId);
    }

}
