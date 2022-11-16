package com.vmo.FresherManager_PhungNT.service;

import com.vmo.FresherManager_PhungNT.entity.Assignment;
import model.request.AssignmentCreateRequest;

import java.util.List;

public interface AssignmentService {
    Assignment createAssignment(AssignmentCreateRequest assignmentCreateRequest);
    List<String> findAllAssignment();

}
