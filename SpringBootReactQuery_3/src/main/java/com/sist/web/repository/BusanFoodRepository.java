package com.sist.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.BusanFoodEntity;
/*
		Wrapper
		=> 데이터를 쉽게 사용이 가능하게 만든 클래스
		=> 제네릭스에서는 이반 데이터형은 사용할 수 없다 => Wrapper클래스 이용
		=> 데이터가 두개 이상일 경우에는 int => Integer
		=> JPA
			장점 : SQL문장을 몰라도 사용 , 소스가 간결
				  ----------------- 메소드 규칙
				  WHERE 문장을 지원하지 않는다
				  findBy컬럼+Option
 */
import com.sist.web.entity.BusanFoodVO;
@Repository
public interface BusanFoodRepository  extends JpaRepository<BusanFoodEntity, Integer>{
	@Query(value="SELECT fno,poster,name,num "
			+ "FROM (SELECT fno,poster,name,rownum as num "
			+ "FROM (SELECT fno,poster,name "
			+ "FROM busan_food ORDER BY fno))"
			+ "WHERE num BETWEEN :start AND :end",nativeQuery = true)
	public List<BusanFoodVO> busanFoodListData(@Param("start") Integer start, @Param("end") Integer end);
	
	public BusanFoodEntity findByFno(int fno);
}
 