package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.BoardEntity;
import com.sist.web.vo.BusanFoodVO;

import java.util.*;
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{
	
	
	@Query(value="SELECT * FROM board ORDER BY no DESC "
			+ "LIMIT :start,10",nativeQuery =true)
	public List<BoardEntity> boardListData(@Param("start") int start);
	
	public BoardEntity findByNo(int no);
}
