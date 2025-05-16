package com.sist.web.vo;


import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.Id;
import lombok.Data;

@Document(indexName = "busanfood")
/*
id int 
NAME text 
TYPE text 
PHONE text 
ADDRESS text 
SCORE double 
THEME text 
POSTER text 
IMAGES text 
TIME text 
PARKING text 
CONTENT text 
HIT int 
PRICE text 
JJIMCOUNT int 
LIKECOUNT int 
REPLYCOUNT int
*/

@Data
public class EBusanFood { 
	@Id
    private int id;
	private String name, type, phone, address, theme, poster, images, time, parking, content, price;
	private int hit, jjimCount, likeCount, replyCount;
	private double score;
	
	
}
