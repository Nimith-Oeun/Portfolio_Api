package com.personal.portfolio_api.exceptionHandle;

import com.personal.portfolio_api.repository.ProfilePhotoRepository;
import com.personal.portfolio_api.util.FileExtencion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhotoHandle {

    private final ProfilePhotoRepository profilePhotoRepository;
    private final List<String> FILE_EXTENSIONS = Arrays.asList("jpg", "png", "jpeg");

    //check if the file is empty
    public void validateUploadFile(MultipartFile files){
        if(files.isEmpty()){
            log.info("No file uploaded");
            throw new IllegalArgumentException("No file uploaded");
        }
    }

    //check file format
    public void validateFileFormat(MultipartFile files){

        var fileName = StringUtils.cleanPath(Objects.requireNonNull(files.getOriginalFilename()));
        var extension = FileExtencion.getExtension(fileName);

        if (!FILE_EXTENSIONS.contains(extension)) {
            log.warn("File Extencion is not allow to upload , please verify: {}", fileName);
            throw new IllegalArgumentException("File Extencion is not allow to upload , please verify: " + fileName);
        }
    }
}
