package com.anodiam.CRUDStudentProfile.serviceRepository.errorHandling;

import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;

abstract class ErrorHandlingImpl implements ErrorHandlingService {

    @Override
    public MessageResponse GetErrorMessage(String errorMessage) {
        return new ErrorHandlingDal().GetErrorMessage(errorMessage);
    }
}