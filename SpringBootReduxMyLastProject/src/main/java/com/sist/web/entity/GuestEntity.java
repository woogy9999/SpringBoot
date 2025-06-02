package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
/*
 * NO int 
TITLE text 
POSTER text 
ADDRESS text
 */
@Entity(name = "guest")
@Data
public class GuestEntity {

	@Id
	private int no;
	private String title,poster,address;

}
