package com.anodiam.CRUDStudentProfile.serviceRepository.errorHandling;
import com.anodiam.CRUDStudentProfile.model.common.MessageResponse;

public interface ErrorHandlingService
{
    MessageResponse GetErrorMessage(String errorMessage);
}