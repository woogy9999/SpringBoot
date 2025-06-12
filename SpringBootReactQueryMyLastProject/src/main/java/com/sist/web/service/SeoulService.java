package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.GoodsEntity;
import com.sist.web.entity.SeoulEntity;

public interface SeoulService {
	public Map seoulListData(int page);

	public List<SeoulEntity> seoulMainData();
	public SeoulEntity seoulDetailData(int sno);
	

}
