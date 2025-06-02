package com.sist.web.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.GuestEntity;
import com.sist.web.entity.HotelEntity;
import com.sist.web.entity.LocationEntity;
import com.sist.web.entity.NatureEntity;
import com.sist.web.entity.ShopEntity;

public interface HotelService {
	public List<HotelEntity> hotelMainData();
	public List<HotelEntity> hotelListData(int start);
	
	public int hotelTotalPage();
	public HotelEntity hotelDetailData(int no); 
	
	
	public List<ShopEntity> shopListData(int start);
	public int shopTotalPage(); 
	
	public List<GuestEntity> guestListData(int start);
	public int guestTotalPage(); 
	
	public List<LocationEntity> locationListData(int start);
	public int locationTotalPage(); 
	
	public List<NatureEntity> natureListData(int start);
	public int natureTotalPage(); 
	
	public GuestEntity guestDetailData(int no); 
	public ShopEntity shopDetailData(int no); 
	public NatureEntity natureDetailData(int no); 
	public LocationEntity locationDetailData(int no); 
}
