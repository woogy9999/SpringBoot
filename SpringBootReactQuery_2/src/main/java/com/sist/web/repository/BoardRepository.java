package com.sist.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.*;

import java.util.*;
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{
	
	@Query(value="SELECT no,subject,name,TO_CHAR(content) AS content,TO_CHAR(regdate,'yyyy-MM-dd') as dbday,hit,num "
			+ "FROM (SELECT no,subject,name,content,regdate,hit,rownum as num "
			+ "FROM (SELECT no,subject,name,content,regdate,hit "
			+ "FROM board ORDER BY no DESC ))"
			+ "WHERE num BETWEEN :start AND :end",nativeQuery =true)
	public List<BoardVO> boardListData(@Param("start") Integer start,@Param("end") Integer end);
	
	public BoardEntity findByNo(int no);
	
	@Query(value="SELECT NVL(MAX(no)+1,1) FROM board",nativeQuery = true)
	public int maxNo();
}
