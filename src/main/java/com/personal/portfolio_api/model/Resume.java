package com.personal.portfolio_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_resume")
public class Resume extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resume_Description")
    private String resumeDescription;

    @OneToOne
    @JoinColumn(name = "summary_id")
    private Summary summary;

    @OneToOne
    @JoinColumn(name = "education_id")
    private Education education;

    @OneToOne
    @JoinColumn(name = "experience_id")
    private Experienc experience;

}
