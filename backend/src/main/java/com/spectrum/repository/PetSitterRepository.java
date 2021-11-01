package com.spectrum.repository;

import com.spectrum.entity.PetSitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetSitterRepository extends JpaRepository<PetSitter, Long> {

    public List<PetSitter> findAllByUser(Long userPK);

}
