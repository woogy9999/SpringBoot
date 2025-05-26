package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sist.web.dao.*;
import com.sist.web.vo.*;
import java.util.*;
@RestController
@CrossOrigin(origins = "*")
// port => 전화선 
public class BusanFoodRestController {
	@Autowired
	private BusanFoodRepository bDao;
	
	@GetMapping("/food/list_react")
	public Map food_list(@RequestParam("page") int page)
	{
		int rowSize=12;
		int start=(rowSize*page)-rowSize;
		List<BusanFoodVO> list=bDao.busanListData(start);
		int count=(int)bDao.count();
		
		Map map=new HashMap();
		map.put("list", list);
		map.put("curpage", page);
		map.put("count", count);
		
		return map;
		
		
	}
}
