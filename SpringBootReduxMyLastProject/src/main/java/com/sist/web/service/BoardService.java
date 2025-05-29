package com.sist.web.service;

import java.util.List;


import com.sist.web.entity.BoardEntity;

public interface BoardService {
	public List<BoardEntity> boardListData(int start);
	
	public BoardEntity BoardData(int no);
	
	public long count();
	public BoardEntity saveBoard(BoardEntity vo);
	public void deleteBoard(BoardEntity vo);
}
