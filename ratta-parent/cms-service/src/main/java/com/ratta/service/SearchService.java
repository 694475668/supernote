package com.ratta.service;

import com.ratta.dto.SearchDTO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.SearchVO;

/**
 * @author page
 */
public interface SearchService {
	/**
	 * 
	 * <p>search</p>
	 * <p>搜索</p>
	 * @param searchDTO 查询参数
	 * @return 搜索列表
	 */
	CommonListVO<SearchVO> search(SearchDTO searchDTO);
}
