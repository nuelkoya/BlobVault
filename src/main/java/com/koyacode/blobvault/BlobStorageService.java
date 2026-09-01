package com.koyacode.blobvault;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.azure.storage.blob.models.BlobItem;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.models.BlobProperties;

import java.util.*;

import java.io.IOException;
@Service
public class BlobStorageService {

    private final BlobServiceClient blobServiceClient;
    private BlobContainerClient containerClient;
    private final String containerName;
    private final String endpoint;


    public BlobStorageService(
            @Value("${azure.storage.blob.endpoint}") String endpoint,
            @Value("${azure.storage.container-name}") String containerName) {

        this.containerName = containerName;
        this.endpoint = endpoint;

        this.blobServiceClient = new BlobServiceClientBuilder()
                .endpoint(endpoint)
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();

        this.containerClient = blobServiceClient.getBlobContainerClient(containerName);
    }

    public void testConnection() {
        System.out.println("Container exists: " + containerClient.exists());
    }

    public void listFiles() {
        for (BlobItem blobItem : containerClient.listBlobs()) {
            System.out.println(blobItem.getName());
        }
    }

    public void downloadFile(String fileName) {
        BlobClient blobClient =
                containerClient.getBlobClient(fileName);

        blobClient.downloadToFile(
                "/Users/nuelkoya/Desktop/java_files/" + fileName
        );

        System.out.println("File downloaded successfully.");
    }

    public void uploadFile(UUID userId, String fileName) {
        BlobClient blobClient = containerClient.getBlobClient(fileName);

        blobClient.uploadFromFile(
                "/Users/nuelkoya/Desktop/" + fileName
        );
        Map<String, String> metadata = new HashMap<>();
        metadata.put("userId", userId.toString());
        blobClient.setMetadata(metadata);

        System.out.println("File uploaded successfully.");
    }


    public List<String> getFilesForUser(UUID userId) {

        List<String> files = new ArrayList<>();

        for (BlobItem item : containerClient.listBlobs()) {

            BlobClient blobClient =
                    containerClient.getBlobClient(item.getName());

            BlobProperties properties = blobClient.getProperties();

            String ownerId = properties.getMetadata().get("userId");

            if (userId.toString().equals(ownerId)) {
                files.add(item.getName());
            }
        }

        return files;
    }



}
