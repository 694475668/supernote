package com.ratta.stream;

import com.ratta.dto.OssDTO;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 删除ufile上的文件
 * 
 * @author gxy
 *
 */
@EnableBinding(EquipmentChannel.class)
@Service
public class OssDeleteOutput {

	@Resource(name = EquipmentChannel.OSS_OUTPUT)
	private MessageChannel sendMessageChannel;

	public void delete(OssDTO ossDTO) {
		sendMessageChannel.send(MessageBuilder.withPayload(ossDTO).build());
	}
}
