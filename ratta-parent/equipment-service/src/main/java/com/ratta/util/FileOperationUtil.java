package com.ratta.util;
/*
                                           _ooOoo_
                                          o8888888o
                                          88" . "88
                                          (| -_- |)
                                          O\  =  /O
                                       ____/`---'\____
                                     .'  \\|     |//  `.
                                    /  \\|||  :  |||//  \
                                   /  _||||| -:- |||||-  \
                                   |   | \\\  -  /// |   |
                                   | \_|  ''\---/''  |   |
                                   \  .-\__  `-`  ___/-. /
                                 ___`. .'  /--.--\  `. . __
                              ."" '<  `.___\_<|>_/___.'  >'"".
                             | | :  `- \`.;`\ _ /`;.`/ - ` : | |
                             \  \ `-.   \_ __\ /__ _/   .-` /  /
                        ======`-.____`-.___\_____/___.-`____.-'======
                                           `=---='
                        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                                 佛祖保佑       永无BUG
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * ******Title:程序的奥秘 ******
 * ******Description:<p> 文件下载工具类</p>******
 * ******Company: <p> 雷塔智能科技有限公司 </p> ******
 *
 * @version 1.0:
 * @author: 刘 明  [QQ 694475668]
 * @date:Created in 2019-07-05 14:14
 */
public class FileOperationUtil {
    @Autowired
    private static RestTemplate restTemplate=new RestTemplate();

    /**
     * 文件下载
     *
     * @param url
     * @return
     */
    public static InputStream fileDownload(String url) {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<byte[]>(headers), byte[].class);
        byte[] result = response.getBody();
        InputStream inputStream = new ByteArrayInputStream(result);
        System.out.println(inputStream);
        return inputStream;
    }

    /**
     * 文件上传
     *
     * @param url
     * @param os
     * @param authorization
     */
    public static void fileUpload(String url, OutputStream os, String authorization) {
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        //验证签名
        headers.set("Authorization", authorization);

        //设置请求体，注意是LinkedMultiValueMap
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("os", os);

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(param, headers);
        restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
    }
}
