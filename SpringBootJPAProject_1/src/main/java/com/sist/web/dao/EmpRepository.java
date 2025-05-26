package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sist.web.vo.*;
import java.util.*;

// 혼합 
// 가독성 (X) , 소스 간결하다 , SQL을 몰라도 처리가 가능 
// => JSP , SQL 
public interface EmpRepository extends JpaRepository<Emp, Integer> {

	// CRUD => update/insert => save() , delete => delete()
	// findAll() , count()
	// 조건 처리 (X)
	// ElasticSearch => NoSQL => JSON (MongoDB, 카산드라...)
	// 컬럼의 갯수가 명확한 경우 , 명확하지 않는 경우
	@Query(value = "SELECT * FROM emp", nativeQuery = true) // JPQL
	public List<Emp> empAllData();

	// SELECT * FROM emp WHERE empno=?
	public Emp findByEmpno(int empno);

	// ----- ----- ----------
	// WHERE empno=?
	public List<Emp> findByEnameContaining(String ename);

	// WHERE ename LIKE '%?%'
	public List<Emp> findByEnameStartsWith(String ename);
	// WHERE ename LIKE '?%'
	// SELECT DISTINCT job FROM emp
	// public List<Emp> findDistinctByJob();
	// find => SELECT , By => WHERE
	// SELECT * FROM emp WHERE sal>? sal<? sal<=? sal>=?
	/*
	 * findBySalLessThen (sal<?) findBySalGraterThen (sal>?) findBySalLessThenEqual
	 * 
	 * => SELECT * FROM emp ORDER BY sal
	 * 
	 * findBySalOrderBY => 자동으로 SQL문장을 제작
	 * 
	 * SELECT e FROM Emp e; SELECT e.empno,e.ename FROM Emp e
	 */
}