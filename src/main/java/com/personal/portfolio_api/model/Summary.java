package com.personal.portfolio_api.model;

import com.personal.portfolio_api.enumerat.SummaryStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_summary")
public class Summary extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , length = 50)
    private String name;

    @Column(nullable = false , length = 50)
    private String nationality;

    @Email
    @Column(nullable = false , unique = true)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false , unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private SummaryStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    private Resume resumeId;



}
