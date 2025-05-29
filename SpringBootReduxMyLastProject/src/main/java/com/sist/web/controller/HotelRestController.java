package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.HotelService;

import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;


@RestController
@CrossOrigin(origins = "*")
public class HotelRestController {
	
	@Autowired
	private HotelService hService;
	
	@GetMapping("/main_react")
	public Map main_data() {
		
		Map map=new HashMap();
		List<HotelEntity> list=hService.hotelMainData();
		
		map.put("list", list);
		return map;
	}
	
	@GetMapping("/hotel/list")
	public Map food_list(@RequestParam("page") int page)
	{
		Map map=new HashMap();
		int rowSize=12;
		int start=(page-1)*rowSize;
		List<HotelEntity> list=hService.hotelListData(start);
		int totalpage=hService.hotelTotalPage();
		final int BLOCK=10;
		int startPage = ((page - 1) / BLOCK * BLOCK) + 1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage); 
		map.put("endPage", endPage);
		
		return map;
	}
	
	@GetMapping("/hotel/detail")
	public HotelEntity food_detail(@RequestParam("no") int no) {
		HotelEntity vo = hService.hotelDetailData(no);
		System.out.println(vo.getTitle()+"이름출력스");
		return vo;  
	} 
}





