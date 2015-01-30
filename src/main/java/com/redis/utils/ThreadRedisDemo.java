package com.redis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class ThreadRedisDemo extends Thread
{
    public ThreadRedisDemo(String name)
    {

        super(name);
    }

    public static void main(String[] args)
    {

        ThreadRedisDemo m1 = new ThreadRedisDemo("阿三_");
        m1.start();
        ThreadRedisDemo m2 = new ThreadRedisDemo("李四_");
        m2.start();
        ThreadRedisDemo m3 = new ThreadRedisDemo("王五_");
        m3.start();

    }

    public void run()
    {

        System.out.println(this.getName());
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
        Jedis jedis = pool.getResource();
        jedis.auth("root");
        for (int i = 0; i < 10000; i++)
        {
            jedis.watch("userId");  
            Long incrCount = jedis.incr("userId");
            String value = this.getName()+String.valueOf(incrCount);
            jedis.set("user_" + i, value);
            System.out.println(jedis.get("user_" + i));
        }

    }

    
    public void setUserInfoRank(String userid)
    {

        // Transaction tx = jedis.multi();
        //
        // for (int i = 0; i < 10; i++)
        // {
        // tx.watch("userid");
        // Response<Long> rid = tx.incr("userid");
        // System.out.println("----------------------->" + rid);
        // tx.set("t" + rid, "t" + i);
        // }
        // tx.exec();

        // Long count=JedisProxy.getPersistCache().incr("userid");
        // JedisProxy.getPersistCache().set(userid , "id:"+count);
    }
}
