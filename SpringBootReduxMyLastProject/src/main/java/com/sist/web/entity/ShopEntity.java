package com.sist.web.entity;
/*
 * NO int 
TITLE text 
POSTER text 
MSG text 
ADDRESS text 
HIT int 
LIKECOUNT int 
REPLYCOUNT int
 */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "shop")
@Data
public class ShopEntity {
	@Id
	private int no;
	private int hit,likecount,replycount;
	private String title,poster,msg,address; 
}
