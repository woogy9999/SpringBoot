package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.RecipeEntity;
import java.util.*;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer>{
	@Query(value="SELECT no, poster, chef, hit, likecount, replycount, title, link,num "
			+ "FROM (SELECT no, poster, chef, hit, likecount, replycount, title, link,rownum as num "
			+ "FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk) */no, poster, chef, hit, likecount, replycount, title, link "
			+"FROM recipe WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipeDetail))) "
			 +"WHERE num BETWEEN :start AND :end",nativeQuery = true)
	public List<RecipeEntity> recipeListData(@Param("start") Integer start ,@Param("end") Integer end );
	
	public RecipeEntity findByNo(int no);
	
	@Query(value="SELECT CEIL(COUNT(*)/12.0) "
			+ "FROM recipe WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipeDetail )",nativeQuery = true)
	public int recipeTotalPage(); 
	
	
	@Query(value="SELECT no, poster, chef, hit, likecount, replycount, title, link, num "
	        + "FROM (SELECT no, poster, chef, hit, likecount, replycount, title, link, rownum AS num "
	        + "FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk) */ no, poster, chef, hit, likecount, replycount, title, link "
	        + "FROM recipe WHERE no IN (SELECT no FROM recipe INTERSECT SELECT no FROM recipeDetail) "
	        + "AND title LIKE '%' || :title || '%' )) "
	        + "WHERE num BETWEEN :start AND :end", nativeQuery = true)
	public List<RecipeEntity> recipeFindData(@Param("start") Integer start,@Param("end") Integer end,
			@Param("title") String title);// Find
	
	@Query(value="SELECT CEIL(COUNT(*)/12.0) "
			+ "FROM recipe WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipeDetail ) "
			+ "AND title LIKE '%' || :title || '%'  ",nativeQuery = true)
	public int recipeFindTotalPage(@Param("title") String title); 
	
	// Chef
	// Chef의 recipe목록
	// CRUD
}
