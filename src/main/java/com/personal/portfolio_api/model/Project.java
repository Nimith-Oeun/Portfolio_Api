package com.personal.portfolio_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "portfolio_description")
    private String portfolioDescription;

    @Column(name = "project_title")
    private String projectTitle;

    @Column(name = "project_description")
    private String projectDescription;

    @OneToOne(fetch = FetchType.LAZY)
    private ProjectPhoto projectPhoto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;
}
