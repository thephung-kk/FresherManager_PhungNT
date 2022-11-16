package com.vmo.FresherManager_PhungNT.controller;

import com.vmo.FresherManager_PhungNT.entity.Fresher;
import com.vmo.FresherManager_PhungNT.service.FresherService;
import lombok.RequiredArgsConstructor;
import model.request.FresherCreateRequest;
import model.response.ResponseObjectRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/freshers")
@RequiredArgsConstructor
public class FresherController {
    private final FresherService fresherService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Fresher> createFresher(@RequestBody @Valid FresherCreateRequest fresherCreateRequest) {
        return ResponseEntity.ok(fresherService.createFresher(fresherCreateRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> findAllFresher(){
        return ResponseEntity.ok(fresherService.findAllFresher());
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObjectRequest> updateFresher(@RequestBody Fresher newFresher, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(fresherService.updateFresher(newFresher,id));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObjectRequest> deleteFresher(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(fresherService.deleteFresher(id));
    }
}
