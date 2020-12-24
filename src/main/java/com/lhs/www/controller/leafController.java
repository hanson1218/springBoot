package com.lhs.www.controller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class leafController {

    public void getMaxId(){
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(100);
        long startTime = System.currentTimeMillis();
        for(int i=0;i<10000;i++){
//            fixedThreadPool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try (CloseableHttpClient httpclient = HttpClients.custom().build();){
//                        HttpGet httpget = new HttpGet("http://127.0.0.1:8080/api/segment/get/user");
//                        HttpResponse response = httpclient.execute(httpget);
//                        HttpEntity httpEntity = response.getEntity();
//                        String responseContent = EntityUtils.toString(httpEntity,"UTF-8");
//                        System.out.println("userId："+responseContent);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });

            fixedThreadPool.execute(()->{
                try (CloseableHttpClient httpclient = HttpClients.custom().build();){
                    HttpGet httpget = new HttpGet("http://127.0.0.1:8080/api/segment/get/user");
                    HttpResponse response = httpclient.execute(httpget);
                    HttpEntity httpEntity = response.getEntity();
                    String responseContent = EntityUtils.toString(httpEntity,"UTF-8");
                    System.out.println("userId："+responseContent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        fixedThreadPool.shutdown();
        while(true){
            if(fixedThreadPool.isTerminated()){
                break;
            }
        }
        System.out.println("总耗时："+(System.currentTimeMillis()-startTime)/1000);
    }
}
