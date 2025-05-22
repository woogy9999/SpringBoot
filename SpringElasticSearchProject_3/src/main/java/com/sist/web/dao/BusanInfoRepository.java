package com.sist.web.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.BusanInfoEntity;
import java.util.*;
@Repository
public interface BusanInfoRepository extends ElasticsearchRepository<BusanInfoEntity, Integer>{
	// SELECT * FROM table_name , INSERT / UPDATE / DELETE
	// WHERE (조건문) findByNo
	public List<BusanInfoEntity> findByCno(@Param("cno") Integer cno);
	
	public List<BusanInfoEntity> findByTitleContaining(@Param("title") String title); 
	/*
	 		findByTitleStartsWith
	 		findByTitleEndswith
	 		findByTitleContaining 
	 */ 
}
