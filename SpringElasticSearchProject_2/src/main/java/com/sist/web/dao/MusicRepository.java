package com.sist.web.dao;
import com.sist.web.vo.*;
import java.util.*;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.*;
public interface MusicRepository extends ElasticsearchRepository<Music, String>{
	//@Query("{\"query\": { \"match\": { \"title\": ?0 } } }")
	public List<Music> findByTitleContaining(String title);
	

}
