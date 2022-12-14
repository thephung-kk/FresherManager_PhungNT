package com.vmo.FresherManager_PhungNT.controller;

import com.vmo.FresherManager_PhungNT.entity.Fresher;
import com.vmo.FresherManager_PhungNT.service.AssignmentScoreService;
import com.vmo.FresherManager_PhungNT.service.FresherLanguageService;
import com.vmo.FresherManager_PhungNT.service.FresherService;
import lombok.RequiredArgsConstructor;
import model.request.FresherCreateRequest;
import model.response.FresherLanguageResponse;
import model.response.ResponseObjectRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import model.response.FresherResponse;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/freshers")
@RequiredArgsConstructor
public class FresherController {
    private final FresherService fresherService;
    private final FresherLanguageService fresherLanguageService;
    private final AssignmentScoreService assignmentScoreService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Fresher> createFresher(@RequestBody @Valid FresherCreateRequest fresherCreateRequest) {
        return ResponseEntity.ok(fresherService.createFresher(fresherCreateRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> findAllFresher() {
        return ResponseEntity.ok(fresherService.findAllFresher());
    }

    @GetMapping("/countAll")
    public ResponseEntity<ResponseObjectRequest> countAllFresher() {
        return ResponseEntity.ok(fresherService.countAllFresher());
    }

    @GetMapping("/id/{fresherId}")
    public ResponseEntity<FresherResponse> findById(@PathVariable Long fresherId) {
        return ResponseEntity.ok(fresherService.findById(fresherId));
    }

    @GetMapping("/languageId/{languageId}")
    public ResponseEntity<List<FresherLanguageResponse>> findByLanguageId(@PathVariable Long languageId) {
        return ResponseEntity.ok(fresherLanguageService.findAllFresherByLanguageID(languageId));
    }

    @GetMapping("/languageName/{languageName}")
    public ResponseEntity<List<FresherLanguageResponse>> findByLanguageName(@PathVariable String languageName) {
        return ResponseEntity.ok(fresherLanguageService.findAllFresherByLanguage(languageName));
    }

    @GetMapping("/name/{fresherName}")
    public ResponseEntity<List<FresherResponse>> findByName(@PathVariable String fresherName) {
        return ResponseEntity.ok(fresherService.findByName(fresherName));
    }

    @GetMapping("/email/{fresherEmail}")
    public ResponseEntity<List<FresherResponse>> findByEmail(@PathVariable String fresherEmail) {
        return ResponseEntity.ok(fresherService.findByEmail(fresherEmail));
    }

    @GetMapping("/avgScore/{fresherId}")
    public ResponseEntity<ResponseObjectRequest> finalScore(@PathVariable Long fresherId) {
        return ResponseEntity.ok(assignmentScoreService.finalScore(fresherId));
    }

    @GetMapping("/overAvgScore/{score}")
    public ResponseEntity<ResponseObjectRequest> findAllFresherByAvgScore(@PathVariable Long score) {
        return ResponseEntity.ok(assignmentScoreService.findAllFresherByAvgScore(score));
    }

    @PutMapping("/{fresherId}")
    public ResponseEntity<FresherResponse> updateFresher(@RequestBody Fresher newFresher, @PathVariable Long fresherId) {
        return ResponseEntity.status(HttpStatus.OK).body(fresherService.updateFresher(newFresher, fresherId));
    }

    @DeleteMapping("/{fresherId}")
    public ResponseEntity<ResponseObjectRequest> deleteFresher(@PathVariable Long fresherId) {
        return ResponseEntity.status(HttpStatus.OK).body(fresherService.deleteFresher(fresherId));
    }

}
