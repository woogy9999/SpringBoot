package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.sist.web.entity.*;
import com.sist.web.service.BoardService;
import com.sist.web.dao.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
// => Restful => GET(읽기) SELECT , POST(쓰기) INSERT ,PUT(수정) UPDATE , DELETE(삭제)

@CrossOrigin(origins = "*")
public class BoardRestController {
	
	@Autowired
	private BoardService bService;

	@GetMapping("/board/list_react/{page}")
	public Map board_list(@PathVariable("page") int page) {
		Map map = new HashMap();
		int rowSize = 10;

		int start = (page - 1) * rowSize;
		List<BoardEntity> list = bService.boardListData(start);
		for (BoardEntity vo : list) {
			String day = vo.getRegdate();
			day = day.substring(0, day.indexOf(" "));
			vo.setRegdate(day); 
		}
		int count = (int) bService.count();
		int totalpage = (int) (Math.ceil(count / (double) rowSize));
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		map.put("today", today);
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		return map;

	}

	@PostMapping("/board/insert_react")
	public Map board_insert(@RequestBody BoardEntity vo) {
		Map map = new HashMap();
		try {

			vo.setHit(0);
			BoardEntity _vo = bService.saveBoard(vo);
			map.put("vo", _vo);
			map.put("msg", "yes");
		} catch (Exception e) {
			// TODO: handle exception
			map.put("msg", e.getMessage());
		}
		return map;
	}

	@GetMapping("/board/detail_react/{no}")
	public BoardEntity board_detail(@PathVariable("no") int no) {
		BoardEntity vo = bService.BoardData(no);
		vo.setHit(vo.getHit() + 1);
		bService.saveBoard(vo);
		vo = bService.BoardData(no);

		return vo;
	}

	@DeleteMapping("/board/delete_react/{no}/{pwd}")
	public Map board_delete(@PathVariable("no") int no, @PathVariable("pwd") String pwd) {
		Map map = new HashMap();
		BoardEntity vo=bService.BoardData(no);
		if(pwd.equals(vo.getPwd())) 
		{
			bService.deleteBoard(vo);
			map.put("msg", "yes");
		}else {
			map.put("msg", "no");
		}
		return map;

	}
	
	@GetMapping("/board/update_react/{no}")
	public BoardEntity board_update(@PathVariable("no") int no) {
		BoardEntity vo=bService.BoardData(no);
		
		return vo;
	}
	
	@PutMapping("/board/update_react_ok/{no}")
	public Map board_update_ok(@PathVariable("no") int no, @RequestBody BoardEntity data) {
	    Map map = new HashMap();
	    BoardEntity vo = bService.BoardData(no);
	    try {
	        vo.setName(data.getName());
	        vo.setSubject(data.getSubject());
	        vo.setContent(data.getContent());

	        if(data.getPwd().equals(vo.getPwd())) {
	        	bService.saveBoard(vo);
	            map.put("msg", "yes");
	        } else { 
	            map.put("msg", "no");
	        }
	    } catch (Exception e) { 
	        map.put("msg", e.getMessage());
	    }
 
	    return map;
	}
}
