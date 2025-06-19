package com.personal.portfolio_api.service;

import com.personal.portfolio_api.dto.ProfilePhotoDTO;
import com.personal.portfolio_api.model.ProfilePhoto;
import org.springframework.web.multipart.MultipartFile;



public interface ProfilePhotoService {

    ProfilePhoto uploadProfile(MultipartFile file , ProfilePhotoDTO profilePhotoDTO);
    ProfilePhoto getProfilePhotoById(Long id);
}
