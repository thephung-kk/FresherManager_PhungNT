package com.vmo.FresherManager_PhungNT.service;

import com.vmo.FresherManager_PhungNT.entity.Center;
import com.vmo.FresherManager_PhungNT.entity.Fresher;
import com.vmo.FresherManager_PhungNT.repository.CenterFresherRepository;
import com.vmo.FresherManager_PhungNT.repository.CenterRepository;
import com.vmo.FresherManager_PhungNT.repository.FresherLanguageRepository;
import com.vmo.FresherManager_PhungNT.repository.FresherRepository;
import model.request.CenterFresherCreateRequest;
import model.response.ResponseObjectRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CenterFresherServiceTest {

    @Autowired
    CenterFresherService centerFresherService;
    @MockBean
    CenterFresherRepository centerFresherRepository;
    @MockBean
    CenterRepository centerRepository;
    @MockBean
    FresherRepository fresherRepository;
    @Autowired
    private FresherLanguageRepository fresherLanguageRepository;

    @Test
    @DisplayName("Test addFresherToCenter Success")
    void testAddFresherToCenterSuccess(){
        Center c5Center = Center.builder()
                .id(1L).name("Center 5").code("c5").dob(LocalDate.parse("2022-10-15")).address("Floor 10")
                .build();
        Fresher johnFresher = Fresher.builder()
                .id(1L).name("John1").address("123 Street1").email("john1@email.com")
                .build();

        CenterFresherCreateRequest centerFresherCreateRequest = new CenterFresherCreateRequest();
        centerFresherCreateRequest.setCenterID(1L);
        centerFresherCreateRequest.setFresherID(1L);
        centerFresherCreateRequest.setEndDate(LocalDate.parse("2022-12-16"));
        centerFresherCreateRequest.setStartDate(LocalDate.parse("2022-11-16"));

        // Return the mocked data when retrieving
        when(fresherRepository.existsById(1L)).thenReturn(true);
        when(fresherRepository.findById(1L)).thenReturn(Optional.of(johnFresher));
        when(centerRepository.existsById(1L)).thenReturn(true);
        when(centerRepository.findById(1L)).thenReturn(Optional.of(c5Center));
        //  Execute the service call
        ResponseObjectRequest reponseAddFresherToCenter = centerFresherService.addFresherToCenter(centerFresherCreateRequest);
        assertEquals(reponseAddFresherToCenter.getStatus(),"Successed");
    }

    @Test
    @DisplayName("Test addFresherToCenter fail byCenter")
    void testAddFresherToCenterFail1(){
        CenterFresherCreateRequest centerFresherCreateRequest = new CenterFresherCreateRequest();
        centerFresherCreateRequest.setCenterID(1L);
        centerFresherCreateRequest.setFresherID(1L);
        centerFresherCreateRequest.setEndDate(LocalDate.parse("2022-12-16"));
        centerFresherCreateRequest.setStartDate(LocalDate.parse("2022-11-16"));

        // Return the mocked data when retrieving
        when(fresherRepository.existsById(1L)).thenReturn(true);
        when(centerRepository.existsById(1L)).thenReturn(false);
        //  Execute the service call
        ResponseObjectRequest reponseAddFresherToCenter = centerFresherService.addFresherToCenter(centerFresherCreateRequest);
        assertEquals(reponseAddFresherToCenter.getStatus(),"Failed");
    }
    @Test
    @DisplayName("Test addFresherToCenter fail byFresher")
    void testAddFresherToCenterFail2(){
        CenterFresherCreateRequest centerFresherCreateRequest = new CenterFresherCreateRequest();
        centerFresherCreateRequest.setCenterID(1L);
        centerFresherCreateRequest.setFresherID(1L);
        centerFresherCreateRequest.setEndDate(LocalDate.parse("2022-12-16"));
        centerFresherCreateRequest.setStartDate(LocalDate.parse("2022-11-16"));

        // Return the mocked data when retrieving
        when(fresherRepository.existsById(1L)).thenReturn(false);
        when(centerRepository.existsById(1L)).thenReturn(true);
        //  Execute the service call
        ResponseObjectRequest reponseAddFresherToCenter = centerFresherService.addFresherToCenter(centerFresherCreateRequest);
        assertEquals(reponseAddFresherToCenter.getStatus(),"Failed");
    }
}