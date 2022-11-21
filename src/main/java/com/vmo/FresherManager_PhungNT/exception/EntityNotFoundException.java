package com.vmo.FresherManager_PhungNT.exception;

public class EntityNotFoundException extends AbstractCustomException{

    public EntityNotFoundException(ApiErrorDetail apiErrorDetail) {
        super(apiErrorDetail);
    }
}