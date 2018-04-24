package com.zoom.rest;

import com.zoom.node.CoreGlobalProperty;
import com.zoom.util.ErrorCode;
import com.zoom.util.JSONObjectUtil;
import com.zoom.util.errorcode.ErrorFacade;
import com.zoom.util.errorcode.SysErrors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by liangbo.zhou on 18-4-23.
 */
public class RESTFacadeImpl implements RESTFacade {
    private TimeoutRestTemplate template;
    private static final Logger logger = LogManager.getLogger((RESTFacadeImpl.class));
    @Autowired
    private ErrorFacade errf;

    private interface AsyncHttpWrapper {
        void fail(ErrorCode err);

        void success(HttpEntity<String> responseEntity);
    }
    private Map<String, AsyncHttpWrapper> wrappers = new ConcurrentHashMap<String, AsyncHttpWrapper>();

    void init(){
        template = RESTFacade.createRestTemplate(CoreGlobalProperty.REST_FACADE_READ_TIMEOUT, CoreGlobalProperty.REST_FACADE_CONNECT_TIMEOUT);
    }
    @Override
    public void asyncJsonPost(String url, Object body, AsyncRESTCallback callback) {
        //in future, specific timeout will be used
        Long timeout = 300000L;
        asyncJsonPost(url, body, callback, TimeUnit.MILLISECONDS, timeout == null ? 300000 : timeout);
    }

    @Override
    public void asyncJsonPost(String url, Object body, AsyncRESTCallback callback, TimeUnit unit, long timeout) {
        asyncJsonPost(url, body, null, callback, unit, timeout);
    }

    @Override
    public void asyncJsonPost(String url, Object body, Map<String, String> headers, AsyncRESTCallback callback, TimeUnit unit, long timeout) {

        String bodyStr = JSONObjectUtil.toJsonString(body);
        asyncJsonPost(url, bodyStr, headers, callback, unit, timeout);
    }



    @Override
    public void asyncJsonPost(final String url, final String body, final AsyncRESTCallback callback, final TimeUnit unit, final long timeout) {
        asyncJsonPost(url, body, null, callback, unit, timeout);
    }

    @Override
    public void asyncJsonPost(final String url, final String body, Map<String, String> headers, final AsyncRESTCallback callback, final TimeUnit unit, final long timeout) {


        long stime = 0;


        final String taskUuid = UUID.randomUUID().toString().replace("-", "");
        final long finalStime = stime;
        AsyncHttpWrapper wrapper = new AsyncHttpWrapper() {
            AtomicBoolean called = new AtomicBoolean(false);

            final AsyncHttpWrapper self = this;



            public void fail(ErrorCode err) {
                if (!called.compareAndSet(false, true)) {
                    return;
                }

                wrappers.remove(taskUuid);

                callback.fail(err);
            }

            @Override
            public void success(HttpEntity<String> responseEntity) {
                if (!called.compareAndSet(false, true)) {
                    return;
                }
                wrappers.remove(taskUuid);
            }
        };

        try {
            wrappers.put(taskUuid, wrapper);
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            requestHeaders.setContentLength(body.length());
            MediaType JSON = MediaType.parseMediaType("application/json; charset=utf-8");
            requestHeaders.setContentType(JSON);

            if (headers != null) {
                for (Map.Entry<String, String> e : headers.entrySet()) {
                    requestHeaders.set(e.getKey(), e.getValue());
                }
            }

            HttpEntity<String> req = new HttpEntity<String>(body, requestHeaders);

            ResponseEntity<String> rsp;

            try {

                    rsp = template.exchange(url, HttpMethod.POST, req, String.class, taskUuid, unit.toMillis(timeout), unit.toMillis(timeout));

            } catch (HttpClientErrorException e) {
                String err = String.format("http status: %s, response body:%s", e.getStatusCode(), e.getResponseBodyAsString());
                wrapper.fail(errf.instantiateErrorCode(SysErrors.HTTP_ERROR, err));
                return;
            }

            if (rsp.getStatusCode() != org.springframework.http.HttpStatus.OK) {
                String err = String.format("http status: %s, response body:%s", rsp.getStatusCode().toString(), rsp.getBody());
                //logger.warn(err);
                wrapper.fail(errf.instantiateErrorCode(SysErrors.HTTP_ERROR, err));
            }
        } catch (Throwable e) {
            logger.warn(String.format("Unable to post to %s", url), e);
        }
    }


    @Override
    public HttpEntity<String> httpServletRequestToHttpEntity(HttpServletRequest req) {
        return null;
    }

    @Override
    public RestTemplate getRESTTemplate() {
        return null;
    }

    @Override
    public Map<String, HttpCallStatistic> getStatistics() {
        return null;
    }

    @Override
    public String getBaseUrl() {
        return null;
    }

    @Override
    public String getSendCommandUrl() {
        return null;
    }

    @Override
    public String getCallbackUrl() {
        return null;
    }

    @Override
    public String makeUrl(String path) {
        return null;
    }
}
