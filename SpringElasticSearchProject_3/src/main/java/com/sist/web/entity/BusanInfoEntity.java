package com.sist.web.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/*
 * id int 
CNO int 
TITLE text 
POSTER text 
ADDRESS text 
PHONE text 
INFO text 
 */
@Document(indexName = "busaninfo")
@Setter
@Getter
public class BusanInfoEntity {
	@Id
	private int id;
	private int cno;
	private String title,poster,address,phone,info;
}
