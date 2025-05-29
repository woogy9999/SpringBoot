package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.BusanFoodEntity;
import com.sist.web.vo.BusanFoodVO;

import java.util.*;
/*
 * 	public int getNo();
	public String getPoster();
	public String getName();
	public String getType();
	public int getHit();
	public int getJjimcount();
	public int getReplycount();
	public int getLikecount();
	public double getScore();
	public String getTheme();
 */

@Repository
public interface BusanFoodRepository extends JpaRepository<BusanFoodEntity, Integer>{
	@Query(value="SELECT fno,poster,name,address,hit,jjimcount,likecount,score,type,parking "
			+ "FROM busan_food ORDER BY fno ASC "
			+ "LIMIT 0,9",nativeQuery = true) 
	public List<BusanFoodVO> busanMainData();
	
	
	@Query(value="SELECT fno,poster,name,address,hit,jjimcount,likecount,score,type,parking "
			+ "FROM busan_food ORDER BY fno ASC "
			+ "LIMIT :start,12",nativeQuery = true) 
	public List<BusanFoodVO> busanListData(@Param("start") int start);
	
	@Query(value="SELECT CEIL(COUNT(*)/12.0) FROM busan_food ")
	public int busanFoodTotalPage();
	
	//SELECT * FROM busan_food WHERE fno=?
	public BusanFoodEntity findByFno(@Param("fno") int fno);
}
