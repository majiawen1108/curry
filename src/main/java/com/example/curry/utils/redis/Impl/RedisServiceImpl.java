package com.example.curry.utils.redis.Impl;

import com.alibaba.fastjson.JSON;
import com.example.curry.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author jw.ma
 * @title: RedisServiceImpl
 * @description: TODO
 * @date 2022/5/5 17:32
 */
@Service
@Slf4j
public class RedisServiceImpl implements RedisUtil {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public Jedis getResource() {
        return jedisPool.getResource();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void returnResource(Jedis jedis) {
        if(jedis != null){
            jedisPool.returnResource(jedis);
        }
    }

    @Override
    public void set(String key, String value) {
        Jedis jedis=null;
        try{
            jedis = getResource();
            jedis.set(key, value);
            log.info("Redis set success - " + key + ", value:" + value);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + value);
        }finally{
            returnResource(jedis);
        }
    }

    @Override
    public String get(String key) {
        String result = null;
        Jedis jedis=null;
        try{
            jedis = getResource();
            result = jedis.get(key);
            log.info("Redis get success - " + key + ", value:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + result);
        }finally{
            returnResource(jedis);
        }
        return result;
    }

    @Override
    public void rpush(String key,Object o) {
        Jedis jedis=null;
        try{
            jedis = getResource();
            jedis.rpush(key, String.valueOf(o));
            log.info("Redis set success - " + key + ", value:" + String.valueOf(o));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + String.valueOf(o));
        }finally{
            jedis.close();
        }
    }

    @Override
    public String lpop(String key) {
        Jedis jedis=null;
        String res = null;
        try{
            jedis = getResource();
            res = jedis.lpop(key);
            log.info("Redis set success - " + key + ", value:" + res);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + res);
        }finally{
            jedis.close();
        }
        return res;
    }

    private String toJson(Object object){
        return !(object instanceof Integer) && !(object instanceof Long) && !(object instanceof Float) && !(object instanceof Double) && !(object instanceof Boolean) && !(object instanceof String) ? JSON.toJSONString(object) : String.valueOf(object);
    }
}
