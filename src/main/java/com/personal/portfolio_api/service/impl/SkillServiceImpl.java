package com.personal.portfolio_api.service.impl;

import com.personal.portfolio_api.dto.SkillDTO;
import com.personal.portfolio_api.enumerat.FileType;
import com.personal.portfolio_api.exception.InternalServerError;
import com.personal.portfolio_api.exception.ResoureNoteFoundException;
import com.personal.portfolio_api.exceptionHandle.PhotoHandle;
import com.personal.portfolio_api.mapper.PhotoMapper;
import com.personal.portfolio_api.model.Skill;
import com.personal.portfolio_api.repository.SkillRepository;
import com.personal.portfolio_api.service.SkillService;
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
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final PhotoHandle photoHandle;
    private final PhotoMapper photoMapper;

    // Path to the directory where files will be uploaded during development
//    private static final String FILE_UPLOAD_PATH = System.getProperty("user.dir") + "/upload/skill_logo/";

    // Path to the directory where files will be uploaded server production
    @Value("${spring.uploadsPart}")
    private String FILE_UPLOAD_PATH;

    @Override
    public Skill uploadSkill(MultipartFile file, SkillDTO skillDTO) {

        photoHandle.validateFileFormat(file);
        photoHandle.validateUploadFile(file);

        try{
            Skill skill = new Skill();
            Skill userId = photoMapper.mapToSkill(skillDTO);

            var name = FilenameUtils.removeExtension(file.getOriginalFilename());
            var extensionName = FileExtencion.getExtension(file.getOriginalFilename());
            var fileName = name + "." + extensionName;

            File filePathTemp = new File(FILE_UPLOAD_PATH + fileName);
            file.transferTo(filePathTemp);

            skill.setFileName(fileName);
            skill.setFileFomate(extensionName);
            skill.setFileSize(file.getSize());
            skill.setPartUpload(FILE_UPLOAD_PATH + fileName);
            skill.setFileType(String.valueOf(FileType.LOGO));
            skill.setSkillTitle(skillDTO.getSkillTitle());
            skill.setUserProfileID(userId.getUserProfileID());

            return skillRepository.save(skill);


        }catch (Exception e){
            log.error("Error while uploading file: {}", e.getMessage());
            throw new InternalServerError("Error while uploading file: " + e.getMessage());
        }
    }

    @Override
    public Skill getSkillById(Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new ResoureNoteFoundException("Skill not found with id: " + id));
    }

    @Override
    public void deleteSkill(Long id) {

//         Fetch the skill with id from the database
        Skill skill = getSkillById(id);

//         Delete the file associated with the skill

        File Deletefile = new File(skill.getPartUpload());
        if (Deletefile.exists() && Deletefile.isFile()) {
            boolean deleted = Deletefile.delete();
            if (!deleted) {
                log.warn("Failed to delete file: {}", skill.getPartUpload());
            }else {
                log.info("File {} deleted successfully", skill.getPartUpload());
            }
        }else {
            log.info("File does not exist or is not a file: {}", skill.getPartUpload());
        }

//         Delete the skill from the database

        skillRepository.deleteById(id);
        log.info("Skill with id {} and associated file {} deleted successfully",
                id, skill.getPartUpload()
        );

    }
}
