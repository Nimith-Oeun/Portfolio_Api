package com.personal.portfolio_api.service.impl;

import com.personal.portfolio_api.enumerat.FileType;
import com.personal.portfolio_api.exception.InternalServerError;
import com.personal.portfolio_api.exception.ResoureNoteFoundException;
import com.personal.portfolio_api.exceptionHandle.PhotoHandle;
import com.personal.portfolio_api.model.ProjectPhoto;
import com.personal.portfolio_api.repository.ProjectPhotoRepository;
import com.personal.portfolio_api.service.ProjectPhotoService;
import com.personal.portfolio_api.util.FileExtencion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectPhotoServiceImpl implements ProjectPhotoService {

    private final ProjectPhotoRepository projectPhotoRepository;
    private final PhotoHandle photoHandle;

    // Path to the directory where files will be uploaded in development
//  private static final String FILE_UPLOAD_PATH = System.getProperty("user.dir") + "/upload/projectPhoto/";

    // Path to the directory where files will be uploaded server production
    @Value("${spring.uploadsPart}")
    private String FILE_UPLOAD_PATH;


    @Override
    public ProjectPhoto uploadProjectPhoto(MultipartFile file) {

        photoHandle.validateUploadFile(file);
        photoHandle.validateFileFormat(file);

        try {
            ProjectPhoto projectPhoto = new ProjectPhoto();

            var name = FilenameUtils.removeExtension(file.getOriginalFilename());
            var extensionName = FileExtencion.getExtension(file.getOriginalFilename());
            var fileName = name + "." + extensionName;

            File filePathTemp = new File(FILE_UPLOAD_PATH+ fileName);
            file.transferTo(filePathTemp);

            projectPhoto.setFileName(fileName);
            projectPhoto.setFileFomate(extensionName);
            projectPhoto.setFileType(String.valueOf(FileType.PHOTO));
            projectPhoto.setPartUpload(FILE_UPLOAD_PATH + fileName);
            projectPhoto.setFileSize(file.getSize());

            return projectPhotoRepository.save(projectPhoto);

        } catch (Exception e) {
            log.error("Error while uploading file: {}", e.getMessage());
            throw new InternalServerError("Error while uploading file: " + e.getMessage());
        }
    }

    @Override
    public ProjectPhoto getProjectPhotoById(Long id) {
    return projectPhotoRepository.findById(id)
            .orElseThrow(() -> new ResoureNoteFoundException("Project photo not found with id: " + id));
    }
}
