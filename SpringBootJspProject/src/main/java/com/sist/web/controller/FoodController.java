package com.sist.web.controller;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.FoodService;
import com.sist.web.vo.FoodVO;

import java.util.*;
// => Maven / Gradle => 프로젝트 관리 , 라이브러리 관리 , 버전 관리 , 배포 관리
// => 원격 : git / svn => 형상관리
// => 브런치가 뭔지
// => 협업
// => 개발과정 / 리더십 
// 일관성.

/*
 * 	1. 웹 흐름 , 데이터베이스 연동 : CRUD , 동적페이지
 *  2. 스프링 / MVC / SQL고급
 *  3. 최신 기술
 *  	=> CI/CD
 *  과정중시 => 산출물 , 협업 ( 문제점 / 해결책)
 */

@Controller
public class FoodController {
	@Autowired
	private FoodService fService;
	
    @GetMapping("/")
    public String main() {
        return "list"; // /WEB-INF/jsp/list.jsp 로 이동
    }
    
	@GetMapping("/list")
	public String food_list(@RequestParam(name="page",required = false) String page, Model model)
	{	
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		map.put("start",(curpage*12)-11);
		map.put("end",(curpage*12)); 
		  
		List<FoodVO> list=fService.foodListData(map);
		int totalpage=fService.foodTotalPage();
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		
		return "list";
	}
	@GetMapping("/detail")
	public String food_detail(@RequestParam("fno") int fno,Model model) {
		
		FoodVO vo=fService.foodDetailData(fno);
		model.addAttribute("vo",vo);
		return "detail";
	}
	
}
