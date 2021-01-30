package com.vstore.framework.base.util;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 调用接口
 * @author vstore
 */
public class HttpUtils {

   private static  Logger logger = LoggerFactory.getLogger( HttpUtils.class);


    public static String post(String postBody, String interfaceUrl, Map<String,String> headerMap){
        HttpURLConnection conn =null;
        BufferedInputStream bis =null;
        String responseStr=null;
        try {
            URL url = new URL(interfaceUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

            if(headerMap!=null){
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    conn.setRequestProperty(entry.getKey(),entry.getValue())  ;
                }
            }

            conn.setReadTimeout( 1000 * 60 *10);
            conn.setConnectTimeout(1000 *5 );
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.getOutputStream().write(postBody.getBytes(StandardCharsets.UTF_8));
            conn.getOutputStream().flush();
            conn.getOutputStream().close();
            //开始获取数据
            bis = new BufferedInputStream(conn.getInputStream());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len;
            byte[] arr = new byte[1024];
            while((len=bis.read(arr))!= -1){
                bos.write(arr,0,len);
                bos.flush();
            }
            bos.close();
            responseStr= bos.toString("utf-8");
            logger.info("参数:{}",postBody);
            logger.info("返回值:{}",responseStr);
            int responseCode = conn.getResponseCode();

            if (responseCode != Constant.SUCCESS) {
                logger.error("调用接口:{},参数:{} 失败",interfaceUrl,postBody);
            }

        } catch (IOException e) {
            logger.error("调用接口:{},参数:{} ,失败:{}",interfaceUrl,postBody,e.getMessage(),e);
        }finally {
            if(bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    logger.error( e.getMessage(),e);
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return responseStr;
    }

}
