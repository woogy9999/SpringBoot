package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.GuestEntity;
import com.sist.web.entity.LocationEntity;
import com.sist.web.entity.ShopEntity;

public interface LocationRepository extends JpaRepository<LocationEntity, Integer>{
	@Query(value="SELECT * "
			+ "FROM location ORDER BY no ASC "
			+ "LIMIT :start,12",nativeQuery = true) 
	public List<LocationEntity> locationListData(@Param("start") int start);
	
	@Query(value="SELECT CEIL(COUNT(*)/12.0) FROM location ")
	public int locationTotalPage(); 


	public LocationEntity findByNo(@Param("no") int no);
}
