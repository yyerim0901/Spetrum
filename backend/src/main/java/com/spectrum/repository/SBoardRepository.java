package com.spectrum.repository;

import com.spectrum.entity.QUser;
import com.spectrum.entity.SBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SBoardRepository extends JpaRepository<SBoard, Long>{
    List<SBoard> findByUser(QUser user);
}
