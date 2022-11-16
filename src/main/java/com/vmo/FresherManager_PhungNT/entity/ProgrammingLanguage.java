package com.vmo.FresherManager_PhungNT.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "PROGRAMMING_LANGUAGE")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgrammingLanguage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;
}