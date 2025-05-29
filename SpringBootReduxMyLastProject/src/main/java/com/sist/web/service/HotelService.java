package com.sist.web.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.HotelEntity;

public interface HotelService {
	public List<HotelEntity> hotelMainData();
	public List<HotelEntity> hotelListData(int start);
	
	public int hotelTotalPage();
	public HotelEntity hotelDetailData(int no); 
}
