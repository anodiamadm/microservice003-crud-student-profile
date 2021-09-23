package com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Board;

import com.anodiam.CRUDStudentProfile.model.masterData.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface BoardRepository extends JpaRepository<Board, BigInteger> {
}
