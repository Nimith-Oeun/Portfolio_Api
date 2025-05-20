package com.personal.portfolio_api.repository;

import com.personal.portfolio_api.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> id(Long id);
    // Custom query methods can be defined here if needed
}
