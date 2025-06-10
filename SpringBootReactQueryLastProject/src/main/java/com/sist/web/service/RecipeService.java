package com.sist.web.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.RecipeDetailEntity;
import com.sist.web.entity.RecipeEntity;
import com.sist.web.vo.RecipeVO;
import java.util.*;
 
public interface RecipeService {
	public List<RecipeEntity> recipeMainData();
	public Map recipeListData(int page);
	public Map recipeDetailData(int no);
}
