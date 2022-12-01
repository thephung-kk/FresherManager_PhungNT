package com.vmo.FresherManager_PhungNT.service.impl;

import com.vmo.FresherManager_PhungNT.entity.CenterFresher;
import com.vmo.FresherManager_PhungNT.entity.Fresher;
import com.vmo.FresherManager_PhungNT.repository.CenterFresherRepository;
import com.vmo.FresherManager_PhungNT.repository.CenterRepository;
import com.vmo.FresherManager_PhungNT.repository.FresherRepository;
import com.vmo.FresherManager_PhungNT.service.CenterFresherService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.request.CenterFresherCreateRequest;
import model.response.CenterFresherResponse;
import model.response.FresherResponse;
import model.response.ResponseObjectRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CenterFresherServiceImpl implements CenterFresherService {

    private final CenterFresherRepository centerFresherRepository;
    private final CenterRepository centerRepository;
    private final FresherRepository fresherRepository;

    @Override
    public List<CenterFresherResponse> findAllFreshersByCenterId(Long centerId) {
        return centerFresherRepository.findAllByCenterId(centerId).stream()
                .map(centerFresher -> CenterFresherResponse.builder()
                        .centerId(centerFresher.getCenter().getId())
                        .centerName(centerFresher.getCenter().getName())
                        .fresherId(centerFresher.getFresher().getId())
                        .fresherName(centerFresher.getFresher().getName())
                        .startDate(centerFresher.getStartDate())
                        .endDate(centerFresher.getEndDate()).build())
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ResponseObjectRequest addFresherToCenter(CenterFresherCreateRequest centerFresherCreateRequest) {
        boolean fresherExist = fresherRepository.existsById(centerFresherCreateRequest.getFresherID());
        boolean centerExist = centerRepository.existsById(centerFresherCreateRequest.getCenterID());

        if (!fresherExist) {
            return new ResponseObjectRequest("Failed","Cannot find fresher has id = "+centerFresherCreateRequest.getFresherID(),"");
        } else if (!centerExist) {
            return new ResponseObjectRequest("Failed","Cannot find center has id = "+centerFresherCreateRequest.getCenterID(),"");
        } else {
            CenterFresher centerFresher = CenterFresher.builder()
                    .center(centerRepository.findById(centerFresherCreateRequest.getCenterID()).get())
                    .fresher(fresherRepository.findById(centerFresherCreateRequest.getFresherID()).get())
                    .startDate(centerFresherCreateRequest.getStartDate())
                    .endDate(centerFresherCreateRequest.getEndDate())
                    .build();
            centerFresherRepository.save(centerFresher);
            return new ResponseObjectRequest("Successed",
                    "Fresher has id = \'" + centerFresherCreateRequest.getFresherID()+"\' has been added to center has id = \'"+centerFresherCreateRequest.getCenterID()+"\'"
                    ,"");
        }
    }
}

