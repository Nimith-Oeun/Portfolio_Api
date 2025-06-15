//package com.personal.portfolio_api.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity
//@Data
//@Table(name = "tbl_user_profile")
//public class UserProfile {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "first_name")
//    private String firstName;
//
//    @Column(name = "last_name")
//    private String lastName;
//
//    private String username;
//
//    private String email;
//
//    @Column(name = "phone_number")
//    private String phoneNumber;
//
//    @Column(name = "date_of_birth")
//    private String dateOfBirth;
//
//    private String degree;
//
//    private String website;
//
//    private String city;
//
//    private String freelance = "none";
//
//    private String subject;
//
//    @Column(name = "profile_description")
//    private String profileDescription;
//
//    @Column(name = "subject_description")
//    private String subjectDescription;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    private Sociel sociel;
//
//    private ProfilePhoto profilePhoto;
//
//    private CoverPhoto coverPhoto;
//
//    private Skill skill;
//
//    private Resume resume;
//
//    private ProjectPhoto projectPhoto;
//}
