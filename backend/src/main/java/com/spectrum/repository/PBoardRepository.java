package com.spectrum.repository;

import com.spectrum.entity.PBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public interface PBoardRepository extends JpaRepository<PBoard, Long> {

    public List<PBoard> findAllByUserId(Long userPK);
}
