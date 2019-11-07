package com.ratta.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface SystemBaseChannel {

	/**
	 * 发消息的通道名称
	 */

	String EQUIPMENT_OUTPUT = "equipment_output";
	/**
	 * 发消息的通道名称
	 */

	String USER_OUTPUT = "user_output";

	/**
	 * 发消息的通道名称
	 */

	String CMS_OUTPUT = "cms_output";
	/**
	 * 消息的订阅通道名称
	 */

	String EQUIPMENT_INPUT = "equipment_input";

	/**
	 * 发消息的通道
	 */
	@Output(USER_OUTPUT)
	MessageChannel sendUserMessageChannel();

	/**
	 * 发消息的通道
	 */
	@Output(CMS_OUTPUT)
	MessageChannel sendCmsMessageChannel();

	@Output(EQUIPMENT_OUTPUT)
	MessageChannel sendEquipmentMessageChannel();

	/**
	 * 收消息的通道
	 */
	@Input(EQUIPMENT_INPUT)
	SubscribableChannel recieveSnMessage();
}
