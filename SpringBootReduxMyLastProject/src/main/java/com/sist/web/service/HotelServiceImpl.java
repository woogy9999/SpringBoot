package com.sist.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.HotelRepository;
import com.sist.web.entity.HotelEntity;

@Service
public class HotelServiceImpl implements HotelService{

	
	@Autowired
	private HotelRepository hDao;
	
	@Override
	public List<HotelEntity> hotelMainData() {
		// TODO Auto-generated method stub
		return hDao.hotelMainData();
	}

	@Override
	public List<HotelEntity> hotelListData(int start) {
		// TODO Auto-generated method stub
		return hDao.hotelListData(start);
	}

	@Override
	public int hotelTotalPage() {
		// TODO Auto-generated method stub
		return hDao.hotelTotalPage();
	}

	@Override
	public HotelEntity hotelDetailData(int no) {
		// TODO Auto-generated method stub
		return hDao.findByNo(no);
		
	}

}
