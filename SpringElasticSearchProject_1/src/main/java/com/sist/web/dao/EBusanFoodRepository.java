package com.sist.web.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.sist.web.vo.EBusanFood;
import java.util.*;
/*
 *    JPA 형식 => 메소드 제작 
 *    select distinct ... where lastname and firstname 
 *    
 *    findDistinctByLastnameAndFirstName()
 *    
 *    where no between .. and ...
 *    
 *    findByNoBetween(int a,int b)
 *    
 *    where age<?
 *    findByAgeLassThen(int age)
 *             ---- GreaterThen 
 *             ---- GreaterThenEqual
 *             
 *     가격(price) 1000보다 작고 가격 => 내림차순
 *     findByPriceLessThenOrderByPriceDESC()  
 *     WHERE Order BY a DESC 
 *     
 *    @Query({hits:hits:{_source:{type:'*0?*'}})
 *      1? 2? 3?
 *    
 */
public interface EBusanFoodRepository extends ElasticsearchRepository<EBusanFood, Integer>{
   public List<EBusanFood> findByTypeContaining(String type);
   // 메소드화 => 조건이 있는 경우 / Order By , Group by ....
   // JOIN => 메소드(X)
   public EBusanFood findById(String id); // findByName(String name) name=?
   // WHERE id=?
}