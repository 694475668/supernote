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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ratta.EquipmentServiceApplication;
import com.ratta.service.OssService;
import com.ratta.vo.ReferenceVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.Collections;
import java.util.List;

/**
 * ******Title:程序的奥秘 ******
 * ******Description:<p> </p>******
 * ******Company: <p> 雷塔智能科技有限公司 </p> ******
 *
 * @version 1.0:
 * @author: 刘 明  [QQ 694475668]
 * @date:Created in 2019-07-05 10:49
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EquipmentServiceApplication.class)
public class test {
    @Autowired
    private OssService ossService;

    RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 上传
     */
    @Test
    public void testApply() throws IOException {
        String url = "https://test-log.cn-sh2.ufileos.com/2SN100A10000999_20181115123502142.zip";

        FileInputStream inputStream = new FileInputStream(new File("F:\\bootstrap-3.3.7-dist.rar"));
        OutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        HttpHeaders headers = new HttpHeaders();
        //headers.set("Content-Type", "application/x-www-form-urlencoded");
        //headers.set("Accept", "text/plain");
        headers.set("Authorization", "UCloud rDlR5TKlbJ9czJLkJrNttUfkJaudoVi+r+SksVk3XFKjvmr2ue/yc/uO2TdhyBpo:FWYhzWCTDrsOtxS635QPgwCNe1k=");

        //设置请求体，注意是LinkedMultiValueMap
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("os", outputStream);

        //关闭ObjectMapper的空实体报错 无效
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
        RestTemplate restTemplate = new RestTemplate(Collections.singletonList(converter));


        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(param, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
        HttpHeaders headers1 = responseEntity.getHeaders();
        MediaType contentType = headers1.getContentType();
        System.out.println(contentType);
    }


    /**
     * 下载
     */
    @Test
    public void testDownload() {
        String url = "http://test-log.cn-sh2.ufileos.com/2SN100A10000999_20181115123502142.zip?UCloudPublicKey=rDlR5TKlbJ9czJLkJrNttUfkJaudoVi%2Br%2BSksVk3XFKjvmr2ue%2Fyc%2FuO2TdhyBpo&Expires=1562307740&Signature=Ep5iMs76l%2Br37wkxcOR1DiO%2FwVw%3D";
        InputStream inputStream = null;
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<byte[]>(headers), byte[].class);
        byte[] result = response.getBody();
        inputStream = new ByteArrayInputStream(result);
        System.out.println(inputStream);
    }

    @Test
    public void testRedis() {
        List<ReferenceVO> referenceVOList = (List<ReferenceVO>) redisTemplate.opsForList().range("TASK_PRIORITY", 0, -1);
        System.out.println(referenceVOList);
        System.out.println(referenceVOList.size());
    }
}
