package com.gaoyifeng.apigateway.socket.agreement;

/**
 * @author gaoyifeng
 * @Classname GatewayResultMessage
 * @Description TODO 网关结果封装
 * @Date 2024/11/15 20:53
 * @Created by gaoyifeng
 */
public class GatewayResultMessage
{

    private String code;
    private String info;
    private Object data;

    protected GatewayResultMessage(String code, String info, Object data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public static GatewayResultMessage buildSuccess(Object data) {
        return new GatewayResultMessage(AgreementConstants.ResponseCode._200.getCode(), AgreementConstants.ResponseCode._200.getInfo(), data);
    }

    public static GatewayResultMessage buildError(String code, String info) {
        return new GatewayResultMessage(code, info, null);
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public Object getData() {
        return data;
    }

}
