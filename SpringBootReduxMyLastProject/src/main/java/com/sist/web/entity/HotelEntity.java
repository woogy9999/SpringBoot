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

@Entity(name="seoul_hotel")
@Data
public class HotelEntity {
	@Id
	private int no;
	private String title,poster,address;
}
