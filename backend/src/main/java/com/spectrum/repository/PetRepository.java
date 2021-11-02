package com.spectrum.repository;

import com.spectrum.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    public List<Pet> findAllByKeyword(int keyword);

}
