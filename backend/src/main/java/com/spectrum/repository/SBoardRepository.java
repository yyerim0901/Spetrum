package com.spectrum.repository;

import com.spectrum.entity.SBoard;
import com.spectrum.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SBoardRepository extends JpaRepository<SBoard, Long>{

    Optional<SBoard> findById(Long id);

    List<SBoard> findAllByUser(User user, Pageable pageable);

    List<SBoard> findAllByUser(User user);
}
