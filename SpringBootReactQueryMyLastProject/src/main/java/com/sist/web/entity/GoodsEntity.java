package com.sist.web.entity;
/*
 * 
NO                NOT NULL NUMBER         
GOODS_NAME        NOT NULL VARCHAR2(1000) 
GOODS_SUB                  VARCHAR2(1000) 
GOODS_PRICE       NOT NULL VARCHAR2(50)   
GOODS_DISCOUNT             NUMBER         
GOODS_FIRST_PRICE          VARCHAR2(20)   
GOODS_DELIVERY    NOT NULL VARCHAR2(20)   
GOODS_POSTER               VARCHAR2(260)  
HIT                        NUMBER         
LIKECOUNT                  NUMBER         
REPLYCOUNT                 NUMBER         

 */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity(name = "goods_all")
@Data
public class GoodsEntity {
	@Id
	private int no;
	private int goods_discount, hit, likecount, replycount;
	private String goods_name, goods_sub, goods_price, goods_first_price, goods_delivery, goods_poster;

	@Transient
	private int num;
}
