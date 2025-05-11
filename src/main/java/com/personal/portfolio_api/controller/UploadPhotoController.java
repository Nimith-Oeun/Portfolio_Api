package com.personal.portfolio_api.controller;


import com.personal.portfolio_api.dto.SocielDTO;
import com.personal.portfolio_api.exception.ApiResponeUtils;
import com.personal.portfolio_api.mapper.SocielMapper;
import com.personal.portfolio_api.model.ProfilePhoto;
import com.personal.portfolio_api.model.Sociel;
import com.personal.portfolio_api.service.CoverPhotoService;
import com.personal.portfolio_api.service.ProfilePhotoService;
import com.personal.portfolio_api.service.SocielService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/portfolio/upload-photo")
@RequiredArgsConstructor
@Slf4j
public class UploadPhotoController {

    private final ProfilePhotoService profilePhotoService;
    private final CoverPhotoService coverPhotoService;


    @PostMapping("/profile")
    public ResponseEntity<?> uploadProfile(@RequestParam("file") MultipartFile file) {

        try{
            log.info("Uploading profile photo" + file.getOriginalFilename());
            profilePhotoService.uploadProfile(file);
            return ResponseEntity.ok(ApiResponeUtils.successRespone("File uploaded successfully"));
        }catch (Exception e){
            log.info("Error occurred while uploading photo: {}", e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("status", HttpStatus.BAD_REQUEST, "File to upload file", e.getMessage()));
        }
    }

    @PostMapping("/cover")
    public ResponseEntity<?> uploadCover(@RequestParam("file") MultipartFile file) {

        try{
            log.info("Uploading cover photo" + file.getOriginalFilename());
            coverPhotoService.uploadCover(file);
            return ResponseEntity.ok(ApiResponeUtils.successRespone("File uploaded successfully"));
        }catch (Exception e){
            log.info("Error occurred while uploading photo: " + e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("status", HttpStatus.BAD_REQUEST, "File to upload file", e.getMessage()));
        }
    }

}
