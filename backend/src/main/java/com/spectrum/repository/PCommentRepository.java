package com.spectrum.repository;

import com.spectrum.entity.PComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PCommentRepository extends JpaRepository<PComment, Long> {

}
