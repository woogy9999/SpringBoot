//package com.sist.web.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.sist.web.dao.*;
//import com.sist.web.vo.*;
//
//@RestController
//public class ElasticBoardRestContoller {
//	@Autowired
//	private ElasticBoardRepository bDao;
//
//	@PostMapping("/eboard/update_ok")
//	public String board_update_ok(ElasticBoard vo) {
//
//		String result = "no";
//		ElasticBoard dbvo = bDao.findById(vo.getId());
//
//		if (dbvo.getPwd().equals(vo.getPwd())) {
//			dbvo.setName(vo.getName()); 
//			dbvo.setSubject(vo.getSubject());
//			dbvo.setContent(vo.getContent());
//			bDao.save(dbvo); // 수정 내용 저장
//			// return "redirect:/eboard/detail?id=" + vo.getId();
//			result = "<script>"
//					+"location.href=\"/eboard/detail?id="+vo.getId()+"\""
//					+ "</script>";
//					
//		}else {
//			result="<script>"
//					+ "alert(\"비밀번호가 틀립니다\");"
//					+ "history.back();"
//					+ "</script>";
//		}
//
//		return result;
//	}
//
//}
