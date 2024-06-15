package com.dinsaren.oneposappserverapi.controllers.rest;

import com.dinsaren.oneposappserverapi.models.FileImageDetail;
import com.dinsaren.oneposappserverapi.payload.response.MessageRes;
import com.dinsaren.oneposappserverapi.services.UploadFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;

@Slf4j
@RestController
@RequestMapping("/api/public")
public class FileViewController {
    private final UploadFileService uploadFileService;
    private MessageRes resMessage;

    public FileViewController(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    @GetMapping(value = "/view/image",
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public ResponseEntity<byte[]> getImageById(
            @RequestParam("filename") String filename) {
        log.debug("Intercept view image file by filename : {}", filename);

        try {
            FileImageDetail detail = uploadFileService.findImageByFileName(filename);
            if (detail == null) {
                return null;
            }
            String path = detail.getFilePath();
            File file = new File(path);
            return ResponseEntity.ok(Files.readAllBytes(file.toPath()));
        } catch (Throwable e) {
            log.error("Errors while view image", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            log.info("Final Response Get Image {}", filename);
        }
    }

}
