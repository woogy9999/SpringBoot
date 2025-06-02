package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
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
@Entity(name = "location")
@Data
public class LocationEntity {

	@Id
	private int no;
	private String title,poster,msg,address;
	private int hit,likecount,replycount;
}
