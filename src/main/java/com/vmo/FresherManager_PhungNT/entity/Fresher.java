package com.vmo.FresherManager_PhungNT.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "FRESHER")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fresher extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DOB", nullable = false)
    private LocalDate dob;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @OneToMany(mappedBy = "fresher" )
    private List<CenterFresher> centerFresherList;

    @OneToMany(mappedBy = "fresher" )
    private List<FresherLanguage> fresherLanguageList;

    @OneToMany(mappedBy = "fresher" )
    private List<AssignmentScore> assignmentScoreList;

}