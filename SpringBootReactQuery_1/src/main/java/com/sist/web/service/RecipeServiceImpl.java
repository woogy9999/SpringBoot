package com.sist.web.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.RecipeDetailRepository;
import com.sist.web.dao.RecipeRepository;
import com.sist.web.entity.RecipeDetailEntity;
import com.sist.web.entity.RecipeEntity;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository rDao;

	@Autowired
	private RecipeDetailRepository rdDao;

	@Override
	public List<RecipeEntity> recipeListData(int start, int end) {
		// TODO Auto-generated method stub
		return rDao.recipeListData(start, end);
	}

	@Override
	public int recipeTotalPage() {
		// TODO Auto-generated method stub
		return rDao.recipeTotalPage();
	}

	@Override
	public Map recipeDetailData(int no) {
		// TODO Auto-generated method stub

		Map map = new HashMap();

		RecipeDetailEntity vo = rdDao.findByNo(no);
		String[] datas = vo.getFoodmake().split("\\\\n");
		List<String> mList = new ArrayList<String>();
		List<String> iList = new ArrayList<String>();

		for (String d : datas) {
			StringTokenizer st = new StringTokenizer(d, "^");
			mList.add(st.nextToken());
			iList.add(st.nextToken());

		}
		String s = vo.getData();
		s = s.replaceAll("[구매]", "");
		vo.setData(s);
		String[] arr = s.split(",");
		List<String> dList = Arrays.asList(arr);

		map.put("vo", vo);
		map.put("mList", mList);
		map.put("iList", iList);
		map.put("dList", dList);

		return map;
	}

	@Override
	public Map recipeFindData(int page, String title) {
		// TODO Auto-generated method stub
		Map map = new HashMap();

		int rowSize = 12;
		int start = (page - 1) * rowSize + 1;
		int end = page * rowSize;
		List<RecipeEntity> list = rDao.recipeFindData(start, end,title);
		int totalpage = rDao.recipeFindTotalPage(title);
		final int BLOCK = 10;
		int startPage = ((page - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((page - 1) / BLOCK * BLOCK) + BLOCK;
		if (endPage > totalpage)
			endPage = totalpage;

		map.put("rlist", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		return map;
	}

}
