package com.personal.portfolio_api.service.impl;

import com.personal.portfolio_api.enumerat.FileType;
import com.personal.portfolio_api.exception.ResoureNoteFoundException;
import com.personal.portfolio_api.exceptionHandle.PhotoHandle;
import com.personal.portfolio_api.model.CoverPhoto;
import com.personal.portfolio_api.repository.CoverPhotoRepository;
import com.personal.portfolio_api.service.CoverPhotoService;
import com.personal.portfolio_api.util.FileExtencion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoverPhotoServiceImpl implements CoverPhotoService {

    private final CoverPhotoRepository coverPhotoRepository;
    private final PhotoHandle PhotoHandle;

    private static final String FILE_UPLOAD_PATH = System.getProperty("user.dir") + "/upload/coverPhoto/";

    @Override
    public CoverPhoto uploadCover(MultipartFile file) {

        PhotoHandle.validateUploadFile(file);
        PhotoHandle.validateFileFormat(file);

        try {
            CoverPhoto coverPhoto = new CoverPhoto();

            var namePhoto = FilenameUtils.removeExtension(file.getOriginalFilename());
            var extensionName = FileExtencion.getExtension(file.getOriginalFilename());
            var fileName = namePhoto + "." + extensionName;

            File filePathTemp = new File(FILE_UPLOAD_PATH + fileName);
            file.transferTo(filePathTemp);

            coverPhoto.setFileFomate(extensionName);
            coverPhoto.setFileSize(file.getSize());
            coverPhoto.setFileType(String.valueOf(FileType.PHOTO));
            coverPhoto.setFileName(namePhoto);
            coverPhoto.setPartUpload(FILE_UPLOAD_PATH + fileName);
            
            return coverPhotoRepository.save(coverPhoto);
            
        } catch (IOException e) {
            
            log.error("Error while uploading file: {}", e.getMessage());
            throw new ResoureNoteFoundException("Error while uploading file: " + e.getMessage());
        }

    }

}
