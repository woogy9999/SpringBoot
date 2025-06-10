package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.GoodsEntity;
import com.sist.web.service.*;
import java.util.*;
@RestController
@CrossOrigin(origins = "*")
public class MainRestController {
   @Autowired
   private GoodsService gService;
   
   
   @GetMapping("/main")
   public ResponseEntity<Map> main_data()
   {
	   Map map=new HashMap();
	   try
	   { 
		   List<GoodsEntity> gList=gService.goodsMainData();
		   
		   GoodsEntity main=gList.get(0);
		   List<GoodsEntity> list1=new ArrayList<GoodsEntity>();
		   for(int i=1;i<=4;i++)
		   {
			   list1.add(gList.get(i));
		   }
		   
		   
		  
		   map.put("main", main);
		   map.put("list1",list1);
		   map.put("gList", gList);
	   }catch(Exception ex)
	   {
		   return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	   return new ResponseEntity<>(map,HttpStatus.OK);
   }
}