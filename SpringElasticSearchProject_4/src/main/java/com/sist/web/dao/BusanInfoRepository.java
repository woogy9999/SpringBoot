package com.sist.web.dao;
import java.util.*;

import org.springframework.data.repository.query.Param;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.*;
// JPA
@Repository
public interface BusanInfoRepository extends ElasticsearchRepository<BusanInfoEntity,Integer>{
	
	public List<BusanInfoEntity> findByTitleContaining(@Param("title") String title);
	
	@Query("{\"wildcard\": {\"title\": {\"value\": \"*?0*\"}}}")
	public List<BusanInfoEntity> busanFindData(@Param("title") String title);
	//@Query("{\"match\": {\"title\": \"*?0*\"}}")
	//public List<BusanInfoEntity> busanFindData(@Param("title") String title);
	
	 
	
	public List<BusanInfoEntity> findByCno(@Param("cno") Integer cno);
	
	@Query("{\"match\": {\"cno\":?0}}")
	public List<BusanInfoEntity> busanCnoData(@Param("cno") Integer cno);
	
	
	
	
	@Query("{\"match\": {\"id\":?0}}")
	public BusanInfoEntity busanDetailData(@Param("id") Integer id);
	
	public Optional<BusanInfoEntity> findById(@Param("id") String id);
	
}
