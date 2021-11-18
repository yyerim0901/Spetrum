package com.spectrum.repository;

import com.spectrum.entity.Dogging;
import com.spectrum.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoggingRepository extends JpaRepository<Dogging, Long> {

     public List<Dogging> findByUserId(Long userid);
     public void deleteById(Long id);
     public Dogging findFirstByUserIdOrderByDateDesc(Long userId);
     List<Dogging> findAllByUser(User user);
}