package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.BoardEntity;
import com.sist.web.entity.BoardVO;

import java.util.*;
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{
	//findAll()
	
	@Query(value = "SELECT no,subject,name,regdate,hit "
			+ "FROM board ORDER BY no DESC "
			+ "LIMIT :start,10",nativeQuery=true)
	public List<BoardVO> boardListData(@Param("start") int start);
	
	//상세보기 
	public BoardEntity findByNo(@Param("no") Integer no);  
	
	public List<BoardEntity> findByNameContaining(@Param("name") String name);
	public List<BoardEntity> findBysubjectContaining(@Param("subject") String subject);
	public List<BoardEntity> findBycontentContaining(@Param("content") String content);
}
