package com.sist.web.service;


import com.sist.web.vo.*;
import com.sist.web.dao.*;
import com.sist.web.entity.RecipeDetailEntity;
import com.sist.web.entity.RecipeEntity;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
// Service : 1. 여러개의 DAO를 통합 => BI , 2. DAO에서 받은 데이터를 변경 , 추가 => Controller

@Service
public class RecipeServiceImpl implements RecipeService{
	@Autowired
	private RecipeRepository rDao;
	
	@Autowired
	private RecipeDetailRepository dDao;

	public List<RecipeEntity> recipeMainData(){
		return rDao.recipeMainData();
	}

	@Override
	public Map recipeListData(int page) {
		// TODO Auto-generated method stub
		Map map =new HashMap();
		int rowSize=12;
		int start=(rowSize*page)-(rowSize-1);
		int end=(rowSize*page);
		List<RecipeEntity> list=rDao.recipeListData(start, end);
		int count=rDao.recipeCount();
				
		int totalpage=(int)(Math.ceil(count/12.0));
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage) 
			endPage=totalpage;
		 
		map.put("list", list);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		return map;
	}

	@Override
	public Map recipeDetailData(int no) {
		// TODO Auto-generated method stub
		Map map=new HashMap();
		RecipeDetailEntity vo=dDao.recipeDetailData(no);
		String[] foodmake=vo.getFoodmake().split("\\\\n");
		List<String> mList=new ArrayList<String>();
		List<String> iList=new ArrayList<String>();
		
		for(String fm:foodmake)
		{
			StringTokenizer st=new StringTokenizer(fm,"^");
			mList.add(st.nextToken());
			iList.add(st.nextToken());
		}
		
		String[] datas=vo.getData().split(",");
		List<String> dList=Arrays.asList(datas);
		
		map.put("vo", vo);
		map.put("mList", mList);
		map.put("iList", iList);
		map.put("dList", dList);
		
		return map;
	}

}
