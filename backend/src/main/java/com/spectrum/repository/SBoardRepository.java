package com.spectrum.repository;

import com.spectrum.entity.SBoard;
import com.spectrum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SBoardRepository extends JpaRepository<SBoard, Long>{
    List<SBoard> findByUser(User user);
}
