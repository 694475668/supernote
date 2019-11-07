package com.ratta.util;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author 刘明
 * 消息推送类
 */
@Data
@ToString(callSuper = true)
public class MessagePush {
    private String noticeCode;
    private String noticeMsg;
    private String language;
    private String busCode;
    private long timestamp;
    private String nonce;
    private List<String> equipmentNoList;

}
