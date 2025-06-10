package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.RecipeEntity;
import com.sist.web.vo.*;
import java.util.*;
@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer>{
  @Query(value="SELECT no,title,poster,chef,hit,likecount,link, replycount,num "
		+"FROM (SELECT no,title,poster,chef,hit,likecount,link, replycount,rownum as num "
		+"FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk)*/no,title,poster,chef,hit,likecount,link, replycount "
		+"FROM recipe WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipedetail))) "
		+"WHERE num BETWEEN :start AND :end",nativeQuery = true)
  public List<RecipeEntity> recipeListData(@Param("start") Integer start,@Param("end") Integer end);
  
  @Query(value="SELECT COUNT(*) FROM recipe "
		+"WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipedetail)",nativeQuery = true)
  public int recipeCount();
  
  @Query(value="SELECT no,title,poster,chef,hit,likecount,link, replycount,num  "
			+ "FROM (SELECT no,title,poster,chef,hit,likecount,link, replycount,rownum AS num "
			+ "FROM (SELECT no,title,poster,chef,hit,likecount,link, replycount "
			+ "FROM recipe ORDER BY no ASC))  "
			+ "WHERE num<=9", nativeQuery = true) 
  public List<RecipeEntity> recipeMainData();
  
  
}