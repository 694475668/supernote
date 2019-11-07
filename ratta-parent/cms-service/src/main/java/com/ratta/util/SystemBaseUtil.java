package com.ratta.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.ratta.vo.DictionaryVO;
import com.ratta.vo.UserBeanVO;

@Component
@SuppressWarnings("rawtypes")
public class SystemBaseUtil {

	@Autowired
	private RedisTemplate redisTemplate;

	public UserBeanVO querySystemUser(Long userId) {
		return (UserBeanVO) redisTemplate.opsForValue().get("cmsUserKey" + userId.toString());
	}

	@SuppressWarnings("unchecked")
	public List<DictionaryVO> queryDictionaryNameAndValue(String type) {
		return (List<DictionaryVO>) redisTemplate.opsForList().range(type, 0, -1);
	}
}
