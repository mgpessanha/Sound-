package com.example.soundplus.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

@Service
public class AzureStorageAccountService {
    
    public String uploadFileToAzure(MultipartFile file) throws IOException {
        String connectionString = "DefaultEndpointsProtocol=https;AccountName=soundstorage;AccountKey=f6dH0b3/WfM/6qsaRI+AGHUO+SuJFgpQ5uNQd+BKo2wSz+tFjkgjIq7asLlZ7Jaxx7YcQ1dQIL0r+AStLcNKbQ==;EndpointSuffix=core.windows.net";

        BlobContainerClient client = new BlobContainerClientBuilder()
            .connectionString(connectionString)
            .containerName("images")
            .buildClient();

        BlobClient blob = client.getBlobClient(file.getOriginalFilename());

        blob.upload(file.getInputStream(), file.getSize(), true);

        return "https://soundstorage.blob.core.windows.net/images/" + file.getOriginalFilename();

    }
    
}

