package com.spectrum.repository;

import com.spectrum.entity.PBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public interface PBoardRepository extends JpaRepository<PBoard, Long> {

    public List<PBoard> findAllByUserId(Long userPK);

    String curLocation = "";
    //현재 위치에서 가장 가까운 순으로 정렬 -> POINT(경도, 위도)를 현재위치로 잡으면 됨
    String nativeQuery = "select id,content,created,picture,status,title,user_pk" +
            " st_distance_sphere(POINT("+ curLocation +"), point) as distance\n" +
            "from pboard\n" +
            "order by distance;";

}
