package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;
import com.sist.web.entity.*;
public interface SeoulRepository extends JpaRepository<SeoulEntity, Integer> {

	@Query(value="SELECT sno, title, poster, msg, address, hit, likecount, replycount, num "
			+"FROM (SELECT sno, title, poster, msg, address, hit, likecount, replycount ORDER BY sno) "
			+"WHERE num<=10",nativeQuery = true)
	public List<SeoulEntity> seoulMainData();
	
    @Query(value = "SELECT sno, title, poster, msg, address, hit, likecount, replycount, num "
            + "FROM (SELECT sno, title, poster, msg, address, hit, likecount, replycount, rownum as num "
            + "FROM (SELECT /*+ INDEX_ASC(seoul_nature sn_no_pk)*/ sno, title, poster, msg, address, hit, likecount, replycount "
            + "FROM seoul_nature)) "
            + "WHERE num BETWEEN :start AND :end", nativeQuery = true)
    public List<SeoulEntity> seoulListData(@Param("start") Integer start, @Param("end") Integer end);

    @Query(value = "SELECT COUNT(*) FROM seoul_nature", nativeQuery = true)
    public int seoulCount(); 

    @Query(value = "SELECT sno, title, poster, msg, address, hit, likecount, replycount "
            + "FROM seoul_nature WHERE sno = :sno", nativeQuery = true)
    public SeoulEntity seoulDetailData(@Param("sno") int sno);
}