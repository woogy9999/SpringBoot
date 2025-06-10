package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.entity.GoodsEntity;


@Repository 
public interface GoodsRepository extends JpaRepository<GoodsEntity, Integer>{
	@Query(value = "SELECT no, goods_name, goods_sub, goods_price, goods_discount, goods_first_price, goods_delivery, goods_poster, hit, likecount, replycount, num "
	        + "FROM (SELECT no, goods_name, goods_sub, goods_price, goods_discount, goods_first_price, goods_delivery, goods_poster, hit, likecount, replycount, rownum as num "
	        + "FROM (SELECT /*+ INDEX_ASC(goods_all ga_no_pk)*/ no, goods_name, goods_sub, goods_price, goods_discount, goods_first_price, goods_delivery, goods_poster, hit, likecount, replycount "
	        + "FROM goods_all)) "
	        + "WHERE num BETWEEN :start AND :end", nativeQuery = true)
	public List<GoodsEntity> goodsListData(@Param("start") Integer start, @Param("end") Integer end);

	@Query(value = "SELECT COUNT(*) FROM goods_all", nativeQuery = true)
	public int goodsCount();

	@Query(value = "SELECT no, goods_name, goods_sub, goods_price, goods_discount, goods_first_price, goods_delivery, goods_poster, hit, likecount, replycount, num "
	        + "FROM (SELECT no, goods_name, goods_sub, goods_price, goods_discount, goods_first_price, goods_delivery, goods_poster, hit, likecount, replycount, rownum AS num "
	        + "FROM (SELECT no, goods_name, goods_sub, goods_price, goods_discount, goods_first_price, goods_delivery, goods_poster, hit, likecount, replycount "
	        + "FROM goods_all ORDER BY no ASC)) "
	        + "WHERE num <= 9", nativeQuery = true)
	public List<GoodsEntity> goodsMainData();
	
	
	
	@Query(value="SELECT no, goods_name, goods_sub, goods_price, goods_discount, goods_first_price, goods_delivery, goods_poster, hit, likecount, replycount "
			+ "FROM goods_all WHERE no=:no",nativeQuery = true)
	public GoodsEntity goodsDetailData(@Param("no") int no);


}
