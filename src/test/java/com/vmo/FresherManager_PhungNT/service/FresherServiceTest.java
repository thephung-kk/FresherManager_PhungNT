package com.vmo.FresherManager_PhungNT.service;

import com.vmo.FresherManager_PhungNT.entity.Fresher;
import com.vmo.FresherManager_PhungNT.exception.EntityNotFoundException;
import com.vmo.FresherManager_PhungNT.repository.FresherRepository;
import model.response.FresherResponse;
import model.response.ResponseObjectRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class FresherServiceTest {

    @Autowired
    private FresherService fresherService;

    @MockBean
    FresherRepository fresherRepository;

    @Test
    @DisplayName("Test findById Success")
    void testFindByIdSuccess() {
        Fresher johnFresher = Fresher.builder()
                .id(1L).name("John").address("123 Street").email("john@email.com")
                .build();

        // Return the mocked data when retrieving
        when(fresherRepository.findById(1L)).thenReturn(Optional.of(johnFresher));

        //  Execute the service call
        FresherResponse returnedFresher = fresherService.findById(1L);

        assertNotNull(returnedFresher, "Fresher was not found!");
        assertEquals(johnFresher.getName(), returnedFresher.fresherName());
        assertEquals(johnFresher.getId(), returnedFresher.fresherId());
    }

    @Test
    @DisplayName("Test findById Fail")
    void testFindByIdFail() {

        // Return the mocked data when retrieving
        when(fresherRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Expect fail
        assertThrows(EntityNotFoundException.class, () -> {
            fresherService.findById(1L);
        });

    }

    @Test
    @DisplayName("Test findByName Success")
    void testFindByNameSuccess() {
        Fresher johnFresher = Fresher.builder()
                .id(1L).name("John").address("123 Street").email("john@email.com")
                .build();
        Fresher punFresher = Fresher.builder()
                .id(2L).name("Pun").address("1234 Street").email("pun@email.com")
                .build();
        Fresher jerryFresher = Fresher.builder()
                .id(3L).name("Jerry").address("12345 Street").email("jerry@email.com")
                .build();
        List<Fresher> list = new ArrayList<>();
        list.add(johnFresher);
        list.add(jerryFresher);

        // Return the mocked data when retrieving
        when(fresherRepository.findFresherByNameContaining("J")).thenReturn(list);

        //  Execute the service call
        List<FresherResponse> returnedList = fresherService.findByName("J");

        assertNotNull(returnedList, "Fresher was not found!");
        assertEquals(list.get(0).getName(), returnedList.get(0).fresherName());
        assertEquals(list.get(0).getId(), returnedList.get(0).fresherId());
        assertEquals(list.get(1).getName(), returnedList.get(1).fresherName());
        assertEquals(list.get(1).getId(), returnedList.get(1).fresherId());
    }

    @Test
    @DisplayName("Test findByName Fail")
    void testFindByNameFail() {

        // Return the mocked data when retrieving
        when(fresherRepository.findFresherByNameContaining(anyString())).thenReturn(List.of());

        // Expect fail
        assertThrows(EntityNotFoundException.class, () -> {
            fresherService.findByName("J");
        });
    }

    @Test
    @DisplayName("Test findByEmail Success")
    void testFindByEmailSuccess() {
        Fresher johnFresher = Fresher.builder()
                .id(1L).name("John").address("123 Street").email("john1@email.com")
                .build();
        Fresher punFresher = Fresher.builder()
                .id(2L).name("Pun").address("1234 Street").email("pun1@email.com")
                .build();
        Fresher jerryFresher = Fresher.builder()
                .id(3L).name("Jerry").address("12345 Street").email("jerry2@email.com")
                .build();
        List<Fresher> list = new ArrayList<>();
        list.add(johnFresher);
        list.add(punFresher);

        // Return the mocked data when retrieving
        when(fresherRepository.findAllByEmailContaining("1@gmail.com")).thenReturn(list);

        //  Execute the service call
        List<FresherResponse> returnedList = fresherService.findByEmail("1@gmail.com");

        assertNotNull(returnedList, "Fresher was not found!");
        assertEquals(list.get(0).getName(), returnedList.get(0).fresherName());
        assertEquals(list.get(0).getId(), returnedList.get(0).fresherId());
        assertEquals(list.get(1).getName(), returnedList.get(1).fresherName());
        assertEquals(list.get(1).getId(), returnedList.get(1).fresherId());
    }

    @Test
    @DisplayName("Test findByEmail Fail")
    void testFindByEmailFail() {

        // Return the mocked data when retrieving
        when(fresherRepository.findAllByEmailContaining(anyString())).thenReturn(List.of());

        // Expect fail
        assertThrows(EntityNotFoundException.class, () -> {
            fresherService.findByEmail("Phung@gmaill.com");
        });
    }

    @Test
    @DisplayName("Test updateFresher Success")
    void testUpdateFresherSuccess() {
        Fresher johnFresher = Fresher.builder()
                .id(1L).name("John1").address("123 Street1").email("john1@email.com")
                .build();
        Fresher newJohnFresher = Fresher.builder()
                .id(1L).name("John2").address("123 Street2").email("john2@email.com")
                .build();
        // Return the mocked data when retrieving
        when(fresherRepository.findById(1L)).thenReturn(Optional.of(johnFresher));
        when(fresherRepository.save(johnFresher)).thenReturn(newJohnFresher);

        //  Execute the service call
        FresherResponse updated = fresherService.updateFresher(newJohnFresher,1L);

        assertEquals(updated.fresherId(), newJohnFresher.getId());
        assertEquals(updated.dob(), newJohnFresher.getDob());
        assertEquals(updated.fresherName(), newJohnFresher.getName());
        assertEquals(updated.phone(), newJohnFresher.getPhone());
        assertEquals(updated.email(), newJohnFresher.getEmail());
        assertEquals(updated.address(), newJohnFresher.getAddress());
    }

    @Test
    @DisplayName("Test updateFresher Fail")
    void testUpdateFresherFail() {
        Fresher newJohnFresher = Fresher.builder()
                .id(1L).name("John2").address("123 Street2").email("john2@email.com")
                .build();

        // Return the mocked data when retrieving
        when(fresherRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Expect fail
        assertThrows(EntityNotFoundException.class, () -> {
            fresherService.updateFresher(newJohnFresher,anyLong());
        });

    }
}