package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;
/*
 * NO
TITLE
POSTER
CHEF
LINK
HIT
LIKECOUNT
REPLYCOUNT
 */
@Entity(name = "recipe")
@Data
public class RecipeEntity {
	@Id
	private int no;
	private String title, poster, chef, link;
	private int hit, likecount, replycount;

	@Transient
	private int num;
}
 