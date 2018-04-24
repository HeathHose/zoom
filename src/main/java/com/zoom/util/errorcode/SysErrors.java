package com.zoom.util.errorcode;

/**
 * Created by liangbo.zhou on 18-4-24.
 */
public enum SysErrors {
    HTTP_ERROR(1015);

    private String code;

    private SysErrors(int id) {
        code = String.format("SYS.%s", id);
    }

    @Override
    public String toString() {
        return code;
    }
}
