package com.ratta.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface CmsChannel {

	/**
	 * 发消息的通道名称
	 */

	String BASE_OUTPUT = "base_output";

	/**
	 * 发消息的通道
	 */
	@Output(BASE_OUTPUT)
	MessageChannel sendMessage();

	/**
	 * 发消息的通道名称
	 */

	String OSS_OUTPUT = "oss_output";

	/**
	 * 发消息的通道
	 */
	@Output(OSS_OUTPUT)
	MessageChannel sendOssMessage();

	/**
	 * 收消息的通道名称
	 */

	String CMS_INPUT = "cms_input";

	/**
	 * 收消息的通道
	 */
	@Input(CMS_INPUT)
	SubscribableChannel recieveFileMessage();
}
