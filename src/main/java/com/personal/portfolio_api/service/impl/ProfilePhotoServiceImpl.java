package com.personal.portfolio_api.service.impl;

import com.personal.portfolio_api.dto.ProfilePhotoDTO;
import com.personal.portfolio_api.enumerat.FileType;
import com.personal.portfolio_api.exception.InternalServerError;
import com.personal.portfolio_api.exception.ResoureNoteFoundException;
import com.personal.portfolio_api.exceptionHandle.PhotoHandle;
import com.personal.portfolio_api.mapper.PhotoMapper;
import com.personal.portfolio_api.model.ProfilePhoto;
import com.personal.portfolio_api.repository.ProfilePhotoRepository;
import com.personal.portfolio_api.service.ProfilePhotoService;
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
public class ProfilePhotoServiceImpl implements ProfilePhotoService {

    private final ProfilePhotoRepository profilePhotoRepository;
    private final PhotoHandle PhotoHandle;
    private final PhotoMapper photoMapper;

    // Path to the directory where files will be uploaded during development
//    private static final String FILE_UPLOAD_PATH = System.getProperty("user.dir") + "/upload/profilePhoto/";

    // Path to the directory where files will be uploaded server production
    @Value("${spring.uploadsPart}")
    private String FILE_UPLOAD_PATH;

    @Override
    public ProfilePhoto uploadProfile(MultipartFile file , ProfilePhotoDTO profilePhotoDTO) {

        PhotoHandle.validateUploadFile(file);
        PhotoHandle.validateFileFormat(file);

        try {
            ProfilePhoto profilePhoto = new ProfilePhoto();
            ProfilePhoto userId = photoMapper.mapToProfilePhoto(profilePhotoDTO);

            var name = FilenameUtils.removeExtension(file.getOriginalFilename());
            var extensionName = FileExtencion.getExtension(file.getOriginalFilename());
            var fileName = name + "." + extensionName;

            File filePathTemp = new File(FILE_UPLOAD_PATH + fileName);
            file.transferTo(filePathTemp);

            profilePhoto.setFileFomate(extensionName);
            profilePhoto.setFileSize(file.getSize());
            profilePhoto.setFileType(String.valueOf(FileType.PHOTO));
            profilePhoto.setFileName(name);
            profilePhoto.setPartUpload(FILE_UPLOAD_PATH + fileName);
            profilePhoto.setUserProfileID(userId.getUserProfileID());

            return profilePhotoRepository.save(profilePhoto);

        } catch (IOException e) {

            log.error("Error while uploading file: {}", e.getMessage());
            throw new InternalServerError("Error while uploading file: " + e.getMessage());
        }

    }

    @Override
    public ProfilePhoto getProfilePhotoById(Long id) {
        return profilePhotoRepository.findById(id)
                .orElseThrow(() -> new ResoureNoteFoundException("Profile photo not found with id: " + id));
    }

}
