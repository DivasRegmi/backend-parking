package com.project.carparking.service;

import com.project.carparking.entity.FileMetadata;
import com.project.carparking.repository.FileMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class FileMetadataService {

    private final FileMetadataRepository fileMetadataRepository;

    @Autowired
    public FileMetadataService(FileMetadataRepository fileMetadataRepository) {
        this.fileMetadataRepository = fileMetadataRepository;
    }

    public ResponseEntity<Resource> getImageResponse(Long id) {
        Optional<FileMetadata> fileMetadataOpt = fileMetadataRepository.findById(id);
        if (fileMetadataOpt.isPresent()) {
            FileMetadata fileMetadata = fileMetadataOpt.get();
            ByteArrayResource resource = new ByteArrayResource(fileMetadata.getData());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(fileMetadata.getContentType())) // Dynamically set based on the file's content type
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileMetadata.getFileName() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<String> getAllImageUrls() {
        List<FileMetadata> files = fileMetadataRepository.findAll();
        return files.stream()
                .map(file -> ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/images/")
                        .path(file.getId().toString())
                        .toUriString())
                .collect(Collectors.toList());
    }



    public ResponseEntity<String> uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file to upload.", HttpStatus.BAD_REQUEST);
        }

        try {
            // Get file data
            byte[] bytes = file.getBytes();
            String contentType = file.getContentType();
            String fileName = file.getOriginalFilename();

            // Create a new FileMetadata object with the file data
            FileMetadata fileMetadata = new FileMetadata(fileName, contentType, bytes);

            // Save file metadata and data to the database
            fileMetadataRepository.save(fileMetadata);

            return ResponseEntity.ok("Successfully uploaded '" + fileName + "'");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}