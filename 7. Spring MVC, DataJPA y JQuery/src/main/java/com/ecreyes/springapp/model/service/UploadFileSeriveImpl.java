package com.ecreyes.springapp.model.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileSeriveImpl implements IUploadFileService {

    @Override
    public String storeFile(MultipartFile file) throws Exception {
        String nombreFoto = UUID.randomUUID().toString().concat(file.getOriginalFilename());
        Path rootPath = getRootPath(nombreFoto);
        Files.copy(file.getInputStream(),rootPath);
        return nombreFoto;
    }

    @Override
    public boolean deleteFile(String filename) {
        Path rootPath = getRootPath(filename);
        File archivo = rootPath.toFile();
        if(archivo.exists() && archivo.canRead()){
            return archivo.delete();
        }
        return false;
    }

    private Path getRootPath(String filename){
        Path rootPath = Paths.get("uploads").resolve(filename).toAbsolutePath();
        return rootPath;
    }
}
