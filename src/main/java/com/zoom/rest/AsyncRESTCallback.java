package com.zoom.rest;

import com.zoom.util.ErrorCode;
import org.springframework.http.HttpEntity;

/**
 * Created by liangbo.zhou on 18-4-23.
 */
public interface AsyncRESTCallback {
    public abstract void fail(ErrorCode err);

    public abstract void success(HttpEntity<String> responseEntity);
}
