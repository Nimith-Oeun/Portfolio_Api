package com.personal.portfolio_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_skill")
public class Skill extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_Type")
    private String fileType;

    @Column(name = "file_Fomate")
    private String fileFomate;

    @Column(name = "file_Size")
    private double fileSize;

    @Column(name = "file_Name")
    private String fileName;

    @Column(name = "Part_Upload")
    private String partUpload;

    @Column(name = "skill_Title")
    private String skillTitle;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfileID;
}
