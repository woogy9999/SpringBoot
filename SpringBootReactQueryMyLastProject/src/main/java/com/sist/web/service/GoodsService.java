package com.sist.web.service;

import java.util.List;
import java.util.Map;

import com.sist.web.entity.GoodsEntity;

public interface GoodsService {
	public List<GoodsEntity> goodsMainData();
	public Map goodsListData(int page);

}
