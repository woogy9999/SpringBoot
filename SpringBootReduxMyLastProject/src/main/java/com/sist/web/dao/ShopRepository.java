package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.LocationEntity;
import com.sist.web.entity.ShopEntity;


public interface ShopRepository extends JpaRepository<ShopEntity,Integer>{
	
	@Query(value="SELECT * "
			+ "FROM shop ORDER BY no ASC "
			+ "LIMIT :start,12",nativeQuery = true) 
	public List<ShopEntity> shopListData(@Param("start") int start);
	
	@Query(value="SELECT CEIL(COUNT(*)/12.0) FROM shop ")
	public int shopTotalPage(); 


	public ShopEntity findByNo(@Param("no") int no);
}
