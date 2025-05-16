package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.vo.*;

/*
		
		LogStash : 데이터 수집 ( DB, CSV ) => 집계 , 파싱 => ElasticSearch
		Kibna : 빠른 검색 => 데이터를 시각화 모니터링
		
		------------------------------------------
		
		1. primary key : _id(자동 생성)
		2. 스키마 : mapping
		3. Relation : Parent / Child
		4. SQL : Query DSL
		
		@Query("SELECT ~~~") : @Query("{'mapping'{'address':*0?*}}")
							=> ElasticSearch / MongoDB => NoSQL => JSON
		1) 검색엔진 => 루씬(구글) => 라이센스 (아파치)
		2) 대량용 데이터를 저장
		3) Kafka => 실시간으로 값을 받는 경우 => 누수현상
		4) 리눅스환경에서 주로 사용 => AWS
		=> 필수 => Option
		
		=> 페이징 / 검색 => LIKE
 */
@Controller
public class ElasticBoardController {
	@Autowired
	private ElasticBoardRepository bDao;

	@GetMapping("/eboard/list")
	public String eboard_list(@RequestParam(value = "page", required = false) String page, Model model) {
		if (page == null)
			page = "1";
		int curpage = Integer.parseInt(page);
		int rowSize = 10;
		Pageable pg = PageRequest.of(curpage - 1, rowSize, Sort.by(Sort.Direction.DESC, "id"));
		// 전체 데이터 읽기 => findAll => SELECT ~~
		Page<ElasticBoard> pList = bDao.findAll(pg);

		// Page => List로 변환
		List<ElasticBoard> list = new ArrayList<ElasticBoard>();
		if (pList != null && pList.hasContent()) {
			list = pList.getContent();
		}

		for (ElasticBoard eb : list) {
			String s = eb.getRegdate();
			int a = s.indexOf("T");
			if (a > 0) {

				s = s.substring(0, s.indexOf("T"));
				eb.setRegdate(s);
			}

		}
		/*
		 * ElasticBoard eb=list.get(0); String s=eb.getRegdate();
		 * s=s.substring(0,s.indexOf("T")); list.get(0).setRegdate(s);
		 */
		// 총페이지 구하기
		int count = (int) bDao.count();
		int totalpage = (int) (Math.ceil(count / 10.0));

		// HTML로 전송

		/*
		 * findAll() : SELECT * FROM board findById(int id) : SELECT * FROM board WHERE
		 * id=? count() : SELECT COUNT(*) FROM board save() : insert , update delete() :
		 * delete
		 */
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);

		return "eboard/list";
	}

	@GetMapping("/eboard/insert")
	public String eboard_insert() {

		return "eboard/insert";
	}

	@PostMapping("/eboard/insert_ok")
	public String eboard_insert_ok(ElasticBoard vo) {
		vo.setHit(0);
		vo.setId(idMaxData());
		vo.setRegdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		bDao.save(vo);
		return "redirect:/eboard/list";
	}
	// => SEQUENCE

	public int idMaxData() {
		int max = 0;
		try {
			int rowSize = 10;
			int start = 0;
			Pageable pg = PageRequest.of(start, rowSize, Sort.by(Sort.Direction.DESC, "id"));
			// 전체 데이터 읽기 => findAll => SELECT ~~
			Page<ElasticBoard> pList = bDao.findAll(pg);

			// Page => List로 변환
			List<ElasticBoard> list = new ArrayList<ElasticBoard>();
			if (pList != null && pList.hasContent()) {
				list = pList.getContent();
				max = list.get(0).getId();
			}

		} catch (Exception e) {
			max = 0;
		}
		return max + 1;
	}

	// default => mysal => 설계 => 키바나
	@GetMapping("/eboard/detail")
	public String eboard_detail(@RequestParam("id") int id, Model model) {

		ElasticBoard vo = bDao.findById(id);
		vo.setHit(vo.getHit() + 1);
		bDao.save(vo); // update => vo(이미 id존재할떄는 update)
		vo = bDao.findById(id);
		model.addAttribute("vo", vo);
		return "eboard/detail";
	}

	@GetMapping("/eboard/update")
	public String board_update(@RequestParam("id") int id, Model model) {

		ElasticBoard vo = bDao.findById(id);
		model.addAttribute("vo", vo);

		return "eboard/update";
	}

	@PostMapping("/eboard/update_ok")
	public String board_update_ok(ElasticBoard vo, Model model) {

		String result = "no";
		ElasticBoard dbvo = bDao.findById(vo.getId());

		if (dbvo.getPwd().equals(vo.getPwd())) {
			dbvo.setName(vo.getName());
			dbvo.setSubject(vo.getSubject());
			dbvo.setContent(vo.getContent());
			bDao.save(dbvo); // 수정 내용 저장
			return "redirect:/eboard/detail?id=" + vo.getId();

		} else {
			model.addAttribute("vo", vo);
			model.addAttribute("error", "비밀번호가 일치하지 않습니다");
			return "eboard/update";
		}
	}
 
	@GetMapping("/eboard/delete")
	public String eboard_delete(@RequestParam("id") int id, Model model) {
		model.addAttribute("id", id);
		return "eboard/delete";
	}

	@PostMapping("/eboard/delete_ok")
	public String eboard_delete_ok(ElasticBoard vo, Model model) {
		ElasticBoard dbvo = bDao.findById(vo.getId());

		if (dbvo.getPwd().equals(vo.getPwd())) {
			bDao.deleteById(vo.getId());
			return "redirect:/eboard/list";
		} else {
			model.addAttribute("id", vo.getId());
			model.addAttribute("error", "비밀번호가 일치하지 않습니다");
			return "eboard/delete";
		}
	}

}
