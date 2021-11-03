package com.spectrum.repository;

import com.spectrum.entity.SBoard;
import com.spectrum.entity.SBoardFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SBoardFileRepository extends JpaRepository<SBoardFile, Long> {
    List<SBoardFile> findAllById(Long id);
    Optional<List<SBoardFile>> findBysBoard(SBoard sb);
}
