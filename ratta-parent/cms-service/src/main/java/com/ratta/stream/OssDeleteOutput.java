package com.ratta.stream;

import javax.annotation.Resource;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.ratta.dto.OssDTO;

/**
 * 删除ufile上的文件
 * 
 * @author gxy
 *
 */
@EnableBinding(CmsChannel.class)
@Service
public class OssDeleteOutput {

	@Resource(name = CmsChannel.OSS_OUTPUT)
	private MessageChannel sendMessageChannel;

	public void delete(OssDTO ossDTO) {
		sendMessageChannel.send(MessageBuilder.withPayload(ossDTO).build());
	}
}
