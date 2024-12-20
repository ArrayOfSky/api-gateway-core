package com.gaoyifeng.apigateway.core.session.defaults;

import com.gaoyifeng.apigateway.core.datasource.DataSource;
import com.gaoyifeng.apigateway.core.datasource.DataSourceFactory;
import com.gaoyifeng.apigateway.core.datasource.unpooled.UnpooledDataSourceFactory;
import com.gaoyifeng.apigateway.core.executor.Executor;
import com.gaoyifeng.apigateway.core.session.Configuration;
import com.gaoyifeng.apigateway.core.session.GatewaySession;
import com.gaoyifeng.apigateway.core.session.GatewaySessionFactory;

/**
 * @author gaoyifeng
 * @Classname DefaultGatewaySessionFactory
 * @Description TODO
 * @Date 2024/11/7 21:18
 * @Created by gaoyifeng
 */
public class DefaultGatewaySessionFactory implements GatewaySessionFactory {

    private final Configuration configuration;

    public DefaultGatewaySessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public GatewaySession openSession(String uri) {
        // 获取数据源连接信息：这里把 Dubbo、HTTP 抽象为一种连接资源
        DataSourceFactory dataSourceFactory = new UnpooledDataSourceFactory();
        dataSourceFactory.setProperties(configuration, uri);
        DataSource dataSource = dataSourceFactory.getDataSource();

        // 创建执行器
        Executor executor = configuration.newExecutor(dataSource.getConnection());
        // 创建会话：DefaultGatewaySession
        return new DefaultGatewaySession(configuration, uri, executor);
    }



}
