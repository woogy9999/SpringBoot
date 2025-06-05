package com.sist.web.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

/*
 * no int AI PK 
name text 
subject text 
content text 
pwd text 
regdate datetime 
hit int
 */
@Entity(name = "board")
@Data
public class BoardEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq_gen")
	@SequenceGenerator(name = "board_seq_gen", sequenceName = "board_seq", allocationSize = 1)
	private int no;
	private String name; 
	private String  subject;
	private String content;
	@Column(insertable =true, updatable =false)
	private String pwd;
	@Column(insertable =true, updatable =false)
	private String regdate;
	private int hit;
	
	@PrePersist
	public void regdate() {
		this.regdate=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
	}
}
