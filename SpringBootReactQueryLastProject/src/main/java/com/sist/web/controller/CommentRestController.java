package com.sist.web.controller;

import com.sist.web.entity.*;
import com.sist.web.service.*;
import com.sist.web.vo.CommentVO;

import java.util.*;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class CommentRestController {
	@Autowired
	private CommentService cService;
	
	@GetMapping("/member/login/{id}/{pwd}")
	public ResponseEntity<Map> memberLogin(@PathVariable("id") String id,@PathVariable("pwd") String pwd)
	{
		Map map=new HashMap();
		try { 
			// id존재 여부 확인
			int count=cService.idCount(id);
			System.out.println("카운트"+count);
			if(count==0) // id가 없는 경우
			{
				map.put("msg", "NOID");
			}
			else // id가 있는 경우
			{
				MemberEntity vo=cService.memberDetailData(id);
				System.out.println(vo.getAddress()+"주소");
				System.out.println(vo.getEmail()+"이메일");
				System.out.println(vo.getName()+"이름");
				if(pwd.equals(vo.getPwd()))
				{
					map.put("msg", "OK");
					map.put("name", vo.getName());
					map.put("id", vo.getId());
				}
				else 
				{
					map.put("msg", "NOPWD");
					
				}				 
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	// 댓글 작업
	// => 글쓰기 / 수정하기 / 삭제하기 / 목록
	
	@PostMapping("/comment/insert")
	public ResponseEntity<Map> comment_insert(@RequestBody CommentEntity vo){
		Map map=new HashMap();
		try {
			
			List<CommentVO> list=cService.commentInsert(vo);
			map.put("comments", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
		
	} 
	
	@DeleteMapping("/comment/delete/{no}/{fno}")
	public ResponseEntity<Map> comment_delete(@PathVariable("no") int no,@PathVariable("fno") int fno)
	{
		Map map=new HashMap();
		try {
			
			List<CommentVO> list=cService.commentDelete(no, fno);
			map.put("comments", list); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	@PutMapping("/comment/update")
	public ResponseEntity<Map> comment_update(@RequestBody CommentEntity vo) {
	    Map map = new HashMap();
	    try {
	        int no = vo.getNo();
	        String msg = vo.getMsg();
	        int fno = vo.getFno();

	        List<CommentVO> list = cService.commentUpdate(no, msg, fno);
	        map.put("comments", list);
	        return new ResponseEntity<>(map, HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
}
