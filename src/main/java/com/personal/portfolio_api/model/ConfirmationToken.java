package com.personal.portfolio_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_confirmation_token")
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "expiry_at", nullable = false)
    private LocalDateTime expiryAt;

    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            name = "app_user_id",
            referencedColumnName = "id",
            nullable = false
    )
    private UserProfile userProfile;


    public ConfirmationToken(String token,
                             LocalDateTime now,
                             LocalDateTime localDateTime,
                             UserProfile userProfile) {
        this.token = token;
        this.createdAt = now;
        this.expiryAt = localDateTime;
        this.userProfile = userProfile;
    }
}