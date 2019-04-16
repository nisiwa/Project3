package com.nisiwa.project3.utils;

/**
 * @author: nisiwa
 * @date: 2019-04-12 17:55
 */
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisUtils {

    static JedisPool jedisPool = new JedisPool();

    public  static  Jedis getJedisFromPool(){

        Jedis resource = jedisPool.getResource();

        return  resource;

    }



}