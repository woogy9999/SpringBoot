package com.sist.web.entity;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
 * no int 
CNO int 
TITLE text 
POSTER text 
ADDRESS text 
PHONE text 
INFO text
 */
@Data
@Entity(name="busan_info")
public class BusanInfoEntity {
	@Id
	private int no;
	private int cno;
	private String title,address,phone,info,poster;
}
