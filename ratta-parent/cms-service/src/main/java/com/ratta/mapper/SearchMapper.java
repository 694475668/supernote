package com.ratta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ratta.domain.SearchDO;

/**
 * @author page
 */
@Mapper
public interface SearchMapper {
	/**
	 * 
	 * <p>search</p>
	 * <p>搜索</p>
	 * @param key 关键字
	 * @param language 语言
	 * @return 搜索列表
	 */
	List<SearchDO> search(String key,String language);
}
