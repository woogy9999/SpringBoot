package com.sist.web.entity;
/*
SNO         NOT NULL NUMBER         
TITLE      NOT NULL VARCHAR2(200)  
POSTER     NOT NULL VARCHAR2(300)  
MSG        NOT NULL VARCHAR2(4000) 
ADDRESS    NOT NULL VARCHAR2(300)  
HIT                 NUMBER         
LIKECOUNT           NUMBER         
REPLYCOUNT          NUMBER 

 */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "seoul_nature")
@Data
public class SeoulEntity {
	@Id
	private int sno;
	private int hit,likecount,replycount;
	private String title,poster,msg,address;
}
