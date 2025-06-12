package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.SeoulEntity;
import com.sist.web.service.CommentService;
import com.sist.web.service.SeoulService;
import com.sist.web.vo.CommentVO;

@RestController
@CrossOrigin(origins = "*")
public class SeoulRestController {
	@Autowired
	private SeoulService sService;
	

	@Autowired
	private CommentService cService;
	
	@GetMapping("/seoul/list/{page}")
	public ResponseEntity<Map> seoul_list(@PathVariable("page") int page) {
		Map map=new HashMap();
		try {
			map=sService.seoulListData(page);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	
	@GetMapping("/seoul/detail/{sno}")
	public ResponseEntity<Map> seoul_detail(@PathVariable("sno") int sno)
	{
		Map map=new HashMap();
		SeoulEntity vo=new SeoulEntity();
		try {
			
			vo=sService.seoulDetailData(sno);
			List<CommentVO> list=cService.commentListData(sno);

			map.put("seouls", vo);
			map.put("comments", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	

}
