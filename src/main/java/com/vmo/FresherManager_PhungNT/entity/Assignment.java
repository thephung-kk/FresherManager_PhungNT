package com.vmo.FresherManager_PhungNT.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "ASSIGNMENT")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Assignment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Min(0)
    @Max(100)
    @Column(name = "PERCENTAGE", nullable = false)
    private Integer percentage;

    @Column(name = "NAME",nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;


}