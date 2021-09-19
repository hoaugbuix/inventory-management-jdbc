package com.hoangbuix.dev.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileSystemStorageService {
    void init();

    String saveFile(MultipartFile file);

    Resource loadFile(String fileName);
}
