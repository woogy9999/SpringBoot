package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.LocationEntity;
import com.sist.web.entity.NatureEntity;
import com.sist.web.entity.ShopEntity;

public interface NatureRepository extends JpaRepository<NatureEntity, Integer>{
	@Query(value="SELECT * "
			+ "FROM nature ORDER BY no ASC "
			+ "LIMIT :start,12",nativeQuery = true) 
	public List<NatureEntity> natureListData(@Param("start") int start);
	
	@Query(value="SELECT CEIL(COUNT(*)/12.0) FROM nature ")
	public int natureTotalPage(); 


	public NatureEntity findByNo(@Param("no") int no);
}
