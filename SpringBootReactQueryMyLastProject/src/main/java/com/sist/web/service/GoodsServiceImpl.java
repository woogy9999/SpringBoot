package com.sist.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.GoodsRepository;
import com.sist.web.entity.GoodsEntity;

@Service
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
	private GoodsRepository gDao;

	@Override
	public List<GoodsEntity> goodsMainData() {
		// TODO Auto-generated method stub
		return gDao.goodsMainData();
	}

	@Override
	public Map goodsListData(int page) {
		// TODO Auto-generated method stub
		Map map =new HashMap();
		int rowSize=12;
		int start=(rowSize*page)-(rowSize-1);
		int end=(rowSize*page);
		List<GoodsEntity> list=gDao.goodsListData(start, end);
		int count=gDao.goodsCount();
				
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
	public GoodsEntity goodsDetailData(int no) {
		// TODO Auto-generated method stub
		return gDao.goodsDetailData(no);
	}
	
	

}
