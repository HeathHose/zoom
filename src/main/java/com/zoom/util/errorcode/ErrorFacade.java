package com.zoom.util.errorcode;

import com.zoom.util.ErrorCode;

/**
 * Created by liangbo.zhou on 18-4-23.
 */
public interface ErrorFacade {
    ErrorCode instantiateErrorCode(Enum code, String details);
}
