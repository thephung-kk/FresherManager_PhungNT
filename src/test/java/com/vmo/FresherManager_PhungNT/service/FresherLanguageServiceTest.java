package com.vmo.FresherManager_PhungNT.service;

import com.vmo.FresherManager_PhungNT.entity.Fresher;
import com.vmo.FresherManager_PhungNT.entity.FresherLanguage;
import com.vmo.FresherManager_PhungNT.entity.ProgrammingLanguage;
import com.vmo.FresherManager_PhungNT.exception.EntityNotFoundException;
import com.vmo.FresherManager_PhungNT.repository.FresherLanguageRepository;
import model.response.FresherLanguageResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class FresherLanguageServiceTest {
    @Autowired
    FresherLanguageService fresherLanguageService;
    @MockBean
    FresherLanguageRepository fresherLanguageRepository;

    @Test
    @DisplayName("Test findByLanguageName Success")
    void testFindByLanguageNameSuccess() {
        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
        programmingLanguage.setName("Java");
        programmingLanguage.setId(1L);

        Fresher johnFresher = Fresher.builder()
                .id(1L).name("John1").address("123 Street1").email("john1@email.com")
                .build();
        Fresher jerryFresher = Fresher.builder()
                .id(1L).name("Jerry1").address("123 Street2").email("jerry1@email.com")
                .build();

        FresherLanguage johnFresherLanguage = FresherLanguage.builder()
                .id(1L).language(programmingLanguage).fresher(johnFresher)
                .build();
        FresherLanguage jerryFresherLanguage = FresherLanguage.builder()
                .id(1L).language(programmingLanguage).fresher(jerryFresher)
                .build();

        List<FresherLanguage> list = new ArrayList<>();
        list.add(jerryFresherLanguage);
        list.add(johnFresherLanguage);

        // Return the mocked data when retrieving
        when(fresherLanguageRepository.findAllByLanguage_Name("Java")).thenReturn(list);

        //  Execute the service call
        List<FresherLanguageResponse> returnedList = fresherLanguageService.findAllFresherByLanguage("Java");

        assertNotNull(returnedList, "Fresher was not found!");
        assertEquals(list.get(0).getLanguage().getName(), returnedList.get(0).languageName());
        assertEquals(list.get(0).getFresher().getName(), returnedList.get(0).fresherName());
        assertEquals(list.get(1).getLanguage().getName(), returnedList.get(1).languageName());
        assertEquals(list.get(1).getFresher().getName(), returnedList.get(1).fresherName());
    }

    @Test
    @DisplayName("Test findByLanguageName Fail")
    void testFindByLanguageNameFail() {

        // Return the mocked data when retrieving
        when(fresherLanguageRepository.findAllByLanguage_Name(anyString())).thenReturn(List.of());

        // Expect fail
        assertThrows(EntityNotFoundException.class, () -> {
            fresherLanguageService.findAllFresherByLanguage(anyString());
        });

    }
    @Test
    @DisplayName("Test findByLanguageId Success")
    void testFindByLanguageIdSuccess() {
        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage();
        programmingLanguage.setName("Java");
        programmingLanguage.setId(1L);

        Fresher johnFresher = Fresher.builder()
                .id(1L).name("John1").address("123 Street1").email("john1@email.com")
                .build();
        Fresher jerryFresher = Fresher.builder()
                .id(1L).name("Jerry1").address("123 Street2").email("jerry1@email.com")
                .build();

        FresherLanguage johnFresherLanguage = FresherLanguage.builder()
                .id(1L).language(programmingLanguage).fresher(johnFresher)
                .build();
        FresherLanguage jerryFresherLanguage = FresherLanguage.builder()
                .id(1L).language(programmingLanguage).fresher(jerryFresher)
                .build();

        List<FresherLanguage> list = new ArrayList<>();
        list.add(jerryFresherLanguage);
        list.add(johnFresherLanguage);

        // Return the mocked data when retrieving
        when(fresherLanguageRepository.findAllByLanguage_Id(1L)).thenReturn(list);

        //  Execute the service call
        List<FresherLanguageResponse> returnedList = fresherLanguageService.findAllFresherByLanguageID(1L);

        assertNotNull(returnedList, "Fresher was not found!");
        assertEquals(list.get(0).getLanguage().getName(), returnedList.get(0).languageName());
        assertEquals(list.get(0).getFresher().getName(), returnedList.get(0).fresherName());
        assertEquals(list.get(1).getLanguage().getName(), returnedList.get(1).languageName());
        assertEquals(list.get(1).getFresher().getName(), returnedList.get(1).fresherName());
    }

    @Test
    @DisplayName("Test findByLanguageId Fail")
    void testFindByLanguageIdFail() {

        // Return the mocked data when retrieving
        when(fresherLanguageRepository.findAllByLanguage_Id(anyLong())).thenReturn(List.of());

        // Expect fail
        assertThrows(EntityNotFoundException.class, () -> {
            fresherLanguageService.findAllFresherByLanguageID(anyLong());
        });

    }
}