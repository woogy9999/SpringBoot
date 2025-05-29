package com.sist.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.BoardRepository;
import com.sist.web.entity.BoardEntity;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardRepository bDao;

	@Override
	public List<BoardEntity> boardListData(int start) {
		// TODO Auto-generated method stub
		return bDao.boardListData(start);
	}

	@Override
	public BoardEntity BoardData(int no) {
		// TODO Auto-generated method stub
		return bDao.findByNo(no);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return bDao.count();
	}

	@Override
	public BoardEntity saveBoard(BoardEntity vo) {
		// TODO Auto-generated method stub
		 return bDao.save(vo);
	}

	@Override
	public void deleteBoard(BoardEntity vo) {
		// TODO Auto-generated method stub
		bDao.delete(vo);
	}
	
	

}
