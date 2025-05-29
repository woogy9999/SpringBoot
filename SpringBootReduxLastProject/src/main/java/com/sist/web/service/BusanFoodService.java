package com.sist.web.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.BusanFoodEntity;
import com.sist.web.entity.BusanInfoEntity;
import com.sist.web.vo.BusanFoodVO;

public interface BusanFoodService {
	public List<BusanFoodVO> busanMainData();
	public List<BusanInfoEntity> busanInfoMainData();

	
	public List<BusanFoodVO> busanListData(int start);
	public int busanFoodTotalPage();
	
	public BusanFoodEntity busanDetailData(int fno);
	

	public int busanTotalPage(int cno);
	public List<BusanInfoEntity> busanInfoListData(int cno, int start);		
	public List<BusanInfoEntity> findByTitleContaining (String title);
}
 