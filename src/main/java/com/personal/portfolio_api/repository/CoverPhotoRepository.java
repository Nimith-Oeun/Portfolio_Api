package com.personal.portfolio_api.repository;

import com.personal.portfolio_api.model.CoverPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoverPhotoRepository extends JpaRepository<CoverPhoto, Long> {
}
