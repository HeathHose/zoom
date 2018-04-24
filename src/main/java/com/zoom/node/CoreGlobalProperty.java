package com.zoom.node;

/**
 * Created by liangbo.zhou on 18-4-23.
 */
@GlobalPropertyDefinition
public class CoreGlobalProperty {
    @GlobalProperty(name = "RESTFacade.readTimeout", defaultValue = "300000")
    public static int REST_FACADE_READ_TIMEOUT;
    @GlobalProperty(name = "RESTFacade.connectTimeout", defaultValue = "15000")
    public static int REST_FACADE_CONNECT_TIMEOUT;
}
