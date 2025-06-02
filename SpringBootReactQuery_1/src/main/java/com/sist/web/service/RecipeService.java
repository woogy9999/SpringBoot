package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.RecipeDetailEntity;
import com.sist.web.entity.RecipeEntity;

public interface RecipeService {

	public List<RecipeEntity> recipeListData(int start, int end);
	public int recipeTotalPage();
	
	public Map recipeDetailData(int no);
	
	public Map recipeFindData(int page, String title);

	
}
