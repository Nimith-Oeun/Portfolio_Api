package com.personal.portfolio_api.controller;


import com.personal.portfolio_api.dto.SkillDTO;
import com.personal.portfolio_api.exception.ApiResponeUtils;
import com.personal.portfolio_api.exception.BadRequestException;
import com.personal.portfolio_api.service.SkillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/v1/portfolio/upload-skill")
@RequiredArgsConstructor
@Slf4j
public class UploadSkillController {

    private final SkillService skillService;


    @PostMapping("")
    public ResponseEntity<?> uploadSkill(@RequestParam("file") MultipartFile file , SkillDTO skillDTO) {

        try{
            log.info("Uploading profile photo" + file.getOriginalFilename());
            skillService.uploadSkill(file, skillDTO);
            return ResponseEntity.ok(ApiResponeUtils.successRespone("File uploaded successfully"));
        }catch (Exception e){
            log.info("Error occurred while uploading photo: {}", e.getMessage());
            return ResponseEntity.badRequest().body(new BadRequestException("File to upload file " + e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable Long id) {
            log.info("Deleting skill with id: " + id);
            skillService.deleteSkill(id);
            return ResponseEntity.ok(ApiResponeUtils.successRespone("Skill deleted successfully")
            );
    }

}
