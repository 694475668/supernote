package com.ratta.service;

import com.ratta.dto.FirmwareManualDTO;
import com.ratta.dto.FirmwareManualQueryDTO;
import com.ratta.dto.FirmwareManualTypeLanDTO;
import com.ratta.dto.LineDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.CommonVO;
import com.ratta.vo.FirmwareManualPartVO;
import com.ratta.vo.FirmwareManualVO;

/**
 * @author page
 */
public interface FirmwareManualService {
	/**
	 * 
	 * <p>query</p>
	 * <p>查询固件说明书</p>
	 * @param firmwareManualQueryDTO 查询参数
	 * @return 固件说明书列表
	 */
	CommonListVO<FirmwareManualVO> query(FirmwareManualQueryDTO firmwareManualQueryDTO);
	
	/**
	 * 
	 * <p>queryByTypeAndLan</p>
	 * <p>根据语言和类型查询固件说明书列表信息</p>
	 * @param firmwareManualTypeLanDTO 查询参数
	 * @return 固件说明书列表
	 */
	CommonListVO<FirmwareManualPartVO> queryByTypeAndLan(FirmwareManualTypeLanDTO firmwareManualTypeLanDTO,String line);
	
	/**
	 * 
	 * <p>save</p>
	 * <p>添加固件说明书信息</p>
	 * @param  firmwareManualDTO 固件说明书信息
	 * @param  userId userId
	 * @return 数据库影响行数
	 */
	BaseVO save(FirmwareManualDTO firmwareManualDTO,String userId);
	
	/**
	 * 
	 * <p>update</p>
	 * <p>更新固件说明书信息</p>
	 * @param firmwareManualDTO 固件说明书
	 * @param id id
	 * @param  userId userId
	 * @return 返回结果
	 */
	BaseVO update(FirmwareManualDTO firmwareManualDTO,int id,String userId);
	
	/**
	 * 
	 * <p>delete</p>
	 * <p>删除固件说明书信息</p>
	 * @param id 固件说明书id
	 * @return 返回结果
	 */
	BaseVO delete(int id);
	
	/**
	 * 
	 * <p>queryById</p>
	 * <p>根据id查询固件说明书信息</p>
	 * @param id id
	 * @return 固件说明书信息
	 */
	CommonVO<FirmwareManualVO> queryById(int id);
	
	/**
	 * 
	 * <p>updateLine</p>
	 * <p>修改固件说明书上线下线</p>
	 * @param lineDTO 线上线下
	 * @param id id
	 * @param  userId userId
	 * @return 返回结果
	 */
	BaseVO updateLine(LineDTO lineDTO,int id,String userId);
	
	/**
	 * 
	 * <p>updateSeq</p>
	 * <p>修改固件说明书序号</p>
	 * @param seqDTO 排序
	 * @param id id
	 * @param  userId userId
	 * @return 返回结果
	 */
	BaseVO updateSeq(SeqDTO seqDTO,int id,String userId);
}
