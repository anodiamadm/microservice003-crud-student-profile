package com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Language;

import com.anodiam.CRUDStudentProfile.model.masterData.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
