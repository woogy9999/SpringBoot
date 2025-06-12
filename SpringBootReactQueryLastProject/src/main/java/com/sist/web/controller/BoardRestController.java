package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.BoardRepository;
import com.sist.web.entity.*;
import com.sist.web.vo.BoardVO;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
// => Restful => GET(읽기) SELECT , POST(쓰기) INSERT ,PUT(수정) UPDATE , DELETE(삭제)

@CrossOrigin(origins = "*")
public class BoardRestController {

	@Autowired
	private BoardRepository bDao; 

	@GetMapping("/board/list/{page}")
	public ResponseEntity<Map> board_list(@PathVariable("page") int page) {
		Map map = new HashMap();
		try {

			int rowSize = 10;

			int start = (page * rowSize) - (rowSize - 1);
			int end = page * rowSize;
			List<BoardVO> list = bDao.boardListData(start, end);

			int count = (int) bDao.count();
			int totalpage = (int) (Math.ceil(count / (double) rowSize));
			String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

			map.put("today", today);
			map.put("list", list);
			map.put("curpage", page);
			map.put("totalpage", totalpage);

		} catch (Exception ex) {
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);

	}

	@PostMapping("/board/insert")
	public ResponseEntity<Map> board_insert(@RequestBody BoardEntity vo) {
		Map map = new HashMap();
		try {

			vo.setHit(0);

			BoardEntity _vo = bDao.save(vo);

			map.put("vo", _vo);
			map.put("msg", "yes");
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			map.put("msg", e.getMessage());
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/board/update_ok/{no}")
	public ResponseEntity<Map> board_update_ok(@PathVariable("no") int no, @RequestBody BoardEntity data) {
		Map map = new HashMap();
		BoardEntity vo = bDao.findByNo(no);
		try {
			// 수정할 데이터 덮어쓰기
			vo.setName(data.getName());
			vo.setSubject(data.getSubject());
			vo.setContent(data.getContent());

			// 비밀번호 확인
			if (data.getPwd().equals(vo.getPwd())) {
				bDao.save(vo);
				map.put("msg", "yes");
			} else {
				map.put("msg", "no");
			}
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			map.put("msg", e.getMessage());
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/board/update/{no}")
	public BoardEntity board_update(@PathVariable("no") int no) {
		BoardEntity vo = bDao.findByNo(no);

		return vo;
	}

	@GetMapping("/board/detail/{no}")
	public ResponseEntity<BoardEntity> board_detail(@PathVariable("no") int no,
			@RequestParam(name = "increase", defaultValue = "true") boolean increase) {

		BoardEntity vo = null;
		try {

			vo = bDao.findByNo(no);
			if (increase) {
				vo.setHit(vo.getHit() + 1);
				// System.out.println("regdate 확인: " + vo.getRegdate());
				bDao.save(vo);
			}
			// 날짜 형식
			String dbday = vo.getRegdate().substring(0, 10);
			vo.setRegdate(dbday);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}

	@DeleteMapping("/board/delete/{no}/{pwd}")
	public ResponseEntity<Map> board_delete(@PathVariable("no") int no, @PathVariable("pwd") String pwd) {
		Map map = new HashMap();
		try {

			BoardEntity vo = bDao.findByNo(no);
			if (pwd.equals(vo.getPwd())) {
				bDao.delete(vo);
				map.put("msg", "yes");
			} else {
				map.put("msg", "no");
			}
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			map.put("msg", e.getMessage());
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	

	

}
