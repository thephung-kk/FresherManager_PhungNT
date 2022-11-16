package com.vmo.FresherManager_PhungNT.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CENTER_FRESHER")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CenterFresher extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CENTER_ID", nullable = false)
    private Center center;

    @ManyToOne
    @JoinColumn(name = "FRESHER_ID", nullable = false)
    private Fresher fresher;

    @Column(name = "START_DATE", nullable = false)
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

}
