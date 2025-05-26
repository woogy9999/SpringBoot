package com.sist.web.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

//@Entity(name="emp") 테이블명하고 일치하지 않을경우
// csv 에 null 이 있는 경우 => text
@Entity
@Data
public class Emp {
	@Id
	private int empno;
	private int sal;
	@Column(insertable = false,updatable = false)
	private int deptno;
	
	private String ename,job,mgr,hiredate,comm;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="deptno",referencedColumnName = "deptno")
	private Dept dept;
}
