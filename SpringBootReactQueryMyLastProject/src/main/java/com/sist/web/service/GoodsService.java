package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.query.Param;

import com.sist.web.entity.GoodsEntity;

public interface GoodsService {
	public List<GoodsEntity> goodsMainData();
	public Map goodsListData(int page);
	
	public GoodsEntity goodsDetailData(int no);

}
