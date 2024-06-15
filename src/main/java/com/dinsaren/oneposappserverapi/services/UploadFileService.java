package com.dinsaren.oneposappserverapi.services;

import com.dinsaren.oneposappserverapi.models.FileImageDetail;
import com.dinsaren.oneposappserverapi.models.res.UploadImageRes;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {
    UploadImageRes uploadFile(MultipartFile files);

    FileImageDetail findImageByFileName(String fileName);
}
