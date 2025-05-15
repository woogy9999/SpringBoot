package com.sist.web.vo;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.annotation.Id;
import jakarta.persistence.Table; 
import lombok.Data;


//@Table(name = "board")
 
//Entity => table / index와 동일 => 저장 , 수정 , 삭제 
@Document(indexName = "board")
@Data
public class ElasticBoard { 
	@Id //primary key
	private int id;
	private String name,subject,content,regdate,pwd;
	private int hit;
	
	
}
