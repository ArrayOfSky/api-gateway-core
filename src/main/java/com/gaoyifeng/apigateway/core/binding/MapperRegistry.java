package com.gaoyifeng.apigateway.core.binding;

import com.gaoyifeng.apigateway.core.mapping.HttpStatement;
import com.gaoyifeng.apigateway.core.session.Configuration;
import com.gaoyifeng.apigateway.core.session.GatewaySession;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gaoyifeng
 * @Classname GenericReferenceRegistry
 * @Description TODO
 * @Date 2024/11/3 22:08
 * @Created by gaoyifeng
 */
public class MapperRegistry {


    private final Configuration configuration;

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    // 泛化调用静态代理工厂
    private final Map<String, MapperProxyFactory> cacheProxyMap = new HashMap<>();

    public IGenericReference getMapper(String uri, GatewaySession gatewaySession) {
        final MapperProxyFactory mapperProxyFactory = cacheProxyMap.get(uri);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Uri " + uri + " is not known to the MapperRegistry.");
        }
        try {
            return mapperProxyFactory.newInstance(gatewaySession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    public void addMapper(HttpStatement httpStatement) {
        String uri = httpStatement.getUri();
        // 如果重复注册则报错
        if (hasMapper(uri)) {
            throw new RuntimeException("Uri " + uri + " is already known to the MapperRegistry.");
        }
        cacheProxyMap.put(uri, new MapperProxyFactory(uri));
        // 保存接口映射信息
        configuration.addHttpStatement(httpStatement);
    }

    public <T> boolean hasMapper(String uri) {
        return cacheProxyMap.containsKey(uri);
    }

}
