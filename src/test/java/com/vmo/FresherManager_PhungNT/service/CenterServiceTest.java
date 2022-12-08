package com.vmo.FresherManager_PhungNT.service;

import com.vmo.FresherManager_PhungNT.entity.Center;
import com.vmo.FresherManager_PhungNT.exception.EntityNotFoundException;
import com.vmo.FresherManager_PhungNT.repository.CenterRepository;
import model.response.CenterResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class CenterServiceTest {
    @Autowired
    private CenterService centerService;

    @MockBean
    private CenterRepository centerRepository;
    @Test
    @DisplayName("Test updateCenter Success")
    void testUpdateCenterSuccess() {
        Center c5Center = Center.builder()
                .id(1L).name("Center 5").code("c5").dob(LocalDate.parse("2022-10-15")).address("Floor 10")
                .build();
        Center newC5Center = Center.builder()
                .id(1L).name("Center 5 - 2").code("c5 - 2").dob(LocalDate.parse("2022-12-08")).address("Floor 10 - 2")
                .build();

        // Return the mocked data when retrieving
        when(centerRepository.findById(1L)).thenReturn(Optional.of(c5Center));
        when(centerRepository.save(c5Center)).thenReturn(newC5Center);

        //  Execute the service call
        CenterResponse updated = centerService.updateCenter(newC5Center,1L);

        assertEquals(updated.centerId(), newC5Center.getId());
        assertEquals(updated.dob(), newC5Center.getDob());
        assertEquals(updated.centerName(), newC5Center.getName());
        assertEquals(updated.centerCode(), newC5Center.getCode());
        assertEquals(updated.address(), newC5Center.getAddress());
    }

    @Test
    @DisplayName("Test updateCenter Fail")
    void testUpdateCenterFail() {
        Center newC5Center = Center.builder()
                .id(1L).name("Center 5 - 2").code("c5 - 2").dob(LocalDate.parse("2022-12-08")).address("Floor 10 - 2")
                .build();

        // Return the mocked data when retrieving
        when(centerRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Expect fail
        assertThrows(EntityNotFoundException.class, () -> {
            centerService.updateCenter(newC5Center,anyLong());
        });

    }
}