package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.vo.BusanFoodEntity;
import com.sist.web.dao.*;
import java.util.*;
import com.sist.web.vo.*;
public interface BusanFoodRepository extends JpaRepository<BusanFoodEntity, Integer>{
	@Query(value="SELECT fno,name,poster FROM busan_food "
			+ "ORDER BY fno ASC "
			+ "LIMIT :start,12",nativeQuery = true)
	public List<BusanFoodVO> busanListData(@Param("start") Integer start);
	
	public BusanFoodEntity findByFno(int fno);
}
