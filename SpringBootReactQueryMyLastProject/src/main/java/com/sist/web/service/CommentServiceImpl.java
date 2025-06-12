package com.sist.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.CommentRepository;
import com.sist.web.dao.MemberRepository;
import com.sist.web.entity.CommentEntity;
import com.sist.web.entity.MemberEntity;
import com.sist.web.vo.CommentVO;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private MemberRepository mDao;
	

	@Autowired
	private CommentRepository cDao;
	
	@Override
	public int idCount(String id) {
		// TODO Auto-generated method stub
		return mDao.idCount(id);
	}

	@Override
	public MemberEntity memberDetailData(String id) {
		// TODO Auto-generated method stub
		return mDao.memberDetailData(id);
	}

	@Override
	public List<CommentVO> commentListData(int sno) {
		// TODO Auto-generated method stub
		return commentCommonsData(sno);
	}
	
	public List<CommentVO> commentCommonsData(int sno) {
		// TODO Auto-generated method stub
		return cDao.commentListData(sno);
	}

	@Override
	public List<CommentVO> commentInsert(CommentEntity vo) { 
		// TODO Auto-generated method stub
		int no=cDao.MaxNo();
		vo.setNo(no);
		vo.setRegdate(new Date());
		cDao.save(vo);
		return commentCommonsData(vo.getSno());
	}

	@Override
	public List<CommentVO> commentDelete(int no,int sno) {
		// TODO Auto-generated method stub
		CommentEntity vo=cDao.findByNo(no);
		cDao.delete(vo);
		return commentCommonsData(sno);
	}
	
    // 댓글 수정
	@Override
    public List<CommentVO> commentUpdate(int no, String msg, int sno) {
        CommentEntity vo = cDao.findByNo(no);
        vo.setMsg(msg);
        cDao.save(vo); // 수정된 내용 저장

        // 수정 후 댓글 목록 다시 반환
        return commentCommonsData(sno);
    }
}
