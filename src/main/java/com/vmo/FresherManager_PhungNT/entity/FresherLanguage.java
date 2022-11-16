package com.vmo.FresherManager_PhungNT.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "FRESHER_LANGUAGE")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FresherLanguage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "LANGUAGE_ID", nullable = false)
    private ProgrammingLanguage language;

    @ManyToOne
    @JoinColumn(name = "FRESHER_ID", nullable = false)
    private Fresher fresher;



}
