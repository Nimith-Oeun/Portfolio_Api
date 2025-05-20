package com.personal.portfolio_api.service;

import com.personal.portfolio_api.model.ProjectPhoto;
import org.springframework.web.multipart.MultipartFile;

public interface ProjectPhotoService {

    ProjectPhoto uploadProjectPhoto(MultipartFile file);
}
