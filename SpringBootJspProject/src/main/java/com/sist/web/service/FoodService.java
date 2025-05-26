package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.web.vo.FoodVO;

public interface FoodService {

	public List<FoodVO> foodListData(Map map);

	public int foodTotalPage();

	public FoodVO foodDetailData(int fno);
}
