package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.GoodsEntity;
import com.sist.web.service.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class GoodsRestController {
	@Autowired
	private GoodsService gService;
	
	
	@GetMapping("/goods/list/{page}")
	public ResponseEntity<Map> goods_list(@PathVariable("page") int page) {
		Map map=new HashMap();
		try {
			map=gService.goodsListData(page);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	
	@GetMapping("/goods/detail/{no}")
	public ResponseEntity<GoodsEntity> recipe_detail(@PathVariable("no") int no)
	{
		GoodsEntity vo=new GoodsEntity();
		try {
			
			vo=gService.goodsDetailData(no);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return new ResponseEntity<>(vo,HttpStatus.OK);
	}
}
