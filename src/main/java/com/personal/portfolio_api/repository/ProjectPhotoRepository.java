package com.personal.portfolio_api.repository;

import com.personal.portfolio_api.model.ProjectPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectPhotoRepository extends JpaRepository<ProjectPhoto, Long> {
}
