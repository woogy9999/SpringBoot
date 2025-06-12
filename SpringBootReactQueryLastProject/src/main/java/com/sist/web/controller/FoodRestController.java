package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.FoodEntity;
import com.sist.web.service.*;
import java.util.*;
import com.sist.web.vo.*;

@RestController
@CrossOrigin(origins = "*")
public class FoodRestController {
	@Autowired
	private FoodService fService;
	
	@Autowired
	private CommentService cService;
	
	@GetMapping("/food/list/{page}")
	public ResponseEntity<Map> recipe_list(@PathVariable("page") int page) {
		Map map=new HashMap();
		try {
			map=fService.foodListData(page);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	
	@GetMapping("/food/detail/{fno}")
	public ResponseEntity<Map> food_detail(@PathVariable("fno") int fno)
	{
		Map map=new HashMap();
		FoodEntity vo=new FoodEntity();
		try {
			vo=fService.foodDetailData(fno);
			List<CommentVO> list=cService.commentListData(fno);
			map.put("foods", vo);
			map.put("comments", list);
		} catch (Exception e) {
			// TODO: handle exception   
			e.printStackTrace(); 
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
