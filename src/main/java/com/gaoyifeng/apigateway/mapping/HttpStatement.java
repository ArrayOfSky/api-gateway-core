package com.gaoyifeng.apigateway.mapping;

/**
 * @author gaoyifeng
 * @Classname HttpStatement
 * @Description TODO 网关接口映射信息
 * @Date 2024/11/5 19:52
 * @Created by gaoyifeng
 */
public class HttpStatement {

    /** 应用名称； */
    private String application;
    /** 服务接口；RPC、其他 */
    private String interfaceName;
    /** 服务方法；RPC#method */
    private String methodName;
    /** 参数类型(RPC 限定单参数注册)；
     * new String[]{"java.lang.String"}、
     * new String[]{"com.gaoyifeng.gateway.dto.xxxReq"}
     */
    private String parameterType;
    /** 网关接口 */
    private String uri;
    /** 接口类型；GET、POST、PUT、DELETE */
    private HttpCommandType httpCommandType;

    private boolean auth;

    public HttpStatement(String application, String interfaceName, String methodName, String parameterType, String uri, HttpCommandType httpCommandType, boolean auth) {
        this.application = application;
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.parameterType = parameterType;
        this.uri = uri;
        this.httpCommandType = httpCommandType;
        this.auth = auth;
    }

    public String getApplication() {
        return application;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getUri() {
        return uri;
    }

    public HttpCommandType getHttpCommandType() {
        return httpCommandType;
    }

    public String getParameterType() {
        return parameterType;
    }
    public boolean isAuth() {
        return auth;
    }
}