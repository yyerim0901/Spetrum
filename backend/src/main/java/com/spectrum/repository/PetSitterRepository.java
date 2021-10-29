package com.spectrum.repository;

import com.spectrum.entity.PetSitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetSitterRepository extends JpaRepository<PetSitter, Long> {



}
