package com.sist.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.vo.BusanInfoEntity;

public interface BusanInfoService {
	public List<BusanInfoEntity> busanAllData(int page);
	// findAll()
	public int[] getPageData(int page);
	// count()
	// save , delete
	
	public List<BusanInfoEntity> findByTitleContaining(@Param("title") String title);
	public List<BusanInfoEntity> busanFindData(@Param("title") String title);
	
	public List<BusanInfoEntity> findByCno(@Param("cno") Integer cno);
	public List<BusanInfoEntity> busanCnoData(@Param("cno") Integer cno);
	
	public BusanInfoEntity busanDetailData(@Param("id") Integer id);
	public Optional<BusanInfoEntity> findById(@Param("id") String id);
}
