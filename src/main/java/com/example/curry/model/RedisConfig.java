package com.example.curry.model;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author jw.ma
 * @title: RedisConfig
 * @description: TODO
 * @date 2022/5/5 17:26
 */
@Slf4j
@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {

    private String host;

    private int port;

    private int timeout;


    public JedisPoolConfig getRedisConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }

    @Bean
    public JedisPool getJedisPool(){
        JedisPoolConfig config = getRedisConfig();
        JedisPool pool = new JedisPool(config,host,port);
        log.info("init JredisPool ...");
        return pool;
    }

    public static Logger getLog() {
        return log;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
