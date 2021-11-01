package com.spectrum.repository;

import com.spectrum.entity.PComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PCommentRepository extends JpaRepository<PComment, Long> {

    List<PComment> findByPboardId(Long pboardId);
}
