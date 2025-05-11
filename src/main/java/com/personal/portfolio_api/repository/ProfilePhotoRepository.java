package com.personal.portfolio_api.repository;

import com.personal.portfolio_api.model.ProfilePhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePhotoRepository extends JpaRepository<ProfilePhoto, Long> {
}
