package com.sist.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.GoodsRepository;
import com.sist.web.dao.SeoulRepository;
import com.sist.web.entity.GoodsEntity;
import com.sist.web.entity.SeoulEntity;

@Service
public class SeoulServiceImpl implements SeoulService{

	
	@Autowired
	private SeoulRepository sDao;

	

	@Override
	public Map seoulListData(int page) {
		// TODO Auto-generated method stub
		Map map =new HashMap();
		int rowSize=12;
		int start=(rowSize*page)-(rowSize-1);
		int end=(rowSize*page);
		List<SeoulEntity> list=sDao.seoulListData(start, end);
		int count=sDao.seoulCount();
				
		int totalpage=(int)(Math.ceil(count/12.0));
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage) 
			endPage=totalpage;
		 
		map.put("list", list);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("curpage", page); 
		map.put("totalpage", totalpage);
		return map;
	}

	@Override
	public SeoulEntity seoulDetailData(int sno) {
		// TODO Auto-generated method stub
		return sDao.seoulDetailData(sno);
	}

	@Override
	public List<SeoulEntity> seoulMainData() {
		// TODO Auto-generated method stub
		return sDao.seoulMainData();
	}
	
}
