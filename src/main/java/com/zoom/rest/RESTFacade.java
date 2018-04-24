package com.zoom.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by liangbo.zhou on 18-4-22.
 */
public interface RESTFacade {

    void asyncJsonPost(String url, Object body, AsyncRESTCallback callback);

    void asyncJsonPost(String url, Object body, AsyncRESTCallback callback, TimeUnit unit, long timeout);

    void asyncJsonPost(String url, Object body, Map<String, String> headers, AsyncRESTCallback callback, TimeUnit unit, long timeout);



    void asyncJsonPost(String url, String body, AsyncRESTCallback callback, TimeUnit unit, long timeout);

    void asyncJsonPost(String url, String body, Map<String, String> headers, AsyncRESTCallback callback, TimeUnit unit, long timeout);



    HttpEntity<String> httpServletRequestToHttpEntity(HttpServletRequest req);

    RestTemplate getRESTTemplate();

    Map<String, HttpCallStatistic> getStatistics();


    String getBaseUrl();

    String getSendCommandUrl();

    String getCallbackUrl();

    String makeUrl(String path);


    // timeout are in milliseconds
    static TimeoutRestTemplate createRestTemplate(int readTimeout, int connectTimeout) {
        HttpComponentsClientHttpRequestFactory factory = new TimeoutHttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(readTimeout);
        factory.setConnectTimeout(connectTimeout);
        return new TimeoutRestTemplate(factory);
    }
}
