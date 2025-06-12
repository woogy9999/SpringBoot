package com.sist.web.dao;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.CommentEntity;
import com.sist.web.vo.*;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
	@Query(value="SELECT no,fno,id,name,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday "
			+ "FROM reactcomment "
			+ "WHERE fno=:fno ORDER BY no ASC",nativeQuery = true)
	public List<CommentVO> commentListData(@Param("fno") int fno);

	@Query(value = "SELECT NVL(MAX(no)+1,1) FROM reactcomment",nativeQuery = true)
	public int MaxNo();
	
	public CommentEntity findByNo(int no);
}
