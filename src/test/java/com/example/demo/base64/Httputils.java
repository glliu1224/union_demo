package com.example.demo.base64;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Httputils {
    public void sendPostMethod(String url){
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpPost httpPost=new HttpPost(url);
        httpPost.setHeader("Content-type", "application/json;charset=utf-8");
        httpPost.setHeader("Connection", "Close");
        String jsonStr=" {\"username\":\"aries\",\"password\":\"666666\"}";
        StringEntity entity = new StringEntity(jsonStr.toString(), Charset.forName("UTF-8"));
        entity.setContentEncoding("UTF-8");  //设置编码格式
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        CloseableHttpResponse httpResponse=null;
        InputStream inputStream=null;
        try {
            httpResponse=httpClient.execute(httpPost);
            System.out.println(httpResponse.getStatusLine().getStatusCode());
            HttpEntity httpEntity=httpResponse.getEntity();
            inputStream=httpEntity.getContent(); //获取content实体内容
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while ((line=bufferedReader.readLine())!=null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpResponse!=null){
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
