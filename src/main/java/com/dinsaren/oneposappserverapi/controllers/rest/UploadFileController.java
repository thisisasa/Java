package com.dinsaren.oneposappserverapi.controllers.rest;

import com.dinsaren.oneposappserverapi.models.res.UploadImageRes;
import com.dinsaren.oneposappserverapi.payload.response.MessageRes;
import com.dinsaren.oneposappserverapi.services.UploadFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/app/public")
public class UploadFileController {
    private final UploadFileService uploadFileService;
    private MessageRes resMessage;

    public UploadFileController(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    @PostMapping(value = "/v1/image/upload", consumes = {"multipart/form-data"})
    public ResponseEntity<Object> uploadFile(
            @RequestParam("File") MultipartFile file) {
        log.debug("Intercept upload file req {}", file.toString());
        try {
            UploadImageRes res = uploadFileService.uploadFile(file);
            resMessage = new MessageRes();
            resMessage.setMessageCreateSuccess(res);
            return ResponseEntity.ok(resMessage);
        } catch (Throwable e) {
            log.error("Error while get all category {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            log.info("Final Response Get Image {}", file.getOriginalFilename());
        }
    }

}
