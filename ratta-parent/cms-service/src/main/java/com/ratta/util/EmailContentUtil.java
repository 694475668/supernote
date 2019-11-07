package com.ratta.util;

import org.apache.commons.lang3.StringUtils;

import com.ratta.dto.CustomizeDTO;
import com.ratta.dto.FeedbackRecordDTO;

/** 
* 邮件内容<br>
* author：yll
*/

public class EmailContentUtil {
	 /**
     * <p>邮件反馈</p>
     * @param srcId 模板  
     * @param feedbackRecordDTO 内容
     */
    public static String emailFeedback(String srcId, FeedbackRecordDTO feedbackRecordDTO){
		if(StringUtils.isEmpty(feedbackRecordDTO.getArea())) {
			feedbackRecordDTO.setArea("");
		}
		if(StringUtils.isEmpty(feedbackRecordDTO.getCountryCode())) {
			feedbackRecordDTO.setCountryCode("");
		}
		if(StringUtils.isEmpty(feedbackRecordDTO.getTelephone())) {
			feedbackRecordDTO.setTelephone("");
		}
        String name = "cid:" + srcId;
        StringBuffer theMessage = new StringBuffer();
        theMessage.append("<div class='box' style='width: 90%; height: auto;margin: 30px auto;border: 1px solid #d3d3d3;padding: 20px 15px; border-radius: 10px;box-shadow: 0 10px 30px #eee;'>");
        theMessage.append("<div class='head' style='color: #333;font-size: 16px;font-weight: bold;'>亲：</div>");
        theMessage.append("<div class='cont' style='font-size: 16px;border-bottom: 1px solid #868686;line-height: 1.5;padding: 10px 0 30px 0;'>");
        theMessage.append("<div class='message'>您好，您有新的反馈提醒：");
        theMessage.append("<span style='font-size: 19px; color: #0070c9; font-weight: 800;'>" + "</span></div>");
        theMessage.append("<div style='font-size: 19px; color: #0070c9; font-weight: 800;'>姓名：" + feedbackRecordDTO.getName() + "</div>");
        theMessage.append("<div style='font-size: 19px; color: #0070c9; font-weight: 800;'>邮箱：" + feedbackRecordDTO.getEmail() + "</div>");
        theMessage.append("<div style='font-size: 19px; color: #0070c9; font-weight: 800;'>手机号：" + "(" + feedbackRecordDTO.getArea() + " " + feedbackRecordDTO.getCountryCode() +")" + feedbackRecordDTO.getTelephone() + "</div>");
        theMessage.append("<div style='font-size: 19px; color: #0070c9; font-weight: 800;'>设备号：" + feedbackRecordDTO.getEquipmentNumber() + "</div>");
        theMessage.append("<div style='font-size: 19px; color: #0070c9; font-weight: 800;'>反馈内容：" + feedbackRecordDTO.getDescription() + "</div>");
        theMessage.append("<div class='hint' style='font-size: 16px; color: #999; margin-top: 10px;'>请尽快联系用户处理，谢谢！</div> </div>");
        theMessage.append("<div class='last' style='padding: 25px 0 20px 0'>");
        theMessage.append("<ul style='font-size: 14px;padding:0;line-height: 2'>");
        theMessage.append("<li style='list-style: none'><img style='width:40px;height:40px' src=' " + name + " '></li>");
        theMessage.append("<li style='font-weight: bold;color: #333; list-style: none;'>Supernote团队</li>");
        theMessage.append("<li style='list-style: none;'>客服热线：400-921-7470</li>");
        theMessage.append("<li style='list-style: none;'>服务：service@supernote.com</li>");
        theMessage.append("</ul> </div> </div>");
        return theMessage.toString();
    }
    
    /**
     * <p>套餐定制提醒</p>
     * @param srcId 模板  
     * @param customizeDTO 内容
     */
    public static String emailCustomize(String srcId, CustomizeDTO customizeDTO){
		if(StringUtils.isEmpty(customizeDTO.getArea())) {
			customizeDTO.setArea("");
		}
		if(StringUtils.isEmpty(customizeDTO.getCountryCode())) {
			customizeDTO.setCountryCode("");
		}
		if(StringUtils.isEmpty(customizeDTO.getTelephone())) {
			customizeDTO.setTelephone("");
		}
        String name = "cid:" + srcId;
        StringBuffer theMessage = new StringBuffer();
        theMessage.append("<div class='box' style='width: 90%; height: auto;margin: 30px auto;border: 1px solid #d3d3d3;padding: 20px 15px; border-radius: 10px;box-shadow: 0 10px 30px #eee;'>");
        theMessage.append("<div class='head' style='color: #333;font-size: 16px;font-weight: bold;'>亲：</div>");
        theMessage.append("<div class='cont' style='font-size: 16px;border-bottom: 1px solid #868686;line-height: 1.5;padding: 10px 0 30px 0;'>");
        theMessage.append("<div class='message'>您好，您有新的套餐定制提醒：");
        theMessage.append("<span style='font-size: 19px; color: #0070c9; font-weight: 800;'>" + "</span></div>");
        theMessage.append("<div style='font-size: 19px; color: #0070c9; font-weight: 800;'>姓名：" + customizeDTO.getUserName() + "</div>");
        theMessage.append("<div style='font-size: 19px; color: #0070c9; font-weight: 800;'>邮箱：" + customizeDTO.getEmail() + "</div>");
        theMessage.append("<div style='font-size: 19px; color: #0070c9; font-weight: 800;'>手机号：" + "(" + customizeDTO.getArea() + " " + customizeDTO.getCountryCode() +")" + customizeDTO.getTelephone() + "</div>");
        theMessage.append("<div style='font-size: 19px; color: #0070c9; font-weight: 800;'>产品：" + customizeDTO.getProduct() + "</div>");
        theMessage.append("<div style='font-size: 19px; color: #0070c9; font-weight: 800;'>封套：" + customizeDTO.getCoverColor() + "</div>");
        theMessage.append("<div style='font-size: 19px; color: #0070c9; font-weight: 800;'>笔：" + customizeDTO.getPenColor() + "</div>");
        theMessage.append("<div style='font-size: 19px; color: #0070c9; font-weight: 800;'>描述：" + customizeDTO.getDescription() + "</div>");
        theMessage.append("<div class='hint' style='font-size: 16px; color: #999; margin-top: 10px;'>请尽快联系用户处理，谢谢！</div> </div>");
        theMessage.append("<div class='last' style='padding: 25px 0 20px 0'>");
        theMessage.append("<ul style='font-size: 14px;padding:0;line-height: 2'>");
        theMessage.append("<li style='list-style: none'><img style='width:40px;height:40px' src=' " + name + " '></li>");
        theMessage.append("<li style='font-weight: bold;color: #333; list-style: none;'>Supernote团队</li>");
        theMessage.append("<li style='list-style: none;'>客服热线：400-921-7470</li>");
        theMessage.append("<li style='list-style: none;'>服务：service@supernote.com</li>");
        theMessage.append("</ul> </div> </div>");
        return theMessage.toString();
    }
}
