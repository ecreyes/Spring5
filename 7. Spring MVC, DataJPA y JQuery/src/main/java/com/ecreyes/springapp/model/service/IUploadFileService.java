package com.ecreyes.springapp.model.service;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
    String storeFile(MultipartFile file) throws Exception;
    boolean deleteFile(String name);
}
