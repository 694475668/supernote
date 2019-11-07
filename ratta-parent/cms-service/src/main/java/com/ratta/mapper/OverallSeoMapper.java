package com.ratta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ratta.domain.OverallSeoDO;

/**
 * @author page
 */
@Mapper
public interface OverallSeoMapper {
	/**
	 * 
	 * <p>queryAll</p>
	 * <p>查询所有整体的SEO</p>
	 * @param OverallSeoDO 整体的SEO信息
	 * @return 整体的SEO列表
	 */
	List<OverallSeoDO> queryAll(OverallSeoDO OverallSeoDO);
	
	
	/**
	 * 
	 * <p>save</p>
	 * <p>添加整体的SEO信息</p>
	 * @param OverallSeoDO 整体的SEO信息
	 * @return 数据库影响行数
	 */
	int save(OverallSeoDO OverallSeoDO);
	
	/**
	 * 
	 * <p>update</p>
	 * <p>更新整体的SEO信息</p>
	 * @param OverallSeoDO 整体的SEO
	 * @return 数据库影响行数
	 */
	int update(OverallSeoDO OverallSeoDO);
	
	/**
	 * 
	 * <p>delete</p>
	 * <p>删除整体的SEO信息</p>
	 * @param id 整体的SEOid
	 * @return 数据库影响行数
	 */
	int delete(@Param("id") int id);
	
	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询整体的SEO信息</p>
	 * @param id id
	 * @return 整体的SEO信息
	 */
	OverallSeoDO queryById(@Param("id") int id);
}
