package com.home.mbm.status;

/**
* @Package: com.home.mbm.status
* @ClassName: EnumStatus
* @Description: status
* @Author: mbm
* @date: 2020/2/4 20:06
* @Version: 1.0
*/
public enum EnumStatus {

    SUCCESS("200","操作成功"),
    FALIED("500","操作失败");

    EnumStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
