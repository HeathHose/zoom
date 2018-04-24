package com.zoom.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by liangbo.zhou on 18-4-23.
 */
public class TimeoutRestTemplate extends RestTemplate {

    public TimeoutRestTemplate(ClientHttpRequestFactory requestFactory) {
        super();
        this.setRequestFactory(requestFactory);
    }

    // connectTimeout millisecond
    // readTimeout millisecond
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, String requestId, long connectTimeout, long readTimeout) throws RestClientException {
        assert connectTimeout >= 0;
        assert readTimeout >= 0;
        assert requestId != null;

        this.setRequestConfig(connectTimeout, readTimeout);

        long startTime = System.currentTimeMillis();
        ResponseEntity<T> rsp = null;
        try {
            rsp = this.exchange(url, method, requestEntity, responseType);
        }catch (Throwable t){
            long endTime = System.currentTimeMillis();
            logger.warn(String.format("MyRestTemplate exchange fail, requestId=%s, connectTimeout=%s, readTimeout=%s, spendTime=%s", requestId, connectTimeout, readTimeout, endTime - startTime), t);
            throw t;
        }finally {
            // liningTODO
            // clean info log
            long endTime = System.currentTimeMillis();
            logger.info(String.format("MyRestTemplate timeout info, requestId=%s, connectTimeout=%s, readTimeout=%s, spendTime=%s", requestId, connectTimeout, readTimeout, endTime - startTime));
            if(endTime - startTime > (connectTimeout + 3000) || endTime - startTime > (readTimeout + 3000) ){
                logger.error(String.format("MyRestTemplate timeout error, requestId=%s, connectTimeout=%s, readTimeout=%s, spendTime=%s", requestId, connectTimeout, readTimeout, endTime - startTime));
            }
        }

        return rsp;
    }

    private void setRequestConfig(long connectTimeout, long readTimeout){
        this.getMyRequestFactory().setRequestTimeoutConfig(connectTimeout, readTimeout);
    }


    private TimeoutHttpComponentsClientHttpRequestFactory getMyRequestFactory(){
        TimeoutHttpComponentsClientHttpRequestFactory rf = (TimeoutHttpComponentsClientHttpRequestFactory) this.getRequestFactory();
        return rf;
    }

}
