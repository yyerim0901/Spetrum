package com.spectrum.repository;

import com.spectrum.entity.SBoardFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SBoardFileRepository extends JpaRepository<SBoardFile, Long> {
}
