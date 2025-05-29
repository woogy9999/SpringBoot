package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.HotelEntity;

import java.util.*;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Integer>{
	@Query(value="SELECT * FROM seoul_hotel "
			+ "ORDER BY no ASC "
			+ "LIMIT 0,12",nativeQuery = true)
	public List<HotelEntity> hotelMainData();
	
	@Query(value="SELECT * "
			+ "FROM seoul_hotel ORDER BY no ASC "
			+ "LIMIT :start,12",nativeQuery = true) 
	public List<HotelEntity> hotelListData(@Param("start") int start);
	
	@Query(value="SELECT CEIL(COUNT(*)/12.0) FROM seoul_hotel ")
	public int hotelTotalPage(); 

	public HotelEntity findByNo(@Param("no") int no);
}
