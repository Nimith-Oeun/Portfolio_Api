package com.personal.portfolio_api.service;

import com.personal.portfolio_api.dto.SkillDTO;
import com.personal.portfolio_api.model.Skill;
import org.springframework.web.multipart.MultipartFile;

public interface SkillService {

    Skill uploadSkill(MultipartFile file , SkillDTO skillDTO);
    Skill getSkillById(Long id);
    void deleteSkill(Long id);
}
