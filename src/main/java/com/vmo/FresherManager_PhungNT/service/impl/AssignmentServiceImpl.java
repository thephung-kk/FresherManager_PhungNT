package com.vmo.FresherManager_PhungNT.service.impl;

import com.vmo.FresherManager_PhungNT.entity.Assignment;
import com.vmo.FresherManager_PhungNT.repository.AssignmentRepository;
import com.vmo.FresherManager_PhungNT.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.request.AssignmentCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;

    @Transactional
    @Override
    public Assignment createAssignment(AssignmentCreateRequest assignmentCreateRequest) {
        var assignment = Assignment.builder()
                .percentage(assignmentCreateRequest.getPercentage())
                .name(assignmentCreateRequest.getName())
                .description(assignmentCreateRequest.getDescription())
                .build();
        return assignmentRepository.save(assignment);
    }

    @Override
    public List<String> findAllAssignment() {
        return null;
    }
}
