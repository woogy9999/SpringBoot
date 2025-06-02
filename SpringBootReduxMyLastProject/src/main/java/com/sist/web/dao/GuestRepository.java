package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.GuestEntity;
import com.sist.web.entity.HotelEntity;
import com.sist.web.entity.LocationEntity;
import com.sist.web.entity.ShopEntity;

public interface GuestRepository extends JpaRepository<GuestEntity, Integer>{
	@Query(value="SELECT * "
			+ "FROM guest ORDER BY no ASC "
			+ "LIMIT :start,12",nativeQuery = true) 
	public List<GuestEntity> guestListData(@Param("start") int start);
	
	@Query(value="SELECT CEIL(COUNT(*)/12.0) FROM guest ")
	public int guestTotalPage(); 

	public GuestEntity findByNo(@Param("no") int no);
}
