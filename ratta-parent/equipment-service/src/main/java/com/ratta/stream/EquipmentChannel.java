package com.ratta.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface EquipmentChannel {

    /**
     * 发消息的通道名称
     */

    String EQUIPMENT_OUTPUT = "equipment_output";
    /**
     * 发消息的通道名称
     */

    String FILE_OUTPUT = "file_output";

    /**
     * 发消息的通道名称
     */
    String OSS_OUTPUT = "oss_output";
    /**
     * 消息的订阅通道名称
     */

//	String EQUIPMENT_INPUT = "equipment_input";

    /**
     * 消息的订阅通道名称
     */
    String SYSTEM_BUSINESS_INPUT = "system_business_input";

    /**
     * 消息的订阅通道名称
     */
    String EQUIPMENT_INPUT = "equipment_input";

    /**
     * 发消息的通道
     */
    @Output(EQUIPMENT_OUTPUT)
    MessageChannel sendSnMessage();

    /**
     * 发消息的通道
     */
    @Output(FILE_OUTPUT)
    MessageChannel sendFileMessage();

    /**
     * 发消息的通道
     */
    @Output(OSS_OUTPUT)
    MessageChannel sendOssMessage();

    /**
     * 收消息的通道
     */
//	@Input(EQUIPMENT_INPUT)
//	SubscribableChannel recieveSnMessage();

    /**
     * 收消息的通道
     */
    @Input(SYSTEM_BUSINESS_INPUT)
    SubscribableChannel recieveSysMessage();

    /**
     * 收消息的通道
     */
    @Input(EQUIPMENT_INPUT)
    SubscribableChannel recieveEquipmentMessage();

}
