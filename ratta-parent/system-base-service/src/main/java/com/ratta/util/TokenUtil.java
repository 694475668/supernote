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

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ratta.constants.Constant;

import java.util.Date;

/**
 * ******Title:程序的奥秘 ******
 * ******Description: 生成token工具类******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明  [QQ 694475668]
 * @date:Created in 2019-07-02 15:31
 */
public class TokenUtil {
    public static String createToken(Long userId, Long timestamp) {
        Date date = new Date(System.currentTimeMillis() + Constant.JWT_TTL);
        JSONObject jo = new JSONObject();
        jo.put("userId", userId);
        jo.put("createTime", timestamp);
        String sub = jo.toString();
        String token = JWT.create().withClaim("sub", sub)
                .withExpiresAt(date).sign(Algorithm.HMAC256(Constant.KEY));
        return token;
    }
}
