package com.spectrum.repository;

import com.spectrum.entity.SBoard;
import com.spectrum.entity.SComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SCommentRepository extends JpaRepository<SComment, Long> {
    Optional<List<SComment>> findBysBoard(SBoard sboard);
//    Optional<SComment> findById(Long scommentid);
}
