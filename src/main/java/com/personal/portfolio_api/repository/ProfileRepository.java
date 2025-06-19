package com.personal.portfolio_api.repository;

import com.personal.portfolio_api.dto.UserProfileRespone;
import com.personal.portfolio_api.model.UserProfile;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByEmail(String email);
    boolean existsByEmail(String email);

    /*
        * (@Modifying) This annotation is used to indicate that the method will modify the database such as an update or delete operation
        * For read-only queries (`SELECT`): _No need for `@Modifying`

     */
    @Transactional
    @Modifying
    @Query("UPDATE UserProfile a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

    @Query("SELECT new com.personal.portfolio_api.dto.UserProfileRespone(" +
            "c.firstName, c.lastName, c.username, c.email, " +
            "c.phoneNumber, c.dateOfBirth, c.degree, c.website, " +
            "c.city, c.freelance, c.subject, " +
            "c.profileDescription, c.subjectDescription) " +
            "FROM UserProfile c")
    List<UserProfileRespone> getUserInfo();
}

