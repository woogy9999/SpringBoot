package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import com.sist.web.service.*;
import com.sist.web.entity.*;

// const {isLoding,error,data} = useQuery()
@RestController
@CrossOrigin(origins = "*")
public class RecipeRestController {

	@Autowired
	private RecipeService rService;

	@GetMapping("/recipe/list/{page}")
	public ResponseEntity<Map> recipe_list(@PathVariable("page") int page)

	{
		Map map = new HashMap();
		try {
			int rowSize = 12;
			int start = (page - 1) * rowSize + 1;
			int end = page * rowSize;
			List<RecipeEntity> list = rService.recipeListData(start, end);
			int totalpage = rService.recipeTotalPage();
			final int BLOCK = 10;
			int startPage = ((page - 1) / BLOCK * BLOCK) + 1;
			int endPage = ((page - 1) / BLOCK * BLOCK) + BLOCK;
			if (endPage > totalpage)
				endPage = totalpage;

			map.put("list", list);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);

		} catch (Exception e) {
			// TODO: handle exception

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK); // 정상 수행
	}

	@GetMapping("/recipe/detail/{no}")
	public ResponseEntity<Map> recipe_detail(@PathVariable("no") int no) {

		try {
			Map map = rService.recipeDetailData(no);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/recipe/find/{page}/{title}")
	public ResponseEntity<Map> recipe_find(@PathVariable("page") int page, @PathVariable("title") String title) {
		Map map = new HashMap();
		try {
			map=rService.recipeFindData(page, title);
		
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);

	}

}
