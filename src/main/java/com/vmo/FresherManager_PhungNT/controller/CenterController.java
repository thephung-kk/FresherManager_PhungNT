package com.vmo.FresherManager_PhungNT.controller;

import com.vmo.FresherManager_PhungNT.entity.Center;
import com.vmo.FresherManager_PhungNT.service.CenterFresherService;
import com.vmo.FresherManager_PhungNT.service.CenterService;
import lombok.RequiredArgsConstructor;
import model.request.CenterCreateRequest;
import model.request.CenterFresherCreateRequest;
import model.response.CenterFresherResponse;
import model.response.CenterResponse;
import model.response.ResponseObjectRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/centers")
@RequiredArgsConstructor
public class CenterController {

    private final CenterService centerService;
    private final CenterFresherService centerFresherService;

    @PostMapping("/createCenter")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Center> createCenter(@RequestBody @Valid CenterCreateRequest centerCreateRequest) {
        return ResponseEntity.ok(centerService.createCenter(centerCreateRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> findAllCenter(){
        return ResponseEntity.ok(centerService.findAllCenter());
    }

    @GetMapping("/{centerId}/freshers")
    public ResponseEntity<List<CenterFresherResponse>> getAllFresherByCenter(@PathVariable  Long centerId) {
        return ResponseEntity.ok(centerFresherService.findAllFreshersByCenterId(centerId));
    }

    @DeleteMapping("{centerId}")
    public ResponseEntity<ResponseObjectRequest> deleteCenter(@PathVariable Long centerId){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(centerService.deleteCenter(centerId));
    }

    @PutMapping("{centerId}")
    public ResponseEntity<CenterResponse> updateCenter(@RequestBody Center newCenter, @PathVariable Long centerId){
        return ResponseEntity.status(HttpStatus.OK).body(centerService.updateCenter(newCenter, centerId));
    }

    @PostMapping("/addFresherToCenter")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseObjectRequest> addFresherToCenter(@RequestBody @Valid CenterFresherCreateRequest centerFresherCreateRequest) {
        return ResponseEntity.ok(centerFresherService.addFresherToCenter(centerFresherCreateRequest));
    }

}