package com.spectrum.repository;

import com.spectrum.entity.Follow;
import com.spectrum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findByFollowAndFollower(User myid,User userid);

    List<Follow> findAllByFollow(User user);

    List<Follow> findAllByFollower(User user);

}
