package com.personal.portfolio_api.service.impl;

import com.personal.portfolio_api.dto.CoverPhotoDTO;
import com.personal.portfolio_api.enumerat.FileType;
import com.personal.portfolio_api.exception.InternalServerError;
import com.personal.portfolio_api.exception.ResoureNoteFoundException;
import com.personal.portfolio_api.exceptionHandle.PhotoHandle;
import com.personal.portfolio_api.mapper.PhotoMapper;
import com.personal.portfolio_api.model.CoverPhoto;
import com.personal.portfolio_api.repository.CoverPhotoRepository;
import com.personal.portfolio_api.service.CoverPhotoService;
import com.personal.portfolio_api.util.FileExtencion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
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
    private final PhotoMapper photoMapper;

//    // Path to the directory where files will be uploaded in development
//    private static final String FILE_UPLOAD_PATH = System.getProperty("user.dir") + "/upload/coverPhoto/";

    // Path to the directory where files will be uploaded server production
    @Value("${spring.uploadsPart}")
    private String FILE_UPLOAD_PATH;

    @Override
    public CoverPhoto uploadCover(MultipartFile file , CoverPhotoDTO coverPhotoDTO) {

        PhotoHandle.validateUploadFile(file);
        PhotoHandle.validateFileFormat(file);

        try {
            CoverPhoto coverPhoto = new CoverPhoto();
            CoverPhoto userId = photoMapper.mapToCoverPhoto(coverPhotoDTO);

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
            coverPhoto.setUserProfileID(userId.getUserProfileID());
            
            return coverPhotoRepository.save(coverPhoto);
            
        } catch (IOException e) {
            
            log.error("Error while uploading file: {}", e.getMessage());
            throw new InternalServerError("Error while uploading file: " + e.getMessage());
        }

    }

    @Override
    public CoverPhoto getCoverPhotoById(Long id) {
        return coverPhotoRepository.findById(id)
                .orElseThrow(() -> new ResoureNoteFoundException("Cover photo not found with id: " + id));
    }

}
