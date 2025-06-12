package com.sist.web.service;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.sist.web.entity.*;
import com.sist.web.vo.CommentVO;

public interface CommentService {
	public int idCount(String id);
	public MemberEntity memberDetailData(String id);
	
	public List<CommentVO> commentListData(int sno);
	
	public List<CommentVO> commentInsert(CommentEntity vo);
	
	public List<CommentVO> commentDelete(int no,int sno);
	
	 public List<CommentVO> commentUpdate(int no, String msg, int sno);
}
