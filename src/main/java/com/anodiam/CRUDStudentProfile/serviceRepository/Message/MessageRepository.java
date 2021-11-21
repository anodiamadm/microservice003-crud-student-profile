package com.anodiam.CRUDStudentProfile.serviceRepository.Message;

import com.anodiam.CRUDStudentProfile.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer>{
}

