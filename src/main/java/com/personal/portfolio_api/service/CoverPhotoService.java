package com.personal.portfolio_api.service;

import com.personal.portfolio_api.dto.CoverPhotoDTO;
import com.personal.portfolio_api.model.CoverPhoto;
import com.personal.portfolio_api.model.ProfilePhoto;
import org.springframework.web.multipart.MultipartFile;


public interface CoverPhotoService {

    CoverPhoto uploadCover(MultipartFile file , CoverPhotoDTO coverPhotoDTO);
    CoverPhoto getCoverPhotoById(Long id);
}
