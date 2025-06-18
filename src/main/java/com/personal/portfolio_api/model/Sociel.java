package com.personal.portfolio_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_social")
public class Sociel extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "facebook" , unique = true)
    private String facebook;

    @Column(name = "twitter" , unique = true)
    private String twitter;

    @Column(name = "instagram" , unique = true)
    private String instagram;

    @Column(name = "lingIn" , unique = true)
    private String linkedIn;

    @OneToOne(fetch = FetchType.LAZY)
    private UserProfile userProfile;
}
