package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 *   Spring-Boot : 순수하게 서버역할 => 요청 / 응답 : vue = boot , react = boot 
 *   ----------------------------------------- Back-End : 설정 파일 제외 : XML , TomCat내장
 *                                             => Spring-Framework 호환 
 *   ThymeLeaf 
 *   ----------
 *    웹에서 독립적인 실행형 환경을 위한 Java 템플릿 
 *    => jsp : 브라우저에 실행 : 텍스트 
 *    => thymeleaf => HTML 
 *    => SSR : 서버사이드 HTML 렌더링 
 *             -------- NodeJS
 *    => 목적 : 수순수하게 HTML을 최재한 유지 
 *    => 스프링에서 통합 
 *    => JSP보다 속도가 늦다 
 *       --- 오라클에서 웹에서 유료 => 대체 (JSP : ThymeLeaf , JAVA: 코틀린)
 *    => 기본 사용법 
 *       --------
 *       데이터 출력 
 *         th:text="${}" <td>[[${}]]</td>
 *         url => @{}
 *         선택 변수 => *{}
 *         메세지 표현식 => #{}
 *       연산자 ${10+20}
 *          산술 : + , - , * , / , %
 *          비교 : < , > , <= , >= , != , ==
 *          논리 : and , or ,not 
 *          삼항연산자 : (조건)?값:값 
 *       제어문
 *          th:if / th:else , th:each
 *          => EL + JSTL 
 *          => 장점 : HTML안에 출력  
 *       
 */
import com.sist.web.dao.*;
import com.sist.web.vo.*;
import java.util.*;
@Controller
public class EBusanFoodController {
   @Autowired
   private EBusanFoodRepository eDao;
   
   @GetMapping("/busan/list")
   public String ebusan_list(@RequestParam(name="page",required = false) String page,
		   Model model)
   {
	   // @RequestParam(name="page",required = false) => 값이 없는 경우 
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   int rowSiZe=12;
	   // 페이징 사용 
	   Pageable pg=PageRequest.of(curpage-1, rowSiZe,Sort.by(Sort.Direction.DESC,"id"));
	   // 데이터 읽기 
	   Page<EBusanFood> pList=eDao.findAll(pg);
	   // Page => List변환 
	   List<EBusanFood> list=new ArrayList<EBusanFood>();
	   // 데이터가 있는 경우에만 List에 값을 채운다 => JPA(SQL)
	   if(pList!=null && pList.hasContent())
	   {
		   list=pList.getContent();
	   }
	   
	   for(EBusanFood eb:list)
	   {
		   String s=eb.getPoster();
		   s="https://www.menupan.com"+s;
		   eb.setPoster(s);
	   }
	   
	   // 총페이지 구하기 
	   int count=(int)eDao.count();
	   int totalpage=(int)(Math.ceil(count/12.0));
	   
	   // BLOCK 
	   final int BLOCK=10;
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	   
	   if(endPage>totalpage)
		   endPage=totalpage;
	   
	   model.addAttribute("list", list);
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("startPage", startPage);
	   model.addAttribute("endPage", endPage);
	   
	   return "busan/list";
	  
	   
   }
   @RequestMapping("/busan/find")
   public String busan_find(@RequestParam(name="type",required = false) String type,Model model)
   {
	   if(type==null)
		   type="한식";
	   List<EBusanFood> list=eDao.findByTypeContaining(type);
		/*
		 * for(EBusanFood eb:list) { String s=eb.getPoster();
		 * s="https://www.menupan.com"+s; eb.setPoster(s); }
		 */
	   model.addAttribute("list", list);
	   return "busan/find";
   }
   
   @GetMapping("/busan/detail")
   public String busan_detail(@RequestParam("id") String id,Model model)
   {
	   System.out.println("id:"+id);
	   EBusanFood vo=eDao.findById(id);
	   //vo.setPoster("https://www.menupan.com"+vo.getPoster());
	   //System.out.println("poster:"+vo.getPoster());
	   model.addAttribute("vo", vo);
	   return "busan/detail";
   }
}