package com.spectrum.repository;

import com.spectrum.entity.SBoard;
import com.spectrum.entity.SBoardFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SBoardFileRepository extends JpaRepository<SBoardFile, Long> {
    List<SBoardFile> findAllById(Long id);

//    List<SBoardFile> findAllBySBoard(SBoard sBoard);
}
