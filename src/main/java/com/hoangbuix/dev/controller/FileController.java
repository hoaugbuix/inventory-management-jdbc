package com.hoangbuix.dev.controller;

import com.hoangbuix.dev.model.file.FileResponse;
import com.hoangbuix.dev.service.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1")
public class FileController {
    @Autowired
    FileSystemStorageService fileSystemStorageService;

    @PostMapping("/upload-file")
    public ResponseEntity<FileResponse> uploadSingleFile(@RequestParam("file") MultipartFile file) {

        String upFile = fileSystemStorageService.saveFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/download/")
                .path(upFile)
                .toUriString();

        return ResponseEntity.status(HttpStatus.OK).body(new FileResponse(upFile, fileDownloadUri, "File uploaded with success!"));
    }

    @PostMapping("/upload-files")
    public ResponseEntity<List<FileResponse>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {

        List<FileResponse> responses = Arrays.asList(files)
                .stream()
                .map(
                        file -> {
                            String upFile = fileSystemStorageService.saveFile(file);
                            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                    .path("/api/download/")
                                    .path(upFile)
                                    .toUriString();
                            return new FileResponse(upFile, fileDownloadUri, "File uploaded with success!");
                        }
                )
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }


    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {

        Resource resource = fileSystemStorageService.loadFile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
