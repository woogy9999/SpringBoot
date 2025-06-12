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
	@Query(value="SELECT no,sno,id,name,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday "
			+ "FROM seoulcomment "
			+ "WHERE sno=:sno ORDER BY no ASC",nativeQuery = true)
	public List<CommentVO> commentListData(@Param("sno") int sno);

	@Query(value = "SELECT NVL(MAX(no)+1,1) FROM seoulcomment",nativeQuery = true)
	public int MaxNo();
	
	public CommentEntity findByNo(int no);
}
 