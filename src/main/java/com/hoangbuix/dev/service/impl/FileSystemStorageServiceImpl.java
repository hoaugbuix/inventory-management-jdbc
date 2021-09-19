package com.hoangbuix.dev.service.impl;

import com.hoangbuix.dev.config.FileUploadConfig;
import com.hoangbuix.dev.exception.FileNotFoundException;
import com.hoangbuix.dev.exception.FileStorageException;
import com.hoangbuix.dev.service.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileSystemStorageServiceImpl implements FileSystemStorageService {
    private final Path dirLocation;

    @Autowired
    public FileSystemStorageServiceImpl(FileUploadConfig fileUploadConfig) {
        this.dirLocation = Paths.get(fileUploadConfig.getLocation())
                .toAbsolutePath()
                .normalize();
    }

    @Override
    @PostConstruct
    public void init() {

        try {
            Files.createDirectories(this.dirLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create upload dir!");
        }
    }

    @Override
    public String saveFile(MultipartFile file) {
        try {

            String fileName = file.getOriginalFilename();
            Path dfile = this.dirLocation.resolve(fileName);
            Files.copy(file.getInputStream(), dfile, StandardCopyOption.REPLACE_EXISTING);

            return fileName;

        } catch (Exception e) {
            throw new FileStorageException("Could not upload file");
        }
    }

    @Override
    public Resource loadFile(String fileName) {
        try {

            Path file = this.dirLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException("Could not find file");
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not download file");
        }
    }
}
