package com.sist.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.GuestRepository;
import com.sist.web.dao.HotelRepository;
import com.sist.web.dao.LocationRepository;
import com.sist.web.dao.NatureRepository;
import com.sist.web.dao.ShopRepository;
import com.sist.web.entity.GuestEntity;
import com.sist.web.entity.HotelEntity;
import com.sist.web.entity.LocationEntity;
import com.sist.web.entity.NatureEntity;
import com.sist.web.entity.ShopEntity;

@Service
public class HotelServiceImpl implements HotelService{

	
	@Autowired
	private HotelRepository hDao;
	
	@Autowired
	private ShopRepository sDao;

	@Autowired
	private GuestRepository gDao;

	@Autowired
	private LocationRepository lDao;

	@Autowired
	private NatureRepository nDao;
	
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

	@Override
	public List<ShopEntity> shopListData(int start) {
		// TODO Auto-generated method stub
		return sDao.shopListData(start);
	}

	@Override
	public int shopTotalPage() {
		// TODO Auto-generated method stub
		return sDao.shopTotalPage();
	}

	@Override
	public List<GuestEntity> guestListData(int start) {
		// TODO Auto-generated method stub
		return gDao.guestListData(start);
	}

	@Override
	public int guestTotalPage() {
		// TODO Auto-generated method stub
		return gDao.guestTotalPage();
	}

	@Override
	public List<LocationEntity> locationListData(int start) {
		// TODO Auto-generated method stub
		return lDao.locationListData(start);
	}

	@Override
	public int locationTotalPage() {
		// TODO Auto-generated method stub
		return lDao.locationTotalPage();
	}

	@Override
	public List<NatureEntity> natureListData(int start) {
		// TODO Auto-generated method stub
		return nDao.natureListData(start);
	}

	@Override
	public int natureTotalPage() {
		// TODO Auto-generated method stub
		return nDao.natureTotalPage();
	}

	@Override
	public GuestEntity guestDetailData(int no) {
		// TODO Auto-generated method stub
		return gDao.findByNo(no);
	}

	@Override
	public ShopEntity shopDetailData(int no) {
		// TODO Auto-generated method stub
		return sDao.findByNo(no);
	}

	@Override
	public NatureEntity natureDetailData(int no) {
		// TODO Auto-generated method stub
		return nDao.findByNo(no);
	}

	@Override
	public LocationEntity locationDetailData(int no) {
		// TODO Auto-generated method stub
		return lDao.findByNo(no);
	}
 
}
