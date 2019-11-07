package com.ratta.schedule;

import com.ratta.constants.Constant;
import com.ratta.domain.ReferenceDO;
import com.ratta.domain.ScheduleTaskDO;
import com.ratta.dto.ScheduleAddLogDTO;
import com.ratta.mapper.ReferenceMapper;
import com.ratta.service.ScheduleTaskService;
import com.ratta.stream.SystemBaseChannel;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 刘明 调度任务工厂
 */
@Slf4j
@EnableBinding(SystemBaseChannel.class)
public class QuartzJobFactory implements Job {

	@Autowired
	private ScheduleTaskService scheduleTaskService;

	@Autowired
	private ReferenceMapper referenceMapper;

	@Resource(name = SystemBaseChannel.EQUIPMENT_OUTPUT)
	private MessageChannel sendEquipmentMessageChannel;
	@Resource(name = SystemBaseChannel.USER_OUTPUT)
	private MessageChannel sendUserMessageChannel;
	@Resource(name = SystemBaseChannel.CMS_OUTPUT)
	private MessageChannel sendCmsMessageChannel;

	/**
	 * 所有的调度任务执行入口
	 *
	 * @see Job#execute(JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleTaskDO scheduleTask = (ScheduleTaskDO) context.getMergedJobDataMap().get("ScheduleTask");
		String serviceName = scheduleTask.getBzCode();

		Date startD = new Date();

		if ("1".equals(scheduleTask.getStatus())) {
			return;
		}

		if (Constant.DATABASECLEAN.equals(serviceName)) {
			Long daysBefore = 0L;
			// 清除n天之前的数据, 注意是 “1” 不是 “01”
			List<ReferenceDO> ref = referenceMapper.queryByParamCode("01", "CLEAN_DAY");
			if (ref != null && ref.size() > 0) {
				daysBefore = Long.valueOf(ref.get(0).getValue());
			}
			// 清理调度记录
			scheduleTaskService.dataClean(daysBefore);
			// 清理登录记录
			sendUserMessageChannel.send(
					MessageBuilder.withPayload(daysBefore).setHeader(Constant.USERLONGIN, Constant.DELETE).build());
		} else if (Constant.EQUIPMENTLOGCLEAN.equals(serviceName)) {
			Long daysBefore = 0L;
			List<ReferenceDO> ref = referenceMapper.queryByParamCode("01", "EQUIPMENTLOG_CLEAN_DAY");
			if (ref != null && ref.size() > 0) {
				daysBefore = Long.valueOf(ref.get(0).getValue());
			}
			// 清理设备日志
			sendEquipmentMessageChannel.send(
					MessageBuilder.withPayload(daysBefore).setHeader(Constant.EQUIPMENTLOG, Constant.DELETE).build());
		} else if (Constant.CMSCLEAN.equals(serviceName)) {
			sendCmsMessageChannel
					.send(MessageBuilder.withPayload("liuming").setHeader(Constant.FILE, Constant.DELETE).build());
		}
		// 执行结束 添加日志
		Date endD = new Date();
		// String result = execResult > 0 ? "0" : "1";
		log.info("调度任务:{}执行结束,开始时间:{},结束时间:{},结果:{}", scheduleTask.getName(), startD, endD, "1");
		addLog(Long.valueOf(scheduleTask.getId()), startD, endD, "1");
	}

	/**
	 * 添加 task 日志
	 *
	 * @param taskid 任务 id
	 * @param startD 开始时间
	 * @param endD   结束时间
	 * @param result 执行结果
	 */
	public void addLog(Long taskid, Date startD, Date endD, String result) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ScheduleAddLogDTO log = new ScheduleAddLogDTO();
		log.setTaskId(taskid);
		log.setKsrq(simpleDateFormat.format(startD));
		log.setJsrq(simpleDateFormat.format(endD));
		log.setResult(result);
		scheduleTaskService.addLog(log);
	}
}
