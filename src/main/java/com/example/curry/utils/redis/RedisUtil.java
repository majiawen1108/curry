package com.example.curry.utils.redis;

import redis.clients.jedis.Jedis;

/**
 * @author jw.ma
 * @title: RedisUtil
 * @description: TODO
 * @date 2022/5/5 17:31
 */
public interface RedisUtil {

    public Jedis getResource();

    public void returnResource(Jedis jedis);

    public void set(String key, String value);

    public String get(String key);

    public void rpush(String key,Object o);

    public String lpop(String key);
}
